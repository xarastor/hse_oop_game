package com.Game.Manager;

/**
 * Интерфейс реализующий события игры
 * @author titaninus
 * @version 1.0
 */
public interface IEventable {
    /**
     * Ход на карте
     */
    void onGlobalTurn();

    /**
     * Ход в битве
     */
    void onBattleTurn();

    /**
     * Начало битвы
     */
    void onBattleStart();

    /**
     * Конец битвы
     */
    void onBattleEnd();
}
