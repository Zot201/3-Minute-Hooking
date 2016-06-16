package io.github.zot201.asmhook.processing.util.processing

import java.util

import scala.collection.JavaConversions._

abstract class ImprovedAbstractProcessor extends javax.annotation.processing.AbstractProcessor {

  override def getSupportedAnnotationTypes: util.Set[String] = {
    val supported = getClass.getAnnotation(classOf[SupportedAnnotations])
    if (supported != null) {
      super.getSupportedAnnotationTypes ++ supported.value.map(_.getName)
    }
    else {
      super.getSupportedAnnotationTypes
    }
  }

}
