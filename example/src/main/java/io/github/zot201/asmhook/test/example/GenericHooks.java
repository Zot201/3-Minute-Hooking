package io.github.zot201.asmhook.test.example;

import io.github.zot201.asmhook.annotation.Condition;
import io.github.zot201.asmhook.annotation.HookInstance;
import io.github.zot201.asmhook.annotation.InAdvance;
import io.github.zot201.asmhook.annotation.parameter.Receiver;
import io.github.zot201.asmhook.test.example.util.MyMod;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

/** Added for comprehensiveness concerning generics */
public class GenericHooks<T extends Item> {

  @HookInstance static final GenericHooks<ItemSword> INSTANCE = new GenericHooks<>();

  @InAdvance(end = Condition.IF_TRUE)
  boolean canEnchantItem(@Receiver EnumEnchantmentType type, T i) {
    return type == MyMod.MY_ENCH_TYPE && (i instanceof ItemSword || i instanceof ItemTool);
  }

  @InAdvance(end = Condition.IF_TRUE)
  <U> boolean canEnchantItem(@Receiver EnumEnchantmentType type, U i) {
    return type == MyMod.MY_ENCH_TYPE && (i instanceof ItemSword || i instanceof ItemTool);
  }

}
