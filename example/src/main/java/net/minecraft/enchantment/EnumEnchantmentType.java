package net.minecraft.enchantment;

import net.minecraft.item.Item;

public enum EnumEnchantmentType {
  ALL;

  public boolean canEnchantItem(Item itemIn) {
    return false;
  }

}
