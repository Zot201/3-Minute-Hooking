package io.github.zot201.asmhook.util.processing

import com.squareup.javapoet.{ClassName, ParameterizedTypeName, TypeName}

class RichClassName(val n: ClassName) extends AnyVal {
  def parameterize(typeArguments: TypeName*): TypeName = typeArguments match {
    case Seq() => n
    case _ => ParameterizedTypeName.get(n, typeArguments: _*)
  }
}
