package io.github.zot201.asmhook.test

import com.google.testing.compile.JavaFileObjects
import io.github.zot201.asmhook.processing.HookProcessor
import io.github.zot201.asmhook.test.util.Truths._
import org.junit.Test

import scala.collection.JavaConversions._

class HookTest {

  def dummy(file: String) = JavaFileObjects.forResource(s"dummy/$file.java")
  def root(file: String) = JavaFileObjects.forResource(s"$file.java")

  val itemRes = Set(dummy("Item"))
  val enumEnchantmentTypeRes = itemRes + dummy("EnumEnchantmentType")
  val helloWorldRes = enumEnchantmentTypeRes + root("HelloWorld")

  @Test def helloWorld(): Unit = {
    assertAboutJavaSources
      .that(helloWorldRes)
      .processedWith(new HookProcessor)
      .compilesWithoutError()
  }

}
