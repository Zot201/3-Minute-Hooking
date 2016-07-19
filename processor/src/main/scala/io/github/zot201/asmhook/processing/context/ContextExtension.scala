package io.github.zot201.asmhook.processing.context

import javax.lang.model.`type`.TypeMirror

import scala.language.implicitConversions

trait ContextExtension {
  implicit def wrap(m: TypeMirror): RichTypeMirror = new RichTypeMirror(m)
}
