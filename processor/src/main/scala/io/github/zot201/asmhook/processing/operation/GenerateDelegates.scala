package io.github.zot201.asmhook.processing.operation

import io.github.zot201.asmhook.processing.context.RoundCtx

class GenerateDelegates(implicit val ctx: RoundCtx) {
  println(ctx.delegates)
}
