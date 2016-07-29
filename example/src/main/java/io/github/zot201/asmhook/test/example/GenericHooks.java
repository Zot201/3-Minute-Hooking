/*
 * Copyright 2016 Zot201
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
