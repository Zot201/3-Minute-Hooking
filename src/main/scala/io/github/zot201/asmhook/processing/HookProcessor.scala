package io.github.zot201.asmhook.processing

import java.util
import javax.annotation.processing._
import javax.lang.model.element.TypeElement

class HookProcessor extends AbstractProcessor {
  override def process(annotations: util.Set[_ <: TypeElement], roundEnv: RoundEnvironment): Boolean = false
}
