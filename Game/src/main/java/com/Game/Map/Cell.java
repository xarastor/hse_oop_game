package com.Game.Map;

import java.util.ArrayList;
import com.Game.Character.Monster;
import com.Game.Item.Item;

/**
 * Класс клетки на карте
 * @author titaninus
 * @version 1.0
 */
public class Cell {

    /** Поле типа клетки */
    public CellType Type;

    /** Поле типа клетки */
    public boolean isRevealed = false;

    /** Поле типа клетки */
    public int MonsterId = -1;

    /** Поле типа клетки */
    public ArrayList<Integer> Artifacts = new ArrayList<Integer>();

    /** Поле типа клетки */
    public int X;

    /** Поле типа клетки */
    public int Y;

    /**
     * Конструктор клетки с типом type и расположеним x и y
     * @param type
     * @param x
     * @param y
     */
    public Cell(CellType type, int x, int y) {
        Type = type;
        X = x;
        Y = y;
    }

    /**
     * Добавляет предмет в клетку
     * @param item - предмет для добавления на клетку
     */
    public void AddArtifact(Item item) {
        Artifacts.add(item.Id);
    }

    /**
     * Устанавливает монстра на клетке
     * @param monster - монстер для добавления на клетку
     */
    public void SetMonster(Monster monster) {
        MonsterId = monster.Id;
    }

    /**
     * Добавляет предмет в клетку
     * @param itemId - идентификатор предмета в ItemStorage
     * @see ItemStorage
     */
    public void AddArtifact(Integer itemId) {
        Artifacts.add(itemId);
    }

    /**
     * Устанавливает монстра на клетке
     * @param monsterId - идентификатор монстра в MonsterStorage
     * @see MonsterStorage
     */
    public void SetMonster(Integer monsterId) {
        MonsterId = monsterId;
    }
}
