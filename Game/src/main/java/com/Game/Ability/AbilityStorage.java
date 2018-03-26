package com.Game.Ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.Game.Character.Modifier;
import com.Game.Character.ModifierPoint;

/**
 * Хранилище навыков, которые используются в игре
 * @author titaninus
 * @version 1.1
 */
public class AbilityStorage {
    /** Поле навыков доступных в игре */
    public static HashMap<Integer, Ability> Abilities = new HashMap<Integer, Ability>();

    /** Поле навыков разбитых по уровням */
    public static HashMap<Integer, ArrayList<Integer>> AbilitiesByLevel;

    /** Поле максимального уровня навыков */
    public static Integer MaxLevel = 1;

    /**
     * Функция, которая загружает все доступные навыки и распределяет их по уровням
     */
    public static void LoadAllAbilities() {
        Abilities = new HashMap<Integer, Ability>();
        Abilities.put(0, new Ability(0,"Простой удар", AbilityType.EnemyImposed, 1,20, 0,  10, 0, new ArrayList<Modifier>()));
        Abilities.put(1, new Ability(1,"Сильный удар", AbilityType.EnemyImposed, 2,60, 0,  30, 0, new ArrayList<Modifier>()));
        Abilities.put(2, new Ability(2,"Мощный удар", AbilityType.EnemyImposed, 3,150, 10,  50, 0, new ArrayList<Modifier>()));

        Abilities.put(3, new Ability(3,"Простой выстрел", AbilityType.EnemyImposed, 1,25, 0,  15, 0, new ArrayList<Modifier>()));
        Abilities.put(4, new Ability(4,"Сильный выстрел", AbilityType.EnemyImposed, 2,75, 0,  30, 0, new ArrayList<Modifier>()));
        Abilities.put(5, new Ability(5,"Мощный выстрел", AbilityType.EnemyImposed, 3,180, 0,  70, 0, new ArrayList<Modifier>()));

        Abilities.put(6, new Ability(6,"Малый снаряд крови", AbilityType.EnemyImposed, 2,120, 100,  10, 30, new ArrayList<Modifier>()));
        Abilities.put(7, new Ability(7,"Снаряд крови", AbilityType.EnemyImposed, 4,200, 200,  10, 50, new ArrayList<Modifier>()));
        Abilities.put(8, new Ability(8,"Мощный снаряд крови", AbilityType.EnemyImposed, 6,350, 400,  20, 100, new ArrayList<Modifier>()));

        Abilities.put(9, new Ability(9,"Слабое кровотечение", AbilityType.EnemyImposed, 2,0, 50,  0, 0, new ArrayList<Modifier>(
                Arrays.asList(
                        new Modifier(ModifierPoint.HealthRegen, true, 4, 0, -10),
                        new Modifier(ModifierPoint.StaminaRegen, true, 4, 0, -10)
                )
        )));
        Abilities.put(10, new Ability(10,"Кровотечение", AbilityType.EnemyImposed, 4,0, 100,  0, 0, new ArrayList<Modifier>(
                Arrays.asList(
                        new Modifier(ModifierPoint.HealthRegen, true, 4, 0, -30),
                        new Modifier(ModifierPoint.StaminaRegen, true, 4, 0, -30)
                )
        )));
        Abilities.put(11, new Ability(11,"Сильное кровотечение", AbilityType.EnemyImposed, 6,0, 300,  0, 0, new ArrayList<Modifier>(
                Arrays.asList(
                        new Modifier(ModifierPoint.HealthRegen, true, 4, 0, -70),
                        new Modifier(ModifierPoint.StaminaRegen, true, 4, 0, -70)
                )
        )));

        Abilities.put(12, new Ability(12,"Стрела тьмы", AbilityType.EnemyImposed, 2,60, 50,  0, 0, new ArrayList<Modifier>()));
        Abilities.put(13, new Ability(13,"Снаряд тьмы", AbilityType.EnemyImposed, 4,200, 150,  0, 0, new ArrayList<Modifier>()));
        Abilities.put(14, new Ability(14,"Шар тьмы", AbilityType.EnemyImposed, 6,400, 300,  0, 0, new ArrayList<Modifier>()));

        Abilities.put(15, new Ability(15,"Простое благославление", AbilityType.SelfImposed, 1,0, 50,  0, 0, new ArrayList<Modifier>(
                Arrays.asList(
                        new Modifier(ModifierPoint.HealthRegen, true, 3, 0, 10),
                        new Modifier(ModifierPoint.ManaRegen, true, 3, 0, 10),
                        new Modifier(ModifierPoint.StaminaRegen, true, 3, 0, 10)
                )
        )));
        Abilities.put(16, new Ability(16,"Благославление", AbilityType.SelfImposed, 2,0, 100,  0, 0, new ArrayList<Modifier>(
                Arrays.asList(
                        new Modifier(ModifierPoint.HealthRegen, true, 4, 0, 20),
                        new Modifier(ModifierPoint.ManaRegen, true, 4, 0, 20),
                        new Modifier(ModifierPoint.StaminaRegen, true, 4, 0, 20)
                )
        )));
        Abilities.put(17, new Ability(17,"Сильное благославление", AbilityType.SelfImposed, 4,0, 250,  0, 0, new ArrayList<Modifier>(
                Arrays.asList(
                        new Modifier(ModifierPoint.HealthRegen, true, 5, 0, 40),
                        new Modifier(ModifierPoint.ManaRegen, true, 5, 0, 40),
                        new Modifier(ModifierPoint.StaminaRegen, true, 5, 0, 40)
                )
        )));
        Abilities.put(18, new Ability(18,"Слабое усиление", AbilityType.SelfImposed, 3,0, 10,  60, 0, new ArrayList<Modifier>(
                Arrays.asList(
                        new Modifier(ModifierPoint.HealthRegen, true, 3, 0, 20),
                        new Modifier(ModifierPoint.ManaRegen, true, 3, 0, 20),
                        new Modifier(ModifierPoint.StaminaRegen, true, 3, 0, 20)
                )
        )));
        Abilities.put(19, new Ability(19,"Простое усиление", AbilityType.SelfImposed, 4,0, 20,  160, 0, new ArrayList<Modifier>(
                Arrays.asList(
                        new Modifier(ModifierPoint.HealthRegen, true, 4, 0, 40),
                        new Modifier(ModifierPoint.ManaRegen, true, 4, 0, 40),
                        new Modifier(ModifierPoint.StaminaRegen, true, 4, 0, 40)
                )
        )));
        Abilities.put(20, new Ability(20,"Сильное усиление", AbilityType.SelfImposed, 5,0, 30,  300, 0, new ArrayList<Modifier>(
                Arrays.asList(
                        new Modifier(ModifierPoint.HealthRegen, true, 5, 0, 60),
                        new Modifier(ModifierPoint.ManaRegen, true, 5, 0, 60),
                        new Modifier(ModifierPoint.StaminaRegen, true, 5, 0, 60)
                )
        )));
        AbilitiesByLevel = new HashMap<Integer, ArrayList<Integer>>();
        for (Ability ability: Abilities.values()) {
            if (ability.Level > MaxLevel) {
                MaxLevel = ability.Level;
            }
            if (!AbilitiesByLevel.containsKey(ability.Level)) {
                AbilitiesByLevel.put(ability.Level, new ArrayList<Integer>());
            }
            AbilitiesByLevel.get(ability.Level).add(ability.Id);
        }
    }
}
