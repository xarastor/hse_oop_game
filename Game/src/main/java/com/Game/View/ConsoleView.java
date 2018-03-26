package com.Game.View;

import com.Game.Ability.Ability;
import com.Game.Ability.AbilityStorage;
import com.Game.Ability.AbilityType;
import com.Game.Character.Monster;
import com.Game.Character.MonsterStorage;
import com.Game.Item.Item;
import com.Game.Manager.GameManager;
import com.Game.Map.Cell;
import com.Game.Map.Map;
import com.Game.Character.Player;
import com.Game.Character.GameCharacter;
import com.Game.Item.ItemStorage;
import com.Game.Item.ItemType;

import java.util.ArrayList;

/**
 * Класс реализующий интерфейс IView для конслои.
 * @see IView
 * @author titaninus
 * @version 1.1
 */
public class ConsoleView implements IView {

    /** Обозначение неоткрытой клетки */
    private final String NotRevealed = "# ";

    /** Обозначение положения пользователя */
    private final String PlayerPos = "X ";

    /** Обозначение клетки с предметами */
    private final String ArtifactPos = "A ";

    /** Обозначение клетки с мнстром */
    private final String EnemyPos = "E ";

    /** Обозначение пустой клетки */
    private final String EmptyPos = "O ";

    /** Имена навыков для отображения */
    private final String[] CharNames = {"Сила", "Ловкость", "Интеллект", "Мудрость", "Регенерация маны", "Регенерация здоровья", "Регенерация выносливости", "Удача"};

    /**
     * Распечатать собщение в консоль
     * @param message
     */
    public void Print(String message) {
        System.out.print(message);
    }

    /**
     * Распечатать сообщение s с аргументами args в консоль
     * @param s
     * @param args
     */
    private void PrintFormat(String s, Object... args) {
        Print(String.format(s, args));
    }

    /**
     * @see IView
     */
    public void Start() {
        Print("Начата новая игра\n");
    }

    /**
     * @see IView
     */
    public void HardStop() {
        Print("Игра аварийно завершена\n");
    }

    /**
     * @see IView
     */
    public void WrongInput() {
        Print("Введен неверный символ\n");
    }

    /**
     * @see IView
     */
    public void AwakeApplication() {
        Print("Для того чтобы начать игру введите 'S'\n");
    }

    /**
     * @see IView
     */
    public void GameNotStarted() {
        Print("Игра еще не начата\n");
    }

    /**
     * Показывает состояние текущей клетки
     */
    private void ShowCellDefinition() {
        switch (GameManager.getInstance().getCurrentCell().Type) {
            case Empty: {
                Print("На этой клетке ничего нет\n");
                break;
            }
            case Artifact: {
                Print("На этой клетке есть предметы\n");
                Print("Чтобы поднять предметы нажмите 'P'\n");
                break;
            }
            case Monster: {
                PrintFormat("На этой клетке есть монстр %s\n",
                        MonsterStorage.Monsters.get(GameManager.getInstance().getCurrentCell().MonsterId).Name);
                Print("Чтобы вступить в сражение нажмите 'F'\n");
                break;
            }
        }
    }

    /**
     * Показывает подсказку для главного меню
     */
    private void ShowGlobalHelp() {
        ShowCellDefinition();
        Print(  "Чтобы открыть карту нажмите 'M'\n" +
                "Чтобы открыть инвентарь нажмите 'I'\n" +
                "Чтобы открыть меню характеристик нажмите 'C'\n" +
                "Чтобы завершить игру нажмите 'E'\n");
    }

    /**
     * Показывает подсказку для меню карты
     */
    private void ShowMapHelp() {
        Print("Чтобы пойти вверх введите 'W'\n");
        Print("Чтобы пойти вниз введите 'S'\n");
        Print("Чтобы пойти налево введите 'A'\n");
        Print("Чтобы пойти направо введите 'D'\n");
        Print("Чтобы вернуться назад введите 'B'\n");
    }

    /**
     * Показывает подсказку для битвы
     */
    private void ShowBattleHelp() {
        Print("Ввведите Id навыка, который вы хотите применить\n");
    }

