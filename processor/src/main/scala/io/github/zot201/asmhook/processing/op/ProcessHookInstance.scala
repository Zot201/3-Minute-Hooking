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
package io.github.zot201.asmhook.processing.op

import javax.lang.model.element.{Modifier, TypeElement}

import com.squareup.javapoet.{ClassName, JavaFile, TypeSpec}
import io.github.zot201.asmhook.HookInstance
import io.github.zot201.asmhook.processing.RoundCtx

import scala.collection.JavaConverters._

object ProcessHookInstance extends Proc {

  override def apply(ctx: RoundCtx): Unit = {
    val hooks = ctx.annotatedElements[HookInstance]

    if (!hooks.isEmpty) {
      ctx.processed = true

      for (h <- hooks.asScala) h.getEnclosingElement match {
        case enc: TypeElement =>
          val name = ClassName.get(enc).peerClass(enc.getSimpleName + "Handler")

          //MethodSpec.methodBuilder()

          val typeSpec = TypeSpec.classBuilder(name)
            .addModifiers(Modifier.PUBLIC)
            .build()

          val javaFile = JavaFile.builder(name.packageName, typeSpec).build()
          javaFile.writeTo(System.out)
      }
    }
  }

}
