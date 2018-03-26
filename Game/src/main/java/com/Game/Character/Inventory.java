package com.Game.Character;

import com.Game.Item.Item;
import com.Game.Item.ItemStorage;
import com.Game.Item.ItemType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс инвенторя для предметов
 * @author titaninus
 * @version 1.1
 */
public class Inventory {
    /** Поле владельца инвентаря */
    Player owner;

    /** Поле списка предметов в инвентаре */
    public ArrayList<Integer> items = new ArrayList<Integer>();

    /** Поле предметов в инвентаре разбитых по категориям */
    public HashMap<ItemType, ArrayList<Integer>> ItemsByCategory;

    /** Поле экипированных предметов */
    public int[] EquippedItems; // Helmet, Weapon, Boots, Gloves, Armor, Pants

    /**
     * Конструктор класса
     * @param player - игрок владеющий инвентарем
     */
    public Inventory(Player player) {
        owner = player;
        EquippedItems = new int[ItemType.values().length];
        for (int i = 0; i < EquippedItems.length; i++) {
            EquippedItems[i] = -1;
        }
        ItemsByCategory = new HashMap<ItemType, ArrayList<Integer>>();
        for (ItemType type : ItemType.values()) {
            ItemsByCategory.put(type, new ArrayList<Integer>());
        }
    }

    /**
     * Функция добавления предмета в инвентарь
     * @param Id - идентификатор предмета, который будет помещен в инвентарь
     */
    public void pickup(Integer Id) {
        if (!items.contains(Id)) {
            items.add(Id);
            ItemsByCategory.get(ItemStorage.Items.get(Id).Type).add(Id);
        }
    }

    /**
     * Функция удаления модификаторов предмета с игрока
     * @param Id - идентификатор предмета, модификаторы которого должны быть удалены
     */
    public void removeItemModifiers(Integer Id) {
        for (Modifier modifier : ItemStorage.Items.get(Id).Modifiers) {
            owner.removeModifierExternal(modifier);
        }
    }

    /**
     * Функция добавления модификаторов предмета игроку
     * @param Id - идентификатор предмета, модификаторы которого должны быть добавлены
     */
    public void addItemModifiers(Integer Id) {
        for (Modifier modifier : ItemStorage.Items.get(Id).Modifiers) {
            owner.addModifier(modifier);
        }
    }

    /**
     * Функция отмены экипировки предмета с пользователя
     * @param index - индекс предмета, который должен быть удален
     */
    public void Unequip(Integer index) {
        if (EquippedItems[index] >= 0) {
            removeItemModifiers(EquippedItems[index]);
            EquippedItems[index] = -1;
        }
    }

    /**
     * Функция экипировки предмета
     * @param Id - Идентификатор предмета, который должен быть экипирован
     * @return true - если экипировка прошла успешно, false - иначе
     */
    public boolean Equip(Integer Id) {
        if (items.contains(Id)) {
            Item item = ItemStorage.Items.get(Id);
            int index = item.Type.ordinal();
            Unequip(index);
            EquippedItems[index] = Id;
            addItemModifiers(Id);
            return true;
        } else {
            return false;
        }
    }
}
