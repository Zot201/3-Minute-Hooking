package io.github.zot201.asmhook.processing.operation

import javax.lang.model.element.{ExecutableElement, Modifier}

import com.squareup.javapoet.{JavaFile, MethodSpec, ParameterSpec, TypeSpec}
import io.github.zot201.asmhook.processing.context.RoundContext

class GenerateDelegates(implicit val ctx: RoundContext) {
  lazy val annotationSet = ctx.methodAnnotations

  for (t <- ctx.delegates) {
    var init = false
    lazy val b = {
      init = true
      TypeSpec.classBuilder(s"${t.getSimpleName}AsmDelegate")
    }

    t.getEnclosedElements
      .view
      .filter(_.getAnnotationMirrors
        .exists(annotationSet contains _.getAnnotationType.toElement))
      .foreach {
        case m: ExecutableElement =>
          val s = m.getSimpleName.toString
          val parameters = m.getParameters
            .view
            .zipWithIndex
            .map { case (p, i) => ParameterSpec.builder(p.asType, s"_$i").build }
            .toList
          val statement = parameters
            .view
            .map(_.name)
            .mkString("return $L.$L(", ", ", ")")

          b.addMethod(MethodSpec.methodBuilder(s)
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(m.getReturnType)
            .addParameter(t, "self")
            .addParameters(parameters.asJava)
            .addStatement(statement, "self", s)
            .build)
        case _ =>
      }

    if (init) {
      JavaFile.builder(t.packageName, b.build)
        .build
        .writeTo(System.out)
    }
  }
}
