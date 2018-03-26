package com.Game.Ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.Game.Character.Modifier;
import com.Game.Character.ModifierPoint;

public class AbilityStorage {
    public static HashMap<Integer, Ability> Abilities = new HashMap<Integer, Ability>();

    public static HashMap<Integer, ArrayList<Integer>> AbilitiesByLevel;

    public static Integer MaxLevel = 1;

    public static void LoadAllAbilities() {
        Abilities = new HashMap<Integer, Ability>();
        Abilities.put(0, new Ability(0,"Простой удар", AbilityType.EnemyImposed, 1,20, 0,  10, 0, new ArrayList<Modifier>()));
        Abilities.put(1, new Ability(1,"Простое благославление", AbilityType.SelfImposed, 1,0, 50,  0, 0, new ArrayList<Modifier>(
                Arrays.asList(
                        new Modifier(ModifierPoint.HealthRegen, true, 3, 0, 10),
                        new Modifier(ModifierPoint.ManaRegen, true, 3, 0, 10),
                        new Modifier(ModifierPoint.StaminaRegen, true, 3, 0, 10)
                )
        )));
        Abilities.put(2, new Ability(2,"Сильный удар", AbilityType.EnemyImposed, 2,60, 0,  30, 0, new ArrayList<Modifier>()));
        Abilities.put(3, new Ability(3,"Мощный удар", AbilityType.EnemyImposed, 3,150, 10,  50, 0, new ArrayList<Modifier>()));
        Abilities.put(4, new Ability(4,"Малый снаряд крови", AbilityType.EnemyImposed, 2,120, 50,  10, 30, new ArrayList<Modifier>()));
        Abilities.put(5, new Ability(5,"Простой выстрел", AbilityType.EnemyImposed, 1,25, 0,  15, 0, new ArrayList<Modifier>()));
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
