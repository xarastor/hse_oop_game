package Ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import Character.Modifier;
import Character.ModifierPoint;

public class AbilityStorage {
    public static HashMap<Integer, Ability> Abilities = new HashMap<Integer, Ability>();

    public static HashMap<Integer, ArrayList<Integer>> AbilitiesByLevel;

    public static Integer MaxLevel = 1;

    public static void LoadAllAbilities() {
        Abilities = new HashMap<Integer, Ability>();
        Abilities.put(0, new Ability(0,"Простой удар", AbilityType.EnemyImposed, 1,20, 0,  10, 0, new ArrayList<Modifier>()));
        Abilities.put(1, new Ability(1,"Простое благославление", AbilityType.EnemyImposed, 1,1, 0,  10, 0, new ArrayList<Modifier>(
                Arrays.asList(
                        new Modifier(ModifierPoint.HealthRegen, true, 3, 0, 10),
                        new Modifier(ModifierPoint.ManaRegen, true, 3, 0, 10),
                        new Modifier(ModifierPoint.StaminaRegen, true, 3, 0, 10)
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
