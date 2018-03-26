package com.Game.Manager;

import com.Game.Ability.Ability;
import com.Game.Ability.AbilityStorage;
import com.Game.Character.Monster;
import com.Game.Character.MonsterStorage;
import com.Game.Character.Player;
import com.Game.Controller.ConsoleController;
import com.Game.Controller.IController;
import com.Game.Item.ItemStorage;
import com.Game.View.ConsoleView;
import com.Game.View.IView;
import com.Game.Map.Cell;
import com.Game.Map.CellType;
import com.Game.Map.Map;
import com.Game.Map.MapGenerator;

/**
 * Менеджер игры
 * @author titaninus
 * @version 1.1
 */
public class GameManager {

    /** Визуализатор игры */
    public IView renderer = new ConsoleView();

    /** Контроллер игры */
    public IController controller = new ConsoleController();

    /** Менеджер битв */
    private BattleManager battleManager;

    /** Singleton-паттерн */
    private static GameManager Instance;

    /** Реализация паттерна Singleton */
    public static GameManager getInstance() {
        if (Instance == null) {
            Instance = new GameManager();
        }
        return Instance;
    }

    /** Количество пустых клеток на карте */
    private int EmptyCells;

    /** Идентификатор корректности игры */
    public boolean isValidGame = true;

    /** Идентификатор начатой игры */
    public boolean isGameStarted = false;

    /** Идентификатор нахождения в меню картф */
    public boolean inMapMenu = false;

    /** Идентификатор нахождения в меню битвы */
    public boolean inBattleMenu = false;

    /** Идентификатор нахождения в главном меню */
    public boolean inMainMenu = false;

    /** Идентификатор нахождения в меню инвентаря */
    public boolean inInventoryMenu = false;

    /** Выбранная категория меню инвентаря */
    public int SelectedInventoryMenuCategory = -1;

    /** Ожидание ввода индекса категории для меню инвентаря */
    public boolean isWaitingForInventoryId;

    /** Идентификатор нахождения в меню перснажа */
    public boolean inCharacterMenu = false;

    /** Ожидание ввода индекса навыка в меню персонажа */
    public boolean isWaitingForAbilityId;

    /** Ожидание вводо индекса навыка в битве */
    public boolean isWaitingForBattleId;

    /** Игрок */
    public Player player;

    /** Карта */
    public Map map;

    /** Положение игрока по горизонтали */
    public int PlayerX;

    /** Положение игрока по вертикали */
    public int PlayerY;

    /** Конструктор класса */
    public  GameManager() {
        ItemStorage.LoadAllItems();
        MonsterStorage.LoadAllMonsters();
        AbilityStorage.LoadAllAbilities();
        battleManager = new BattleManager();
        player = new Player();
        map = MapGenerator.GenerateMap(20, 20);
    }

    /**
     * Проверка позиции на расположение в ней игрока
     * @param x
     * @param y
     * @return возвращает расположен ли в данной позиции игрок
     */
    public boolean isPlayerPos(int x, int y) {
        return x == PlayerX && y == PlayerY;
    }

    /**
     * Геттер для текущей клетки
     * @return возвращает клетку, на которой находится игрок
     */
    public Cell getCurrentCell() {
        return map.Cells.get(PlayerY).get(PlayerX);
    }

    /**
     * Сбрасывает идентификаторы меню
     */
    public void ResetMenus() {
        inMapMenu = false;
        inMainMenu = false;
        inBattleMenu = false;
        inInventoryMenu = false;
        inCharacterMenu = false;
    }

    /**
     * Показывает карту
     */
    public void ShowMap() {
        ResetMenus();
        inMapMenu = true;
        renderer.ShowMap();
    }

    /**
     * Показывает инвентарь
     */
    public void ShowInventory() {
        ResetMenus();
        inInventoryMenu = true;
        renderer.ShowInventory();
    }

    /**
     * Выбирает переданный индекс категоии для меню инвентаря
     * @param Id
     */
    public void SelectInventoryCategory(int Id) {
        SelectedInventoryMenuCategory = Id;
    }

