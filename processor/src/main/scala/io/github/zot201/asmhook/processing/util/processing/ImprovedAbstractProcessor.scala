package io.github.zot201.asmhook.processing.util.processing

import java.util
import javax.annotation.processing.AbstractProcessor

import scala.collection.JavaConversions._

abstract class ImprovedAbstractProcessor extends AbstractProcessor {

  override def getSupportedAnnotationTypes: util.Set[String] = {
    val supported = getClass.getAnnotation(classOf[SupportedAnnotations])
    if (supported != null) {
      super.getSupportedAnnotationTypes ++ supported.value.view.map(_.getName)
    }
    else {
      super.getSupportedAnnotationTypes
    }
  }

}
