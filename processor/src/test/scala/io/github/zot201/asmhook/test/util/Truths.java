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
