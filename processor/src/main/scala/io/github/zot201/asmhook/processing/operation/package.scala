package io.github.zot201.asmhook.processing

import io.github.zot201.asmhook.processing.context.RoundContextImplicits
import io.github.zot201.asmhook.util.processing.JavaPoetImplicits

import scala.collection.convert.{DecorateAsJava, ToScalaImplicits}

package object operation extends
  ToScalaImplicits with
  DecorateAsJava with
  RoundContextImplicits with
  JavaPoetImplicits
