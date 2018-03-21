import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MonsterStorage {
    public static HashMap<Integer, Monster> Monsters = new HashMap<Integer, Monster>();

    public static HashMap<Integer, ArrayList<Integer>> MonstersByLevel;

    public static void LoadAllMonsters() {
        Monsters = new HashMap<Integer, Monster>();
        Monsters.put(0, new Monster("Weak skeleton", 1, 0, 10, 7, 4, 1, 1, 2));
        MonstersByLevel = new HashMap<Integer, ArrayList<Integer>>();
        for (Monster monster: Monsters.values()) {
            if (!MonstersByLevel.containsKey(monster.Level)) {
                MonstersByLevel.put(monster.Level, new ArrayList<Integer>());
            }
            MonstersByLevel.get(monster.Level).add(monster.Id);
        }
    }
}
