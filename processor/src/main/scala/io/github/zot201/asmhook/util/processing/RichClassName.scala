package io.github.zot201.asmhook.util.processing

import com.squareup.javapoet.{ClassName, ParameterizedTypeName, TypeName}

class RichClassName(val className: ClassName) extends AnyVal {
  def parameterize(typeArguments: TypeName*) = ParameterizedTypeName.get(className, typeArguments: _*)
}
