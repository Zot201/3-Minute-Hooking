package io.github.zot201.asmhook.test.example;

import io.github.zot201.asmhook.annotation.HookInstance;

public class MethodHookInstance {

  private static final HookExamples HOOK_EXAMPLES = new HookExamples();

  /** Declare a static accessor for hooks */
  @HookInstance static HookExamples getHookExamples() {
    return HOOK_EXAMPLES;
  }

}
