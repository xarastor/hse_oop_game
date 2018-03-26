package com.Game.Character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MonsterStorage {
    /** Поле монстров присутствующих в игре */
    public static HashMap<Integer, Monster> Monsters = new HashMap<Integer, Monster>();

    /** Поле монстров присутствующих в игре разбитых по уровням */
    public static HashMap<Integer, ArrayList<Integer>> MonstersByLevel;

    /** Поле максимального уровня монстров в игре (необходимо для автоматической генерации карты)
     *@see Map
     */
    public static Integer MaxLevel = 1;

    /**
     * Функция загружающая всех монстров в игру и разбивающая их по уровням
     */
    public static void LoadAllMonsters() {
        Monsters = new HashMap<Integer, Monster>();
        Monsters.put(0, new Monster("Слабый скелет", 1, 0, 10, 15, 10, 1, 1, 2, new ArrayList<Integer>(Arrays.asList(
                0
        ))));
        Monsters.put(1, new Monster("Слабый скелет-лучник", 1, 1, 20, 10, 15, 1, 1, 2, new ArrayList<Integer>(Arrays.asList(
                0, 3
        ))));
        Monsters.put(2, new Monster("Скелет", 2, 2, 100, 20, 15, 2, 2, 4, new ArrayList<Integer>(Arrays.asList(
                0, 1
        ))));
        Monsters.put(3, new Monster("Скелет-лучник", 2, 3, 200, 15, 20, 2, 2, 4, new ArrayList<Integer>(Arrays.asList(
                0, 1, 3, 4
        ))));
        Monsters.put(4, new Monster("Скелет-маг", 3, 4, 200, 10, 10, 35, 35, 6, new ArrayList<Integer>(Arrays.asList(
                1, 6, 9
        ))));
        Monsters.put(5, new Monster("Сильный скелет-маг", 4, 5, 1000, 30, 30, 50, 50, 8, new ArrayList<Integer>(Arrays.asList(
                1, 6, 7, 9, 10
        ))));
        Monsters.put(6, new Monster("Слабый зомби", 2, 6, 50, 25, 10, 5, 5, 6, new ArrayList<Integer>(Arrays.asList(
                0, 1, 9
        ))));
        Monsters.put(7, new Monster("Зомби", 3, 7, 150, 35, 20, 15, 15, 6, new ArrayList<Integer>(Arrays.asList(
                0, 1, 9, 10
        ))));
        Monsters.put(8, new Monster("Рыцарь-смерти", 5, 8, 1000, 50, 50, 25, 25, 6, new ArrayList<Integer>(Arrays.asList(
                0, 1, 2, 9, 10, 11, 12, 19
        ))));
        Monsters.put(9, new Monster("Лич", 6, 9, 1000, 25, 25, 50, 50, 6, new ArrayList<Integer>(Arrays.asList(
                0, 1, 2, 6, 7, 8, 12, 13, 15, 19
        ))));
        Monsters.put(10, new Monster("Дракон", 7, 10, 3000, 100, 100, 50, 50, 20, new ArrayList<Integer>(Arrays.asList(
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 17, 20
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
