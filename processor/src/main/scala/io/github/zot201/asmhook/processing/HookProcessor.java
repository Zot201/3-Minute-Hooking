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
import io.github.zot201.asmhook.*;
import io.github.zot201.asmhook.parameter.*;
import io.github.zot201.asmhook.processing.op.ProcessHookInstance;
import io.github.zot201.asmhook.processing.util.processing.ImprovedAbstractProcessor;
import io.github.zot201.asmhook.processing.util.processing.SupportedAnnotations;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotations({HookInstance.class, Name.class, ParameterTypes.class, DeclaredAt.class,
    InAdvance.class, BeforeReturn.class, BeforeInvoke.class, AfterInstanceof.class,
    Receiver.class, Arg.class, Emit.class, LoadSelf.class, LoadArg.class})
public class HookProcessor extends ImprovedAbstractProcessor {

  @Override public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    RoundCtx ctx = new RoundCtx(roundEnv, processingEnv);
    ProcessHookInstance.apply(ctx);
    return ctx.processed();
  }

}
