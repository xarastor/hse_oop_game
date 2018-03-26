package com.Game.Ability;

import java.util.ArrayList;
import com.Game.Character.Modifier;

/**
 * Класс реализующий функционал навыков
 * @author titaninus
 * @version 1.1
 */
public class Ability {
    /** Поле стоимости навыка в мане */
    public int ManaCost;

    /** Поле стоимости навыка в выносливости */
    public int StaminaCost;

    /** Поле стоимости навыка в здоровье */
    public int HealthCost;

    /** Поле урона наносимого навыком противнику */
    public int Damage;

    /** Поле имени навыка */
    public String Name;

    /** Поле типа навыка */
    public AbilityType Type;

    /** Поле идентификатора навыка в AbilityStorage
     * @see AbilityStorage#Abilities
     */
    public int Id;

    /** Поле уровня навыка */
    public int Level;

    /** Поле модификаторов навыка */
    public ArrayList<Modifier> Modifiers;

    /**
     * Конструктор навыка
     * @param id
     * @param name
     * @param type
     * @param level
     * @param damage
     * @param manaCost
     * @param staminaCost
     * @param healthCost
     * @param modifiers
     */
    public Ability(int id, String name, AbilityType type, int level, int damage, int manaCost, int staminaCost, int healthCost, ArrayList<Modifier> modifiers) {
        Id = id;
        Name = name;
        Type = type;
        Level = level;
        Damage = damage;
        ManaCost = manaCost;
        StaminaCost = staminaCost;
        HealthCost = healthCost;
        Modifiers = modifiers;
    }

    /**
     * Возвращение строкового представления навыка
     * @return строковое представление навыка
     */
    @Override
    public String toString() {
        return String.format("Id: %d, Уровень: %d,  Имя: %s, Урон: %d, Стоимость маны:%d, Стоимость выносливости:%d, Стоимость здоровья: %d,  Модификаторы:\n %s\n",
                Id, Level, Name, Damage, ManaCost, StaminaCost, HealthCost, Modifier.ModifiersArrayToString(Modifiers));
    }
}
