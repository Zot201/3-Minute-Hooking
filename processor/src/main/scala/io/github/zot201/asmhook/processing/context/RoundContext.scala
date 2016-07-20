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
package io.github.zot201.asmhook.processing.context

import java.lang.annotation.{Annotation, ElementType, Target}
import javax.annotation.processing.{ProcessingEnvironment, RoundEnvironment}
import javax.lang.model.`type`.NoType
import javax.lang.model.element.TypeElement

import scala.annotation.tailrec
import scala.collection.mutable
import scala.reflect.ClassTag

class RoundContext(
  val annotations: Set[TypeElement],
  val roundEnv: RoundEnvironment,
  val processingEnv: ProcessingEnvironment) {
  implicit private val self = this
  val delegates = mutable.Set.empty[TypeElement]

  def methodAnnotations = annotations.filter(
    _.getAnnotation(classOf[Target]).value.exists(_ == ElementType.METHOD))

  @tailrec final def addDelegateTree(t: TypeElement): Unit = {
    delegates += t
    t.getSuperclass match {
      case _: NoType =>
      case p =>
        addDelegateTree(p.erasure)
    }
  }

  def annotatedElements[T <: Annotation](implicit tag: ClassTag[T]) =
    roundEnv.getElementsAnnotatedWith(tag.runtimeClass.asInstanceOf[Class[T]])

  def elementOf[T](implicit tag: ClassTag[T]) =
    processingEnv.getElementUtils.getTypeElement(tag.runtimeClass.getCanonicalName)
}
