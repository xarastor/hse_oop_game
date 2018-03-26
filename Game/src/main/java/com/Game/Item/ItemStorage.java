package com.Game.Item;

import java.util.*;
import com.Game.Character.Modifier;
import com.Game.Character.ModifierPoint;


/**
 * Хранилище предметов
 * @author titaninus
 * @version 1.0
 */
public class ItemStorage {

    /** Предметы доступные в игре */
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    /** Предметы сгрупированные по типу и уровню */
    public static HashMap<ItemType, HashMap<Integer, ArrayList<Integer>>> ItemsByCategoryAndLevel;

    /** Максимальный уровень предметов в игре */
    public static int MaxLevel = 1;

    /**
     * Загрузка всех предметов в игре и распределение их по типам и уровням
     */
    public static void LoadAllItems() {
        Items = new HashMap<Integer, Item>();
        Items.put(0, new Item("Плохая Броня", 0, 1, ItemType.Armor, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Strength,  1),
                new Modifier(ModifierPoint.Agility,  1)
        ))));
        Items.put(1, new Item("Плохой Меч", 1, 1, ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Strength,  2),
                new Modifier(ModifierPoint.Agility,  1)
        ))));
        Items.put(2, new Item("Плохой Лук", 2, 1, ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Agility,  2),
                new Modifier(ModifierPoint.Strength,  1)
        ))));
        Items.put(3, new Item("Плохой Посох", 3, 1, ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Intelligence,  2),
                new Modifier(ModifierPoint.Wisdom,  1)
        ))));
        Items.put(4, new Item("Плохие Штаны", 4, 1, ItemType.Pants, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Intelligence,  1),
                new Modifier(ModifierPoint.Agility,  1)
        ))));
        Items.put(5, new Item("Плохие Перчатки", 5, 1, ItemType.Gloves, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Wisdom,  1),
                new Modifier(ModifierPoint.Intelligence,  1)
        ))));
        Items.put(6, new Item("Плохие Ботинки", 6, 1, ItemType.Boots, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Intelligence,  2),
                new Modifier(ModifierPoint.Wisdom,  2)
        ))));
        Items.put(7, new Item("Плохой Шлем", 7, 1, ItemType.Helmet, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Strength,  1),
                new Modifier(ModifierPoint.Intelligence,  1)
        ))));
        Items.put(8, new Item("Нормальная Броня", 8, 2, ItemType.Armor, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Strength,  6),
                new Modifier(ModifierPoint.Agility,  6)
        ))));
        Items.put(9, new Item("Нормальный Меч", 9, 2, ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Strength,  6),
                new Modifier(ModifierPoint.Agility,  3)
        ))));
        Items.put(10, new Item("Нормальный Лук", 10, 2, ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Agility,  6),
                new Modifier(ModifierPoint.Strength,  3)
        ))));
        Items.put(11, new Item("Нормальный Посох", 11, 2, ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Intelligence,  6),
                new Modifier(ModifierPoint.Wisdom,  3)
        ))));
        Items.put(12, new Item("Нормальные Штаны", 12, 2, ItemType.Pants, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Intelligence,  3),
                new Modifier(ModifierPoint.Agility,  3)
        ))));
        Items.put(13, new Item("Нормальные Перчатки", 13, 2, ItemType.Gloves, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Wisdom,  3),
                new Modifier(ModifierPoint.Intelligence,  3)
        ))));
        Items.put(14, new Item("Нормальные Ботинки", 14, 2, ItemType.Boots, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Intelligence,  6),
                new Modifier(ModifierPoint.Wisdom,  6)
        ))));
        Items.put(15, new Item("Нормальный Шлем", 15, 2, ItemType.Helmet, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Strength,  3),
                new Modifier(ModifierPoint.Intelligence,  3)
        ))));
        Items.put(16, new Item("Редкая Броня", 16, 3, ItemType.Armor, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Strength,  12),
                new Modifier(ModifierPoint.Agility,  12)
        ))));
        Items.put(17, new Item("Редкий Меч", 17, 3, ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Strength,  12),
                new Modifier(ModifierPoint.Agility,  6)
        ))));
        Items.put(18, new Item("Редкий Лук", 18, 3, ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Agility,  12),
                new Modifier(ModifierPoint.Strength,  6)
        ))));
        Items.put(19, new Item("Редкий Посох", 19, 3, ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Intelligence,  12),
                new Modifier(ModifierPoint.Wisdom,  6)
        ))));
        Items.put(20, new Item("Редкие Штаны", 20, 3, ItemType.Pants, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Intelligence,  6),
                new Modifier(ModifierPoint.Agility,  6)
        ))));
        Items.put(21, new Item("Редкие Перчатки", 21, 3, ItemType.Gloves, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Wisdom,  6),
                new Modifier(ModifierPoint.Intelligence,  6)
        ))));
        Items.put(22, new Item("Редкие Ботинки", 22, 3, ItemType.Boots, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Intelligence,  6),
                new Modifier(ModifierPoint.Wisdom,  6)
        ))));
        Items.put(23, new Item("Редкий Шлем", 23, 3, ItemType.Helmet, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Strength,  6),
                new Modifier(ModifierPoint.Intelligence,  6)
        ))));
        Items.put(24, new Item("Эпическая Броня", 24, 4, ItemType.Armor, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Strength,  25),
                new Modifier(ModifierPoint.Agility,  25)
        ))));
        Items.put(25, new Item("Эпический Меч", 25, 4, ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Strength,  25),
                new Modifier(ModifierPoint.Agility,  15)
        ))));
        Items.put(26, new Item("Эпический Лук", 26, 4, ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Agility,  25),
                new Modifier(ModifierPoint.Strength,  15)
        ))));
        Items.put(27, new Item("Эпический Посох", 27, 4, ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Intelligence,  25),
                new Modifier(ModifierPoint.Wisdom,  15)
        ))));
        Items.put(28, new Item("Эпические Штаны", 28, 4, ItemType.Pants, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Intelligence,  15),
                new Modifier(ModifierPoint.Agility,  15)
        ))));
        Items.put(29, new Item("Эпические Перчатки", 29, 4, ItemType.Gloves, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Wisdom,  15),
                new Modifier(ModifierPoint.Intelligence,  15)
        ))));
        Items.put(30, new Item("Эпические Ботинки", 30, 4, ItemType.Boots, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Intelligence,  15),
                new Modifier(ModifierPoint.Wisdom,  15)
        ))));
        Items.put(31, new Item("Эпический Шлем", 31, 4, ItemType.Helmet, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(ModifierPoint.Strength,  15),
                new Modifier(ModifierPoint.Intelligence,  15)
        ))));
        ItemsByCategoryAndLevel = new HashMap<ItemType, HashMap<Integer, ArrayList<Integer>>>();
        for (Item item: Items.values()) {
            if (!ItemsByCategoryAndLevel.containsKey(item.Type)) {
                ItemsByCategoryAndLevel.put(item.Type, new HashMap<Integer, ArrayList<Integer>>());
            }
            if (!ItemsByCategoryAndLevel.get(item.Type).containsKey(item.ItemLevel)) {
                if (item.ItemLevel > MaxLevel) {
                    MaxLevel = item.ItemLevel;
                }
                ItemsByCategoryAndLevel.get(item.Type).put(item.ItemLevel, new ArrayList<Integer>());
            }
            ItemsByCategoryAndLevel.get(item.Type).get(item.ItemLevel).add(item.Id);
        }
    }
}
