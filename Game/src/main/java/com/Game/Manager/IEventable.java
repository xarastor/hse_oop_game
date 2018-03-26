package com.Game.Manager;

public interface IEventable {
    void onGlobalTurn();

    void onBattleTurn();

    void onBattleStart();

    void onBattleEnd();
}
