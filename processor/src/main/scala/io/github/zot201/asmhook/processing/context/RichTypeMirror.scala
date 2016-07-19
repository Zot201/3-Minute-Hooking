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

import java.util
import javax.lang.model.`type`.{DeclaredType, TypeMirror}
import javax.lang.model.element.TypeElement
import javax.lang.model.util.SimpleTypeVisitor6

class RichTypeMirror(val m: TypeMirror) extends AnyVal {
  def toElement(implicit ctx: RoundContext) =
    ctx.processingEnv.getTypeUtils.asElement(m).asInstanceOf[TypeElement] // TODO: Check if this casting safe

  def typeParameters =
    m.accept(new SimpleTypeVisitor6[util.List[_ <: TypeMirror], Unit] {
      override def visitDeclared(t: DeclaredType, p: Unit) = t.getTypeArguments
    }, ())
}
