package io.github.zot201.asmhook.test.resource;

import io.github.zot201.asmhook.*;
import net.minecraft.enchantment.EnumEnchantmentType;

public class HookExample {

  public void canEnchantItem(@OnReturn boolean canEnchant, @Receiver EnumEnchantmentType type, Item item) {
    // stub
  }

  @Parameters(Item.class)
  public void canEnchantItem(@OnReturn boolean canEnchant, @Receiver EnumEnchantmentType type) {
    // stub
  }

}
