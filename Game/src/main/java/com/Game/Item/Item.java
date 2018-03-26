package com.Game.Item;

import com.Game.Manager.GameManager;
import com.Game.Character.Modifier;

import java.util.ArrayList;

/**
 * Created by Titaninus on 15.03.2018.
 */
public class Item implements IInventorable {


    public ArrayList<Modifier> Modifiers;
    public ItemType Type;
    public String Name;
    public int Id;
    public int ItemLevel;

    public Item(String name, int id, int level, ItemType type, ArrayList<Modifier> description) {
        Modifiers = description;
        Name = name;
        Id = id;
        Type = type;
        ItemLevel = level;
    }

    public void pickup() {
        GameManager.getInstance().player.getInventory().pickup(Id);
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Уровень: %d,  Имя: %s, Модификаторы:\n %s\n", Id, ItemLevel, Name, Modifier.ModifiersArrayToString(Modifiers));
    }
}
