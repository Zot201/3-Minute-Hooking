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

import io.github.zot201.asmhook.Name;
import io.github.zot201.asmhook.OnReturn;
import io.github.zot201.asmhook.Parameters;
import io.github.zot201.asmhook.Receiver;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;

public class EnumEnchantmentTypeExample {

  public void canEnchantItem(@OnReturn boolean canEnchant, @Receiver EnumEnchantmentType type, Item item) {
    // stub
  }

  @Parameters(Item.class)
  public void canEnchantItem(@OnReturn boolean canEnchant, @Receiver EnumEnchantmentType type) {
    // stub
  }

  private boolean isMyEnchantmentType(EnumEnchantmentType type) {
    return type.name().equals("MY_ENCHANTMENT_TYPE");
  }

  @Name("canEnchantItem")
  public boolean myEnchantmentTypeImpl(@Receiver EnumEnchantmentType type) {
    return isMyEnchantmentType(type);
  }

}
