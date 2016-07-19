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

import com.squareup.javapoet.{JavaFile, TypeSpec}
import io.github.zot201.asmhook.annotation.HookInstance
import io.github.zot201.asmhook.processing.context.RoundContext

class ProcessHookInstance(implicit ctx: RoundContext) {
  for (h <- ctx.annotatedElements[HookInstance]) {
    require(h.getModifiers.contains(Modifier.STATIC), s"$h must be static")

    val hookInfo = h match {
      case _: VariableElement =>
        h.asType
      case e: ExecutableElement =>
        e.getReturnType
    }

    h.getEnclosingElement match {
      case container: TypeElement =>
        val hookType = hookInfo.toElement
        ctx.delegates += container
        ctx.addDelegateTree(hookType)

        val weaverSpec = TypeSpec
          .classBuilder(s"${hookType.getSimpleName}AsmWeaver")
          .addModifiers(Modifier.PUBLIC)
          .build

        JavaFile.builder(container.packageName, weaverSpec)
          .build
          //.writeTo(System.out)
    }
  }

  new GenerateDelegates
}
