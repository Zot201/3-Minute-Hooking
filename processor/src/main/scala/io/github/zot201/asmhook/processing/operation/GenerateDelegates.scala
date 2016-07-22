package io.github.zot201.asmhook.processing.operation

import javax.lang.model.element.{ExecutableElement, Modifier, VariableElement}

import com.squareup.javapoet._
import io.github.zot201.asmhook.annotation.HookInstance
import io.github.zot201.asmhook.processing.context.RoundContext

import scala.annotation.tailrec

class GenerateDelegates(implicit ctx: RoundContext) {
  lazy val threadLocalType = ctx.elementOf[ThreadLocal[_]]
  lazy val hookInstanceType = ctx.elementOf[HookInstance]
  lazy val annotationSet = ctx.methodAnnotations - hookInstanceType

  for (t <- ctx.delegates) {
    var init = false
    lazy val b = {
      init = true
      TypeSpec.classBuilder(s"${t.getSimpleName}AsmDelegate")
    }

    for (member <- t.getEnclosedElements) {
      var provider = false
      var others = false

      val it = member.getAnnotationMirrors
        .iterator
        .map(_.getAnnotationType.erasure)

      @tailrec def classify(): Unit = if (it.hasNext) {
        it.next() match {
          case `hookInstanceType` =>
            provider = true
          case c if annotationSet.contains(c) =>
            others = true
          case _ =>
        }
        if (!provider || !others) {
          classify()
        }
      }
      classify()

      if (provider) {
        require(!member.getModifiers.contains(Modifier.PRIVATE), s"$member must not be private")
        require(member.getModifiers.contains(Modifier.STATIC), s"$member must be static")

        b.addMethod(member match {
          case m: ExecutableElement =>
            require(m.getParameters.isEmpty, s"$m must not have parameters")

            MethodSpec.methodBuilder("hookInstance")
              .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
              .returns(m.getReturnType)
              .addStatement("return $L.$L()", t.getSimpleName, m.getSimpleName)
              .build
          case f: VariableElement =>
            MethodSpec.methodBuilder("hookInstance")
              .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
              .returns(f.asType)
              .addStatement("return $L.$L", t.getSimpleName, f.getSimpleName)
              .build
        })
      }

      if (others) {
        require(!member.getModifiers.contains(Modifier.PRIVATE), s"$member must not be private")
        require(!member.getModifiers.contains(Modifier.STATIC), s"$member must not be static")

        b.addMethod(member match {
          case m: ExecutableElement =>
            val parameters = m.getParameters
              .view
              .zipWithIndex
              .map { case (p, i) => ParameterSpec.builder(p.asType, s"_$i").build }
              .toList
            val statement = parameters
              .view
              .map(_.name)
              .mkString("return $L.$L(", ", ", ")")
            val typeParameters = t.getTypeParameters
              .map(toTypeVariableName)

            MethodSpec.methodBuilder(s"${m.getSimpleName}$$$$")
              .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
              .addTypeVariables((typeParameters ++ m.getTypeParameters.view.map(toTypeVariableName)).asJava)
              .returns(m.getReturnType)
              .addParameter(t.parameterize(typeParameters: _*), "self")
              .addParameters(parameters.asJava)
              .addStatement(statement, "self", m.getSimpleName)
              .build
          case f: VariableElement =>
            val declaringType = f.asType
            require(declaringType.erasure == threadLocalType, s"Only ThreadLocal is supported, $f")

            val typeParameters = declaringType.typeParameters
            require(typeParameters.size > 0, s"$f must have a type parameter")

            MethodSpec.methodBuilder(s"${f.getSimpleName}$$set")
              .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
              .returns(TypeName.VOID)
              .addParameter(t, "self")
              .addParameter(typeParameters(0), "value")
              .addStatement("$L.$L.set($L)", "self", f.getSimpleName, "value")
              .build
        })
      }
    }

    if (init) {
      JavaFile.builder(t.packageName, b.build)
        .build
        .writeTo(System.out)
    }
  }
}