    /**
     * Выставляет ожидание ввода предмета для экипировки
     */
    public void TryToEquipItem() {
        isWaitingForInventoryId = true;
    }

    /**
     * Сбрасывает оидание ввода предмета для экипировки
     */
    public void EquippedEnd() {
        isWaitingForInventoryId = false;
    }

    /**
     * Показывает главное меню
     */
    public void ShowMainMenu() {
        ResetMenus();
        inMainMenu = true;
    }

    /**
     * Показывает меню персонажа
     */
    public void ShowCharacterMenu() {
        ResetMenus();
        inCharacterMenu = true;
        renderer.ShowCharacterMenu();
    }

    /**
     * Старт приложения
     */
    public void Awake() {
        renderer.AwakeApplication();
        controller.MakeGameLoop();
    }

    /**
     * Старт игры
     */
    public void Start() {
        PlayerX = map.StartX;
        PlayerY = map.StartY;
        getCurrentCell().isRevealed = true;
        isGameStarted = true;
        inMainMenu = true;
        EmptyCells = 1;
        renderer.Start();
    }

    /**
     * Аварийное завершение игры
     */
    public void HardStop() {
        renderer.HardStop();
        System.exit(1);
    }

    /**
     * Обычное завершение игры
     */
    public void EndGame() {
        renderer.GameEnded();
        isValidGame = false;
    }

    /**
     * Передвинуть игрока вниз по карте
     */
    public void GoDown() {
        if (PlayerY == (map.Width - 1)) {
            renderer.WrongGlobalTurn();
        } else {
            PlayerY += 1;
            if (!getCurrentCell().isRevealed && getCurrentCell().Type == CellType.Empty) {
                EmptyCells += 1;
                CheckWinCondition();
            }
            getCurrentCell().isRevealed = true;
            GlobalTurn();
        }
    }

    /**
     * Передвинуть игрока вверх по карте
     */
    public void GoUp() {
        if (PlayerY == 0) {
            renderer.WrongGlobalTurn();
        } else {
            PlayerY -= 1;
            if (!getCurrentCell().isRevealed && getCurrentCell().Type == CellType.Empty) {
                EmptyCells += 1;
                CheckWinCondition();
            }
            getCurrentCell().isRevealed = true;
            GlobalTurn();
        }
    }

    /**
     * Передвинуть игрока вправо по карте
     */
    public void GoRight() {
        if (PlayerX == (map.Height - 1)) {
            renderer.WrongGlobalTurn();
        } else {
            PlayerX += 1;
            if (!getCurrentCell().isRevealed && getCurrentCell().Type == CellType.Empty) {
                EmptyCells += 1;
                CheckWinCondition();
            }
            getCurrentCell().isRevealed = true;
            GlobalTurn();
        }
    }

    /**
     * Передвинуть игрока влево по карте
     */
    public void GoLeft() {
        if (PlayerX == 0) {
            renderer.WrongGlobalTurn();
        } else {
            PlayerX -= 1;
            if (!getCurrentCell().isRevealed && getCurrentCell().Type == CellType.Empty) {
                EmptyCells += 1;
                CheckWinCondition();
            }
            getCurrentCell().isRevealed = true;
            GlobalTurn();
        }
    }

    /**
     * Обработка события хода на карте
     */
    public void GlobalTurn() {
        player.onGlobalTurn();
    }

    /**
     * Обработка события повышения уровня игрока
     */
    public void LevelUp() {
        renderer.OnLevelUp();
    }

    /**
     * Чит для проверки функционала
     */
    public void Cheat() {
        player.AddExperience(200);
    }

    /**
     * Повышение силы игрока
     */
    public void StrengthUp() {
        if (player.StrengthUp())
            renderer.OnCharacteristicUp(0, 1);
    }

    /**
     * Повышение ловкости игрока
     */
    public void AgilityUp() {
        if (player.AgilityUp())
        renderer.OnCharacteristicUp(1, 1);
    }

