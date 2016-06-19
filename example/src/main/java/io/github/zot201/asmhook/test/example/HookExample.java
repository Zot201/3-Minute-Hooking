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

import io.github.zot201.asmhook.*;
import io.github.zot201.asmhook.ReturnInAdvance.Condition;
import io.github.zot201.asmhook.test.example.util.MyCustomArmor;
import io.github.zot201.asmhook.test.example.util.MyMod;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class HookExample {

  @ReturnInAdvance(Condition.IF_TRUE)
  public boolean canEnchantItem(@Receiver EnumEnchantmentType type, Item i) {
    return type == MyMod.MY_ENCH_TYPE && (i instanceof ItemSword || i instanceof ItemTool);
  }

  @DeclaredAt(EnumEnchantmentType.class)
  @OnInstanceof(ItemArmor.class)
  public boolean canEnchantItem(@Emit boolean isItemArmor, Object i) {
    return isItemArmor || i instanceof MyCustomArmor;
  }

  @DeclaredAt(EnumEnchantmentType.class)
  @InAdvance
  public final ThreadLocal<Item> canEnchantItem = new ThreadLocal<>();

}
