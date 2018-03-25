package Character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MonsterStorage {
    public static HashMap<Integer, Monster> Monsters = new HashMap<Integer, Monster>();

    public static HashMap<Integer, ArrayList<Integer>> MonstersByLevel;

    public static Integer MaxLevel = 1;

    public static void LoadAllMonsters() {
        Monsters = new HashMap<Integer, Monster>();
        Monsters.put(0, new Monster("Слабый скелет", 1, 0, 10, 7, 4, 1, 1, 2, new ArrayList<Integer>(Arrays.asList(
                0
        ))));
        Monsters.put(1, new Monster("Скелет-лучник", 1, 1, 20, 7, 4, 1, 1, 2, new ArrayList<Integer>(Arrays.asList(
                0, 5
        ))));
        Monsters.put(2, new Monster("Сильный скелет-маг", 2, 2, 100, 25, 25, 10, 10, 4, new ArrayList<Integer>(Arrays.asList(
                4, 2
        ))));
        MonstersByLevel = new HashMap<Integer, ArrayList<Integer>>();
        for (Monster monster: Monsters.values()) {
            if (monster.Level > MaxLevel) {
                MaxLevel = monster.Level;
            }
            if (!MonstersByLevel.containsKey(monster.Level)) {
                MonstersByLevel.put(monster.Level, new ArrayList<Integer>());
            }
            MonstersByLevel.get(monster.Level).add(monster.Id);
        }
    }
}
