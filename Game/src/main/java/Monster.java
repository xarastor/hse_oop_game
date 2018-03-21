/**
 * Created by titaninus on 14.03.18.
 */
public class Monster extends Character {
    public int XPReward = 10;
    public int Level = 1;
    public int Id;
    public String Name;

    public Monster(String name, int level, int id, int xpReward, int strength, int agility, int intelligence, int wisdom, int luck) {
        Name = name;
        Level = level;
        Id = id;
        XPReward = xpReward;
        Strength = strength;
        Agility = agility;
        Intelligence = intelligence;
        Wisdom = wisdom;
        Luck = luck;
    }
}
