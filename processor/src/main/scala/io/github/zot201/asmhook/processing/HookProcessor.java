package io.github.zot201.asmhook.processing;

import com.google.auto.service.AutoService;
import io.github.zot201.asmhook.OnReturn;
import io.github.zot201.asmhook.Receiver;
import io.github.zot201.asmhook.processing.util.processing.ImprovedAbstractProcessor;
import io.github.zot201.asmhook.processing.util.processing.SupportedAnnotations;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotations({OnReturn.class, Receiver.class})
public class HookProcessor extends ImprovedAbstractProcessor {

  @Override public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    System.out.println(roundEnv); // TODO
    return false;
  }

}
