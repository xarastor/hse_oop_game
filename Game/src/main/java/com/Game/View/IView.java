package com.Game.View;

import com.Game.Ability.Ability;
import com.Game.Character.Monster;

import java.util.ArrayList;

public interface IView {

    void Start();

    void WinGame();

    void LoseGame();

    void GameEnded();

    void ShowMap();

    void HardStop();

    void WrongInput();

    void AwakeApplication();

    void GameNotStarted();

    void ShowCurrentHelp();

    void WrongGlobalTurn();

    void ItemsPickedUp(ArrayList<Integer> ItemsId);

    void ShowInventory();

    void ItemEquipped();

    void ItemNotInInventory();

    void ShowEquipHelp();

    void WrongIntegerInput();

    void ShowCharacterMenu();

    void ShowCharacterHelp();

    void OnLevelUp();

    void OnCharacteristicUp(int type, int amount);

    void NotEnoughCharacterPoints();

    void AlreadyHaveAbility();

    void NotEnoughAbilityPoints();

    void TooLowLevelForAbility();

    void AbilityDoesntExist();

    void StartBattle();

    void EnemyCast(Ability ability);

    void EnemyFailCast();

    void PlayerCast(Ability ability);

    void NotEnoughMana();

    void NotEnoughStamina();

    void NotEnoughHealth();

    void PlayerDied();

    void WinBattle(Monster enemy);

    void ShowBattleMenu();
}
