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
        Items.put(0, new Item("Useful Armor", 0, 1, Item.ItemType.Armor, new ArrayList<Modifier>(Arrays.asList(
               new Modifier(Modifier.modifiedPoint.Strength,  10),
               new Modifier(Modifier.modifiedPoint.Agility,  10)
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
