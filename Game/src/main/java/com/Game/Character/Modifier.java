package com.Game.Character;

import java.util.ArrayList;

/**
 * Класс реализующий функционал модификаторов
 * @author titaninus
 * @version 1.1
 */
public class Modifier {

    /** Поле типа модификатора */
    public ModifierPoint type;

    /** Поле временности модификатора */
    public boolean isTemporary;

    /** Поле длительности модификатора в бою */
    public int durationBattle;

    /** Поле длительности модификатора на карте */
    public int durationGlobal;

    /** Поле значения модификатора */
    public int value;

    /*
    * Completly full constructor for modifiers
     */

    /**
     * Конструктор модификатора с полным описанием параметров
     * @param mType
     * @param temp
     * @param dBatle
     * @param dGlobal
     * @param val
     */
    public Modifier(ModifierPoint mType, boolean temp, int dBatle, int dGlobal, int val) {
        type = mType;
        isTemporary = temp;
        durationBattle = dBatle;
        durationGlobal = dGlobal;
        value = val;
    }

    /*
    * Constructor for Items
     */

    /**
     * Конструктор модификатора для предметов
     * @param mType - тип модификатора
     * @param val - значение модификатора
     */
    public Modifier(ModifierPoint mType, int val) {
        type = mType;
        isTemporary = false;
        value = val;
    }

    /**
     * Статическая функция возвращающая копию поданного модификатора
     * @param prototype - модификатор, который необходимо скопировать
     * @return возвращает копию модификатора
     */
    public static Modifier Copy(Modifier prototype) {
        return new Modifier(prototype.type, prototype.isTemporary, prototype.durationBattle, prototype.durationGlobal, prototype.value);
    }

    /**
     * Функция, которая возвращает строковое представление модификатора
     * @return возвращает строковое представление модификаторв
     */
    @Override
    public String toString() {
        if (isTemporary) {
            return String.format("Временный модификатор с типом %s, длительность в бою: %d, длительность на карте: %d, значение: %d; ", type.toString(), durationBattle, durationGlobal, value);
        } else {
            return String.format("Постоянный модификатор с типом %s, значение: %d", type.toString(), value);
        }
    }

    /**
     * Статическая функция преобразующая массив модификаторов в строковое представление
     * @param modifiers - модификаторы, которые надо преобразовать
     * @return возвращает строковое представление модификаторов
     */
    public static String ModifiersArrayToString(ArrayList<Modifier> modifiers) {
        String res = "";
        for(Modifier modifier : modifiers) {
            res += "\t" + modifier.toString() + "\n";
        }
        return res;
    }
}
