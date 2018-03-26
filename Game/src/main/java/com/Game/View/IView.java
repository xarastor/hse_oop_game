package com.Game.View;

import com.Game.Ability.Ability;
import com.Game.Character.Monster;

import java.util.ArrayList;

/**
 * Интерфейс визуализации игрового процесса
 * @author titaninus
 * @version 1.1
 */
public interface IView {

    /**
     * Старт игры
     */
    void Start();

    /**
     * Игра выиграна
     */
    void WinGame();

    /**
     * Игра проиграна
     */
    void LoseGame();

    /**
     * Игра окончена
     */
    void GameEnded();

    /**
     * Показать карту
     */
    void ShowMap();

    /**
     * Аварийное завершение игры
     */
    void HardStop();

    /**
     * Неверный ввод
     */
    void WrongInput();

    /**
     * Запуск приложения
     */
    void AwakeApplication();

    /**
     * Игра не начата
     */
    void GameNotStarted();

    /**
     * Показывает текущую помощь
     */
    void ShowCurrentHelp();

    /**
     * Неверный ход на карте
     */
    void WrongGlobalTurn();

    /**
     * Подобраны преметы
     * @param ItemsId - подобранные предметы
     */
    void ItemsPickedUp(ArrayList<Integer> ItemsId);

    /**
     * Показать инвентарь
     */
    void ShowInventory();

    /**
     * Предмет экипирован
     */
    void ItemEquipped();

    /**
     * Предмет отсутствует в инвентаре
     */
    void ItemNotInInventory();

    /**
     * Показать подсказку для экипировки предмета
     */
    void ShowEquipHelp();

    /**
     * Неверный числовой ввод
     */
    void WrongIntegerInput();

    /**
     * Показать меню персонажа
     */
    void ShowCharacterMenu();

    /**
     * показать подсказку для меню персонажа
     */
    void ShowCharacterHelp();

    /**
     * Оповещение о повышении уровня
     */
    void OnLevelUp();

    /**
     * Оповещение о повышении характеристики
     * @param type - тип характеристики
     * @param amount - количество пунктов на которое увеличилась характеристика
     */
    void OnCharacteristicUp(int type, int amount);

    /**
     * Недостаточно очков персонажа для данного действия
     */
    void NotEnoughCharacterPoints();

    /**
     * У игрока уже есть эта способность
     */
    void AlreadyHaveAbility();

    /**
     * Не хватает очков навыков для данного действия
     */
    void NotEnoughAbilityPoints();

    /**
     * Слишком низкий уровень для покупки этого навыка
     */
    void TooLowLevelForAbility();

    /**
     * Навык не существует
     */
    void AbilityDoesntExist();

    /**
     * Начало битвы
     */
    void StartBattle();

    /**
     * Противник применил навык
     * @param ability
     */
    void EnemyCast(Ability ability);

    /**
     * Противнику не удалось применить навык
     */
    void EnemyFailCast();

    /**
     * Игрок применил навык
     * @param ability
     */
    void PlayerCast(Ability ability);

    /**
     * Недостаточно маны
     */
    void NotEnoughMana();

    /**
     * Недостаточно выносливости
     */
    void NotEnoughStamina();

    /**
     * Недостаточно здоровья
     */
    void NotEnoughHealth();

    /**
     * Игрок погиб
     */
    void PlayerDied();

    /**
     * Битва с монстром выиграна
     * @param enemy
     */
    void WinBattle(Monster enemy);

    /**
     * Показывает меню битвы
     */
    void ShowBattleMenu();
}
