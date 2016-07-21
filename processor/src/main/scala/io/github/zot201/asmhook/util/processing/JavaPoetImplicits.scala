package io.github.zot201.asmhook.util.processing

import javax.lang.model.`type`.TypeMirror
import javax.lang.model.element.{TypeElement, TypeParameterElement}

import com.squareup.javapoet.{ClassName, TypeName, TypeVariableName}

import scala.language.implicitConversions

trait JavaPoetImplicits {
  implicit def toClassName(element: TypeElement): ClassName = ClassName.get(element)
  implicit def toRichClassName[E](name: E)(implicit ev: E => ClassName): RichClassName = new RichClassName(name)

  implicit def toTypeName(mirror: TypeMirror): TypeName = TypeName.get(mirror)

  implicit def toTypeVariableName(element: TypeParameterElement): TypeVariableName = TypeVariableName.get(element)
}
