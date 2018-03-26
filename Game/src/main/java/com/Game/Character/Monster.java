package com.Game.Character;

import java.util.ArrayList;

/**
 * Класс монстров
 * @author titaninus
 * @version 1.1
 */
public class Monster extends GameCharacter {
    /** Поле награды получаемой игроком за победу над монстром*/
    public int XPReward;

    /** Поле уровня монстров */
    public int Level;

    /** Поле идентификатора монстар в хранилище монстров
     * @see MonsterStorage
     */
    public int Id;

    /** Поле названия монстра */
    public String Name;

    /**
     * Полный конструктор монстра
     * @param name
     * @param level
     * @param id
     * @param xpReward
     * @param strength
     * @param agility
     * @param intelligence
     * @param wisdom
     * @param luck
     * @param abilities
     */
    public Monster(String name, int level, int id, int xpReward, int strength, int agility, int intelligence, int wisdom, int luck, ArrayList<Integer> abilities) {
        Name = name;
        Level = level;
        Id = id;
        XPReward = xpReward;
        Strength = strength;
        Agility = agility;
        Intelligence = intelligence;
        Wisdom = wisdom;
        Luck = luck;
        Abilities = abilities;
        CurrentHealth = getHealthPoints();
        CurrentMana = getManaPoints();
        CurrentStamina = getStaminaPoints();
    }

    /**
     * Функция, возвращающая копию монстра
     * @return возвращает копиюю монстра
     */
    public Monster Copy() {
        return new Monster(Name, Level, Id, XPReward, Strength, Agility, Intelligence, Wisdom, Luck, Abilities);
    }
}