    /**
     * Повышение интеллекта игрока
     */
    public void IntelligenceUp() {
        if (player.IntelligenceUp())
        renderer.OnCharacteristicUp(2, 1);
    }

    /**
     * Повышение мудрости игрока
     */
    public void WisdomUp() {
        if (player.WisdomUp())
        renderer.OnCharacteristicUp(3, 1);
    }

    /**
     * Обработка события недостатка очков характеристик
     */
    public void NotEnoughCharacterPoints() {
        renderer.NotEnoughCharacterPoints();
    }

    /**
     * Обработка события при попытке покупки уже добавленного навыка
     */
    public void AlreadyHaveAbility() {
        renderer.AlreadyHaveAbility();
    }

    /**
     * Обработка события при недостатке очков навыков
     */
    public void NotEnoughAbilityPoints() {
        renderer.NotEnoughAbilityPoints();
    }

    /**
     * Обработка события недосатка уровня для покупки навыка
     */
    public void TooLowLevelForAbility() {
        renderer.TooLowLevelForAbility();
    }

    /**
     * Обработка события попытки покупки несуществующего навыка
     */
    public void AbilityDoesntExist() {
        renderer.AbilityDoesntExist();
    }

    /**
     * Начало битвы
     */
    public void StartBattle() {

        battleManager.StartBattle(getCurrentCell().MonsterId);
    }

    /**
     * Обработка события начала битвы
     */
    public void onBattleStart() {
        ResetMenus();
        inBattleMenu = true;
        isWaitingForBattleId = true;
        renderer.StartBattle();
        renderer.ShowBattleMenu();
    }

    /**
     * Геттер для текущего монстра в битве
     * @return
     */
    public Monster GetEnemy() {
        return battleManager.Enemy;
    }

    /**
     * Обработка хода игрока с выбранным навыком
     * @param abilityIndex
     */
    public void PlayerTurn(int abilityIndex) {

        Ability ability = AbilityStorage.Abilities.get(abilityIndex);

        if (player.getCurrentHealth() >= ability.HealthCost) {
            if (player.getCurrentMana() >= ability.ManaCost) {
                if (player.getCurrentStamina() >= ability.StaminaCost) {
                    battleManager.ApplyAbility(abilityIndex, player, battleManager.Enemy);
                    renderer.PlayerCast(ability);
                    battleManager.onBattleTurn();
                    if (battleManager.Enemy.getCurrentHealth() <= 0) {
                        WinBattle(battleManager.Enemy);
                    } else {
                        battleManager.EnemyTurn();
                    }
                } else {
                    renderer.NotEnoughStamina();
                }
            } else {
                renderer.NotEnoughMana();
            }
        } else {
            renderer.NotEnoughHealth();
        }

    }

    /**
     * Обработка события выиграша битвы
     * @param Enemy
     */
    public void WinBattle(Monster Enemy) {
        isWaitingForBattleId = false;
        battleManager.onBattleEnd();
        player.AddExperience(Enemy.XPReward);
        getCurrentCell().Type = CellType.Artifact;
        renderer.WinBattle(Enemy);
        ShowMainMenu();
    }

    /**
     * Обработка события добавления предмета в инвентарь
     */
    public void pickUp() {
        for (Integer itemId : getCurrentCell().Artifacts) {
            ItemStorage.Items.get(itemId).pickup();
        }
        getCurrentCell().Type = CellType.Empty;
        renderer.ItemsPickedUp(GameManager.getInstance().getCurrentCell().Artifacts);
        EmptyCells += 1;
        CheckWinCondition();
    }

    /**
     * проверка условия выиигрша игры
     */
    private void CheckWinCondition() {
        if (EmptyCells == map.Width * map.Height) {
            renderer.WinGame();
            EndGame();
        }
    }

    /**
     * Обработка события проигрыша в игре
     */
    public void LoseGame() {
        renderer.LoseGame();
        inMainMenu = true;
        EndGame();
    }
}
