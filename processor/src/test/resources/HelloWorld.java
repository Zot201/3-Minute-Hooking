package io.github.zot201.asmhook.test.resource;

import io.github.zot201.asmhook.Hook;
import net.minecraft.enchantment.EnumEnchantmentType;

@Hook
public class HelloWorld {

  static {
    EnumEnchantmentType.values();
  }

}
