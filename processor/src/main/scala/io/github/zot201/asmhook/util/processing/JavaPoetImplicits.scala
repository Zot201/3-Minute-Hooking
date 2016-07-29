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
