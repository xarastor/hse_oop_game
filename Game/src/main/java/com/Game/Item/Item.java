package com.Game.Item;

import com.Game.Manager.GameManager;
import com.Game.Character.Modifier;

import java.util.ArrayList;

/**
 * Класс предметов
 * @author titaninus
 * @version 1.0
 */
public class Item implements IInventorable {

    /** Модификаторы предмета */
    public ArrayList<Modifier> Modifiers;

    /** Тип предмета */
    public ItemType Type;

    /** Название предмета */
    public String Name;

    /** Идентификатор предмета в ItemStorage */
    public int Id;

    /** Уровень предмета */
    public int ItemLevel;

    /**
     * Полный конструктор класса
     * @param name
     * @param id
     * @param level
     * @param type
     * @param description - модификаторы предмета
     */
    public Item(String name, int id, int level, ItemType type, ArrayList<Modifier> description) {
        Modifiers = description;
        Name = name;
        Id = id;
        Type = type;
        ItemLevel = level;
    }

    /**
     * Реализация интерфейса IInventorable
     * @see IInventorable
     */
    public void pickup() {
        GameManager.getInstance().player.getInventory().pickup(Id);
    }

    /**
     * Преобразование предмета в строковое представление
     * @return возвращает строковое представление предмета
     */
    @Override
    public String toString() {
        return String.format("Id: %d, Уровень: %d,  Имя: %s, Модификаторы:\n %s\n", Id, ItemLevel, Name, Modifier.ModifiersArrayToString(Modifiers));
    }
}