    /**
     * Показывает подсказку для меню инвентаря
     */
    private void ShowInventoryHelp() {
        Print("Чтобы открыть категорию шлемов введите 'H'\n");
        Print("Чтобы открыть категорию оружия введите 'W'\n");
        Print("Чтобы открыть категорию ботинок введите 'T'\n");
        Print("Чтобы открыть категорию перчаток введите 'G'\n");
        Print("Чтобы открыть категорию брони введите 'A'\n");
        Print("Чтобы открыть категорию штанов введите 'P'\n");
        if (GameManager.getInstance().SelectedInventoryMenuCategory != -1) {
            Print("Чтобы экипировать предмет введите 'Q'\n");
            Print("Чтобы вернуться назад введите 'I'\n");
        }
        Print("Чтобы вернуться в главное меню введите 'B'\n");
    }

    /**
     * @see IView
     */
    public void ShowCharacterHelp() {

        if (GameManager.getInstance().player.getCharacterPoint() > 0) {
            Print("Чтобы повысить силу введите 'S'\n");
            Print("Чтобы повысить ловкость введите 'A'\n");
            Print("Чтобы повысить интеллект введите 'I'\n");
            Print("Чтобы повысить мудрость введите 'W'\n");
        }
        if (GameManager.getInstance().player.getAbilityPoint() > 0) {
            Print("Чтобы купить навык введите 'Q' и введите Id навыка\n");
        }
        Print("Чтобы вернуться в главное меню введите 'B'\n");
    }

    /**
     * @see IView
     */
    public void OnLevelUp() {
        Print("Уровень повышен!\n");
        PrintFormat("Очки характеристик +%d ", Player.CharacterPointsOnLevelUp);
        PrintFormat("Очки навыков +%d ", Player.AbilityPointsOnLevelUp);
        PrintFormat("Сила +%d ", Player.StrengthOnLevelUp * (GameManager.getInstance().player.getLevel() / 2 + 1));
        PrintFormat("Ловкость +%d ", Player.AgilityOnLevelUp * (GameManager.getInstance().player.getLevel() / 2 + 1));
        PrintFormat("Интеллект +%d ", Player.IntelligenceOnLevelUp * (GameManager.getInstance().player.getLevel() / 2 + 1));
        PrintFormat("Мудрость +%d ", Player.WisdomOnLevelUp * (GameManager.getInstance().player.getLevel() / 2 + 1));
        PrintFormat("Удача +%d \n", Player.LuckOnLevelUp);
    }

    /**
     * @see IView
     */
    public void OnCharacteristicUp(int type, int amount) {
        String name = CharNames[type];
        PrintFormat("Характеристика %s повышена на %d\n", name, amount);
    }

    /**
     * @see IView
     */
    public void NotEnoughCharacterPoints() {
        Print("Не хватает очков характеристик\n");
    }

    /**
     * @see IView
     */
    public void AlreadyHaveAbility() {
        Print("У вас уже есть этот навык\n");
    }

    /**
     * @see IView
     */
    public void NotEnoughAbilityPoints() {
        Print("Не хватает очков навыков\n");
    }

    /**
     * @see IView
     */
    public void TooLowLevelForAbility() {
        Print("Ваш уровень недостаточен чтобы купить этот навык\n");
    }

    /**
     * @see IView
     */
    public void AbilityDoesntExist() {
        Print("Такого навыка не существует\n");

    }

    /**
     * @see IView
     */
    public void StartBattle() {
        Print("Битва началась!\n");
    }

    /**
     * @see IView
     */
    public void EnemyCast(Ability ability) {
        if (ability.Type == AbilityType.SelfImposed) {
            PrintFormat("Противник усилил себя: %s\n", ability.toString());
        } else {
            PrintFormat("Противник применил навык: %s\n Получено %d урона\n", ability.toString(), ability.Damage);
        }
    }

    /**
     * @see IView
     */
    public void EnemyFailCast() {
        Print("Противнику не удалось применить навык\n");
    }

    /**
     * @see IView
     */
    public void PlayerCast(Ability ability) {
        if (ability.Type == AbilityType.SelfImposed) {
            PrintFormat("Вы усилили себя: %s\n", ability.toString());
        } else {
            PrintFormat("Вы применили навык: %s\n Нанесено %d урона\n", ability.toString(), ability.Damage);
        }
    }

    /**
     * @see IView
     */
    public void NotEnoughMana() {
        Print("Недостаточно маны\n");
    }

    /**
     * @see IView
     */
    public void NotEnoughStamina() {
        Print("Недостаточно выносливости\n");

    }

    /**
     * @see IView
     */
    public void NotEnoughHealth() {
        Print("Недостаточно здоровья\n");

    }

    /**
     * @see IView
     */
    public void PlayerDied() {
        Print("Вы погибли в битве\n");
    }

    /**
     * @see IView
     */
    public void WinBattle(Monster enemy) {
        Print("Вы победили в битве\n");
        PrintFormat("Награда: %d опыта\n", enemy.XPReward);

    }

