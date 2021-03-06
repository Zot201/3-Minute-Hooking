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
package io.github.zot201.asmhook.processing;

import com.google.auto.service.AutoService;
import io.github.zot201.asmhook.annotation.*;
import io.github.zot201.asmhook.annotation.parameter.*;
import io.github.zot201.asmhook.processing.context.RoundContext;
import io.github.zot201.asmhook.processing.operation.ProcessHookInstance;
import io.github.zot201.asmhook.util.processing.ImprovedAbstractProcessor;
import io.github.zot201.asmhook.util.processing.SupportedAnnotations;
import scala.collection.JavaConverters;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotations({HookInstance.class, Name.class, ParameterTypes.class, DeclaredAt.class,
    InAdvance.class, BeforeReturn.class, BeforeInvoke.class, AfterInstanceof.class,
    Receiver.class, Arg.class, Result.class, LoadSelf.class, LoadArg.class})
public class AsmHookProcessor extends ImprovedAbstractProcessor {

  @Override public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    new ProcessHookInstance(new RoundContext(
        JavaConverters.asScalaSet(annotations).toSet(), roundEnv, processingEnv));
    return true;
  }

}
