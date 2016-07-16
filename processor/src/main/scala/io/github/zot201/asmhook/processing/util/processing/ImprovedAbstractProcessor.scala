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
package io.github.zot201.asmhook.processing.util.processing

import java.util
import javax.annotation.processing.AbstractProcessor

import scala.collection.JavaConverters._

abstract class ImprovedAbstractProcessor extends AbstractProcessor {

  override def getSupportedAnnotationTypes: util.Set[String] = {
    val supported = getClass.getAnnotation(classOf[SupportedAnnotations])

    if (supported != null) {
      (Set.newBuilder[String]
        ++= supported.value.view.map(_.getName)
        ++= super.getSupportedAnnotationTypes.asScala)
        .result.asJava
    }
    else {
      super.getSupportedAnnotationTypes
    }
  }

}
