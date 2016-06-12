package io.github.zot201.asmhook.test;

import com.google.testing.compile.JavaFileObjects;
import io.github.zot201.asmhook.processing.HookProcessor;
import org.junit.Test;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class HookTest {

  @Test public void helloWorld() {
    assert_().about(javaSource())
        .that(JavaFileObjects.forResource("HelloWorld.java"))
        .processedWith(new HookProcessor())
        .compilesWithoutError();
  }

}
