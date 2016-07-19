package io.github.zot201.asmhook.processing

import io.github.zot201.asmhook.processing.context.ContextExtension
import io.github.zot201.asmhook.util.processing.JavaPoetExtension

import scala.collection.convert.{DecorateAsJava, ToScalaImplicits}

package object operation extends
  ToScalaImplicits with
  DecorateAsJava with
  ContextExtension with
  JavaPoetExtension
