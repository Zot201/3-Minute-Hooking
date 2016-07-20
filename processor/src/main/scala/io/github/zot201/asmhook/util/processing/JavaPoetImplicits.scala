package io.github.zot201.asmhook.util.processing

import javax.lang.model.`type`.TypeMirror
import javax.lang.model.element.TypeElement

import com.squareup.javapoet.{ClassName, TypeName}

import scala.language.implicitConversions

trait JavaPoetImplicits {
  implicit def toClassName(element: TypeElement): ClassName = ClassName.get(element)

  implicit def toTypeName(mirror: TypeMirror): TypeName = TypeName.get(mirror)
}
