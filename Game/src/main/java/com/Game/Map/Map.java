package com.Game.Map;

import java.util.ArrayList;

/**
 * Класс реализующий карту игры
 * @author titaninus
 * @version 1.0
 */
public class Map {

    /** Клетки карты */
    public ArrayList<ArrayList<Cell>> Cells;

    /** Ширина карты */
    public int Width;

    /** Высота карты */
    public int Height;

    /** Стартовая позиция игрока на карте по горизонтали */
    public int StartX;

    /** Стартовая позиция игрока на карте по вертикали */
    public int StartY;

    /**
     * Конструктор карты
     * @param width - ширина карты
     * @param height - высота карты
     */
    public Map(int width, int height) {
        Width = width;
        Height = height;
        Cells = new ArrayList<ArrayList<Cell>>();
        for (int i = 0; i < Height; ++i) {
            Cells.add(new ArrayList<Cell>());
        }
        StartX = width / 2;
        StartY = height / 2;
    }

}
