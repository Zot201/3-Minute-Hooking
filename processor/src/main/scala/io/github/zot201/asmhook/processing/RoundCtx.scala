/*
 * Copyright 2016 Zot201
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.zot201.asmhook.processing

import java.lang.annotation.Annotation
import javax.annotation.processing.{ProcessingEnvironment, RoundEnvironment}
import javax.lang.model.`type`.TypeMirror
import javax.lang.model.element.TypeElement

import scala.reflect.ClassTag

class RoundCtx(
  val roundEnv: RoundEnvironment,
  val processingEnv: ProcessingEnvironment) {

  var processed = false

  def annotatedElements[T <: Annotation](implicit tag: ClassTag[T]) =
    roundEnv.getElementsAnnotatedWith(tag.runtimeClass.asInstanceOf[Class[T]])

  def getElement(mirror: TypeMirror) =
    processingEnv.getTypeUtils.asElement(mirror).asInstanceOf[TypeElement] // TODO: Check if this casting safe

}
