package Ability;

import java.util.ArrayList;
import Character.Modifier;

public class Ability {
    public int ManaCost;
    public int StaminaCost;
    public int HealthCost;
    public int Damage;
    public String Name;
    public AbilityType Type;
    public int Id;
    public int Level;
    public ArrayList<Modifier> Modifiers;
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

    @Override
    public String toString() {
        return String.format("Id: %d, Уровень: %d,  Имя: %s, Урон: %d, Стоимость маны:%d, Стоимость выносливости:%d, Стоимость здоровья: %d,  Модификаторы:\n %s\n",
                Id, Level, Name, Damage, ManaCost, StaminaCost, HealthCost, Modifier.ModifiersArrayToString(Modifiers));
    }
}
