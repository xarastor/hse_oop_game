package com.Game.Manager;

import com.Game.Ability.Ability;
import com.Game.Ability.AbilityStorage;
import com.Game.Character.Monster;
import com.Game.Character.MonsterStorage;
import com.Game.Character.Player;
import com.Game.Controller.ConsoleController;
import com.Game.Controller.IController;
import com.Game.Item.ItemStorage;
import com.Game.Map.*;
import com.Game.Character.*;
import com.Game.View.ConsoleView;
import com.Game.View.IView;
import com.Game.Map.Cell;
import com.Game.Map.CellType;
import com.Game.Map.Map;
import com.Game.Map.MapGenerator;

/**
 * Created by titaninus on 13.03.18.
 */
public class GameManager {

    public IView renderer = new ConsoleView();
    public IController controller = new ConsoleController();
    private BattleManager battleManager;

    private static GameManager Instance;
    private int EmptyCells;

    public static GameManager getInstance() {
        if (Instance == null) {
            Instance = new GameManager();
        }
        return Instance;
    }

    public boolean isValidGame = true;
    public boolean isGameStarted = false;
    public boolean inMapMenu = false;
    public boolean inBattleMenu = false;
    public boolean inMainMenu = false;

    public boolean inInventoryMenu = false;
    public int SelectedInventoryMenuCategory = -1;
    public boolean isWaitingForInventoryId;

    public boolean inCharacterMenu = false;
    public boolean isWaitingForAbilityId;

    public boolean isWaitingForBattleId;

    public Player player;
    public Map map;
    public int NotRevealedCells;
    public int PlayerX;
    public int PlayerY;

    public  GameManager() {
        ItemStorage.LoadAllItems();
        MonsterStorage.LoadAllMonsters();
        AbilityStorage.LoadAllAbilities();
        battleManager = new BattleManager();
        player = new Player();
        map = MapGenerator.GenerateMap(10, 10);
    }

    public boolean isPlayerPos(int x, int y) {
        return x == PlayerX && y == PlayerY;
    }

    public Cell getCurrentCell() {
        return map.Cells.get(PlayerY).get(PlayerX);
    }

    public void ResetMenus() {
        inMapMenu = false;
        inMainMenu = false;
        inBattleMenu = false;
        inInventoryMenu = false;
        inCharacterMenu = false;
    }

    public void ShowMap() {
        ResetMenus();
        inMapMenu = true;
        renderer.ShowMap();
    }

    public void ShowInventory() {
        ResetMenus();
        inInventoryMenu = true;
        renderer.ShowInventory();
    }

    public void SelectInventoryCategory(int Id) {
        SelectedInventoryMenuCategory = Id;
    }

    public void TryToEquipItem() {
        isWaitingForInventoryId = true;
    }

    public void EquippedEnd() {
        isWaitingForInventoryId = false;
    }

    public void ShowMainMenu() {
        ResetMenus();
        inMainMenu = true;
    }

    public void ShowCharacterMenu() {
        ResetMenus();
        inCharacterMenu = true;
        renderer.ShowCharacterMenu();
    }

    public void Awake() {
        renderer.AwakeApplication();
        controller.MakeGameLoop();
    }

    public void Start() {
        PlayerX = map.StartX;
        PlayerY = map.StartY;
        getCurrentCell().isRevealed = true;
        isGameStarted = true;
        inMainMenu = true;
        EmptyCells = 1;
        renderer.Start();
    }

    public void HardStop() {
        renderer.HardStop();
        System.exit(1);
    }

    public void EndGame() {
        renderer.GameEnded();
        isValidGame = false;
    }

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

    public void GlobalTurn() {
        player.onGlobalTurn();
    }

    public void LevelUp() {
        renderer.OnLevelUp();
    }

    public void Cheat() {
        player.AddExperience(200);
    }

    public void StrengthUp() {
        if (player.StrengthUp())
            renderer.OnCharacteristicUp(0, 1);
    }

    public void AgilityUp() {
        if (player.AgilityUp())
        renderer.OnCharacteristicUp(1, 1);
    }

    public void IntelligenceUp() {
        if (player.IntelligenceUp())
        renderer.OnCharacteristicUp(2, 1);
    }

    public void WisdomUp() {
        if (player.WisdomUp())
        renderer.OnCharacteristicUp(3, 1);
    }

    public void NotEnoughCharacterPoints() {
        renderer.NotEnoughCharacterPoints();
    }

    public void AlreadyHaveAbility() {
        renderer.AlreadyHaveAbility();
    }

    public void NotEnoughAbilityPoints() {
        renderer.NotEnoughAbilityPoints();
    }

    public void TooLowLevelForAbility() {
        renderer.TooLowLevelForAbility();
    }

    public void AbilityDoesntExist() {
        renderer.AbilityDoesntExist();
    }

    public void StartBattle() {

        battleManager.StartBattle(getCurrentCell().MonsterId);
    }

    public void onBattleStart() {
        ResetMenus();
        inBattleMenu = true;
        isWaitingForBattleId = true;
        renderer.StartBattle();
        renderer.ShowBattleMenu();
    }

    public Monster GetEnemy() {
        return battleManager.Enemy;
    }

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

    public void WinBattle(Monster Enemy) {
        isWaitingForBattleId = false;
        battleManager.onBattleEnd();
        player.AddExperience(Enemy.XPReward);
        getCurrentCell().Type = CellType.Artifact;
        renderer.WinBattle(Enemy);
        ShowMainMenu();
    }

    public void pickUp() {
        for (Integer itemId : getCurrentCell().Artifacts) {
            ItemStorage.Items.get(itemId).pickup();
        }
        getCurrentCell().Type = CellType.Empty;
        renderer.ItemsPickedUp(GameManager.getInstance().getCurrentCell().Artifacts);
        EmptyCells += 1;
        CheckWinCondition();
    }

    private void CheckWinCondition() {
        if (EmptyCells == map.Width * map.Height) {
            renderer.WinGame();
            EndGame();
        }
    }

    public void LoseGame() {
        renderer.LoseGame();
        inMainMenu = true;
        EndGame();
    }
}
