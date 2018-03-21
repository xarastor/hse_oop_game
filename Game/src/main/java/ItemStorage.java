import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.*;

import com.google.gson.Gson;

/**
 * Created by Titaninus on 15.03.2018.
 */
public class ItemStorage {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public static HashMap<Item.ItemType, HashMap<Integer, ArrayList<Integer>>> ItemsByCategoryAndLevel;

    public static int MaxLevel = 1;

    public static void LoadAllItems() {
        Items = new HashMap<Integer, Item>();
        Items.put(0, new Item("Плохая Броня", 0, 1, Item.ItemType.Armor, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(Modifier.modifiedPoint.Strength,  1),
                new Modifier(Modifier.modifiedPoint.Agility,  1)
        ))));
        Items.put(1, new Item("Плохой Меч", 1, 1, Item.ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(Modifier.modifiedPoint.Strength,  2),
                new Modifier(Modifier.modifiedPoint.Agility,  1)
        ))));
        Items.put(2, new Item("Плохой Лук", 2, 1, Item.ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(Modifier.modifiedPoint.Agility,  2),
                new Modifier(Modifier.modifiedPoint.Strength,  1)
        ))));
        Items.put(3, new Item("Плохой Посох", 3, 1, Item.ItemType.Weapon, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(Modifier.modifiedPoint.Intelligence,  2),
                new Modifier(Modifier.modifiedPoint.Wisdom,  1)
        ))));
        Items.put(4, new Item("Плохие Штаны", 4, 1, Item.ItemType.Pants, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(Modifier.modifiedPoint.Intelligence,  1),
                new Modifier(Modifier.modifiedPoint.Agility,  1)
        ))));
        Items.put(5, new Item("Плохие Перчатки", 5, 1, Item.ItemType.Gloves, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(Modifier.modifiedPoint.Wisdom,  1),
                new Modifier(Modifier.modifiedPoint.Intelligence,  1)
        ))));
        Items.put(6, new Item("Плохие Ботинки", 6, 1, Item.ItemType.Boots, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(Modifier.modifiedPoint.Intelligence,  10),
                new Modifier(Modifier.modifiedPoint.Wisdom,  10)
        ))));
        Items.put(7, new Item("Плохой Шлем", 7, 1, Item.ItemType.Helmet, new ArrayList<Modifier>(Arrays.asList(
                new Modifier(Modifier.modifiedPoint.Strength,  1),
                new Modifier(Modifier.modifiedPoint.Intelligence,  1)
        ))));
        ItemsByCategoryAndLevel = new HashMap<Item.ItemType, HashMap<Integer, ArrayList<Integer>>>();
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
