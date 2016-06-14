package io.github.zot201.asmhook.test.util;

import com.google.common.truth.AbstractVerb.DelegatedVerb;
import com.google.testing.compile.JavaSourcesSubject;
import com.google.testing.compile.JavaSourcesSubject.SingleSourceAdapter;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

public class Truths {

  /**
   * Adapter for Scala use
   */
  public static DelegatedVerb<SingleSourceAdapter, JavaFileObject> assertAboutJavaSource() {
    return assert_().about(javaSource());
  }

  /**
   * Adapter for Scala use
   */
  public static DelegatedVerb<JavaSourcesSubject, Iterable<? extends JavaFileObject>> assertAboutJavaSources() {
    return assert_().about(javaSources());
  }

}