    /**
     * Показывает характеристики персонажа для битвы
     * @param gameCharacter
     */
    private void PrintBattleInfo(GameCharacter gameCharacter) {
        PrintFormat("Cила - %d; Ловкость - %d; Интеллект - %d; Мудрость - %d\n",
                gameCharacter.getStrength(), gameCharacter.getAgility(), gameCharacter.getIntelligence(), gameCharacter.getWisdom());

        PrintFormat("Здоровье - %d/%d + %d/ход;\n",
                gameCharacter.getCurrentHealth(), gameCharacter.getHealthPoints(), gameCharacter.getHealthRegen());
        PrintFormat("Мана - %d/%d + %d/ход;\n",
                gameCharacter.getCurrentMana(), gameCharacter.getManaPoints(), gameCharacter.getManaRegen());
        PrintFormat("Выносливость - %d/%d + %d/ход;\n",
                gameCharacter.getCurrentStamina(), gameCharacter.getStaminaPoints(), gameCharacter.getStaminaRegen());
    }

    /**
     * @see IView
     */
    public void ShowBattleMenu() {
        Print("=== Игрок ===\n");
        PrintBattleInfo(GameManager.getInstance().player);

        Print("= Доступные навыки =\n");
        for(int index: GameManager.getInstance().player.getAbilities()) {
            PrintFormat("%s\n", AbilityStorage.Abilities.get(index).toString());
        }

        PrintFormat("=== Монстр %s ===\n", GameManager.getInstance().GetEnemy().Name);
        PrintBattleInfo(GameManager.getInstance().GetEnemy());
    }

    /**
     * @see IView
     */
    public void ShowCurrentHelp() {
        if (GameManager.getInstance().isGameStarted) {
            if (GameManager.getInstance().inMainMenu) {
                ShowGlobalHelp();
                return;
            }
            if (GameManager.getInstance().inBattleMenu) {
                ShowBattleHelp();
                return;
            }
            if (GameManager.getInstance().inMapMenu) {
                ShowMapHelp();
                return;
            }
            if (GameManager.getInstance().inInventoryMenu) {
                ShowInventoryHelp();
                return;
            }
            if (GameManager.getInstance().inCharacterMenu) {
                ShowCharacterHelp();
                return;
            }
            Print("Неизвестное состоние игры\n");
            GameManager.getInstance().HardStop();
        } else {
            AwakeApplication();
        }
    }

    /**
     * @see IView
     */
    public void WrongGlobalTurn() {
        Print("Нельзя пойти в эту сторону!\n");
    }

    /**
     * @see IView
     */
    public void ItemsPickedUp(ArrayList<Integer> ItemsId) {
        for (Integer Id: ItemsId) {
            Print("Поднят предмет " + ItemStorage.Items.get(Id).Name + "\n");
        }
    }

    /**
     * @see IView
     */
    public void ShowInventory() {
        if (GameManager.getInstance().SelectedInventoryMenuCategory == -1) {
            Print("В вашем инвентаре " + GameManager.getInstance().player.getInventory().items.size() + " предметов\n");
            for (int i = 0; i < GameManager.getInstance().player.getInventory().EquippedItems.length; ++i) {
                if (GameManager.getInstance().player.getInventory().EquippedItems[i] != -1) {
                    Item equipped = ItemStorage.Items.get(GameManager.getInstance().player.getInventory().EquippedItems[i]);
                    PrintFormat("В слоте %s находится предмет уровня %s с названием %s; Всего предметов в данной категории: %d\n",
                            ItemType.values()[i].toString(), equipped.ItemLevel, equipped.Name, GameManager.getInstance().player.getInventory().ItemsByCategory.get(ItemType.values()[i]).size());
                } else {
                    PrintFormat("В слоте %s пусто;  Всего предметов в данной категории: %d\n",
                            ItemType.values()[i].toString(), GameManager.getInstance().player.getInventory().ItemsByCategory.get(ItemType.values()[i]).size());
                }
            }
        } else {
            ItemType type = ItemType.values()[GameManager.getInstance().SelectedInventoryMenuCategory];
            PrintFormat("В вашем инвенторе %d предметов категории %s\n", GameManager.getInstance().player.getInventory().ItemsByCategory.get(type).size(), type.toString());
            for (int itemIndex : GameManager.getInstance().player.getInventory().ItemsByCategory.get(type)) {
                PrintFormat("%s\n", ItemStorage.Items.get(itemIndex).toString());
            }
        }
    }

