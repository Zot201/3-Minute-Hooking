package io.github.zot201.asmhook.processing

import scala.collection.convert.{DecorateAsJava, ToScalaImplicits}

package object context extends
  ToScalaImplicits with
  DecorateAsJava with
  RoundContextImplicits
