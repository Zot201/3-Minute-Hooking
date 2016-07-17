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

import javax.lang.model.element.{ExecutableElement, Modifier, TypeElement, VariableElement}

import com.squareup.javapoet.{ClassName, JavaFile, MethodSpec, TypeSpec}
import io.github.zot201.asmhook.HookInstance
import io.github.zot201.asmhook.processing.RoundCtx

import scala.collection.JavaConverters._

object ProcessHookInstance extends Proc {

  override def apply(ctx: RoundCtx): Unit = {
    val hookProviders = ctx.annotatedElements[HookInstance]

    if (!hookProviders.isEmpty) {
      ctx.processed = true

      for (h <- hookProviders.asScala) {
        require(h.getModifiers.contains(Modifier.STATIC))

        val hookInfo = h match {
          case _: VariableElement =>
            (h.asType(), h.getSimpleName)
          case e: ExecutableElement =>
            require(!h.getSimpleName.contentEquals("<init>"))
            (e.getReturnType, s"${h.getSimpleName}()")
          case _ =>
            throw new IllegalArgumentException(h.toString)
        }

        h.getEnclosingElement match {
          case container: TypeElement =>
            val hookType = ctx.getElement(hookInfo._1)
            val weaverName = ClassName.get(
              ClassName.get(container).packageName,
              s"${hookType.getSimpleName}Weaver")

            val weaverSpec = TypeSpec.classBuilder(weaverName)
              .addModifiers(Modifier.PUBLIC)
              .addMethod(MethodSpec.methodBuilder("delegate")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(ClassName.get(hookType))
                .addStatement("return $T.$L", hookType, hookInfo._2)
                .build())
              .build()

            JavaFile.builder(weaverName.packageName, weaverSpec)
              .build()
              .writeTo(System.out)
        }
      }
    }
  }

}