    /**
     * @see IView
     */
    public void ItemEquipped() {
        Print("Предмет успешно надет\n");
    }

    /**
     * @see IView
     */
    public void ItemNotInInventory() {
        Print("Такого предмета нет в инвентаре\n");
    }

    /**
     * @see IView
     */
    public void ShowEquipHelp() {
        Print("Чтобы надеть предмет введите его Id\n");
    }

    /**
     * @see IView
     */
    public void WrongIntegerInput() {
        Print("Должно быть введено корректное число\n");
    }

    /**
     * @see IView
     */
    public void ShowCharacterMenu() {
        PrintFormat("Уровень: %d\n", GameManager.getInstance().player.getLevel());
        PrintFormat("Очки опыта: %d, следующий уровень в %d\n",
                GameManager.getInstance().player.getXP(), Player.XPLevels.get(GameManager.getInstance().player.getLevel()));

        Print("==== Основные характеристики ====\n");
        PrintFormat("Сила: %d\n", GameManager.getInstance().player.getStrength());
        PrintFormat("Ловкость: %d\n", GameManager.getInstance().player.getAgility());
        PrintFormat("Интеллект: %d\n", GameManager.getInstance().player.getIntelligence());
        PrintFormat("Мудрость: %d\n", GameManager.getInstance().player.getWisdom());
        PrintFormat("Удача: %d\n", GameManager.getInstance().player.getLuck());

        Print("==== Дополнительные характеристики ====\n");
        PrintFormat("Здоровье: %d из %d, регенерация: %d\n",
                GameManager.getInstance().player.getCurrentHealth(), GameManager.getInstance().player.getHealthPoints(), GameManager.getInstance().player.getHealthRegen());
        PrintFormat("Мана: %d из %d, регенерация: %d\n",
                GameManager.getInstance().player.getCurrentMana(), GameManager.getInstance().player.getManaPoints(), GameManager.getInstance().player.getManaRegen());
        PrintFormat("Выносливость: %d из %d, регенерация: %d\n",
                GameManager.getInstance().player.getCurrentStamina(), GameManager.getInstance().player.getStaminaPoints(), GameManager.getInstance().player.getStaminaRegen());

        Print("==== Навыки ====\n");
        for (Integer abilityId: GameManager.getInstance().player.getAbilities()) {
            Print(AbilityStorage.Abilities.get(abilityId).toString());
        }
        Print("\n\n\n");
        PrintFormat("Доступно очков характеристик: %d\n", GameManager.getInstance().player.getCharacterPoint());
        PrintFormat("Доступно очков навыков: %d\n", GameManager.getInstance().player.getAbilityPoint());

        Print("\n\n\n");
        Print("=== Доступные для покупки навыки ===\n");
        for (Ability ability: AbilityStorage.Abilities.values()) {
            if (    !GameManager.getInstance().player.getAbilities().contains(ability.Id)
                    && ability.Level <= GameManager.getInstance().player.getLevel()
                    && ability.Level <= GameManager.getInstance().player.getAbilityPoint()) {
                PrintFormat("%s\n", ability.toString());
            }
        }
    }

    /**
     * @see IView
     */
    public void ShowMap() {
        Map map = GameManager.getInstance().map;
        ShowCellDefinition();
        Print("Карта\n");
        PrintFormat(" '%s' - неразведанная территория\n '%s' - положение Игрока\n '%s' - место с артефактами\n '%s' - положение Монстра\n",
                NotRevealed, PlayerPos, ArtifactPos, EnemyPos);
        for (int j = 0; j < map.Height; ++j) {
            for (int i = 0; i < map.Width; ++i) {
                Cell cell = map.Cells.get(j).get(i);
                if (GameManager.getInstance().isPlayerPos(i, j)) {
                    Print(PlayerPos);
                } else {
                    if (cell.isRevealed) {
                        switch (cell.Type) {
                            case Artifact:
                                Print(ArtifactPos);
                                break;
                            case Empty:
                                Print(EmptyPos);
                                break;
                            case Monster:
                                Print(EnemyPos);
                        }
                    } else {
                        Print(NotRevealed);
                    }
                }
            }
            Print("\n");
        }
    }

    /**
     * @see IView
     */
    public void WinGame() {
        Print("Вы выиграли!!\n");
    }

    /**
     * @see IView
     */
    public void LoseGame() {
        Print("Вы проиграли!!\n");
    }

    /**
     * @see IView
     */
    public void GameEnded() {
        Print("Игра завершена\n");
    }

}
