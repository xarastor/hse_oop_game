package com.Game.Character;

import java.util.ArrayList;

public class Monster extends GameCharacter {
    public int XPReward;
    public int Level;
    public int Id;
    public String Name;

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
        CurrentMana = getManaPoints() / 2;
        CurrentStamina = getStaminaPoints() / 2;
    }

    public Monster Copy() {
        return new Monster(Name, Level, Id, XPReward, Strength, Agility, Intelligence, Wisdom, Luck, Abilities);
    }
}
