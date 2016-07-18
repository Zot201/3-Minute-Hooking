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
package io.github.zot201.asmhook.processing.operation

import javax.lang.model.element.{ExecutableElement, Modifier, TypeElement, VariableElement}

import com.squareup.javapoet.{ClassName, JavaFile, TypeSpec}
import io.github.zot201.asmhook.annotation.HookInstance
import io.github.zot201.asmhook.processing.context.RoundCtx

import scala.collection.JavaConverters._

class ProcessHookInstance(implicit val ctx: RoundCtx) {
  for (h <- ctx.annotatedElements[HookInstance].asScala) {
    require(h.getModifiers.contains(Modifier.STATIC))

    val hookInfo = h match {
      case _: VariableElement =>
        h.asType
      case e: ExecutableElement =>
        e.getReturnType
    }

    h.getEnclosingElement match {
      case container: TypeElement =>
        val hookType = hookInfo.toElement
        ctx.addDelegateTree(hookType)

        val weaverName = ClassName.get(
          ClassName.get(container).packageName,
          s"${hookType.getSimpleName}Weaver")

        val weaverSpec = TypeSpec.classBuilder(weaverName)
          .addModifiers(Modifier.PUBLIC)
          .build

        JavaFile.builder(weaverName.packageName, weaverSpec)
          .build
          .writeTo(System.out)
    }
  }

  new GenerateDelegates
}
