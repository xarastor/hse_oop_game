package Manager;

import Ability.AbilityStorage;
import Controller.ConsoleController;
import Controller.IController;
import Item.ItemStorage;
import Map.*;
import Character.*;
import View.ConsoleView;
import View.IView;

/**
 * Created by titaninus on 13.03.18.
 */
public class GameManager {

    public IView renderer = new ConsoleView();
    public IController controller = new ConsoleController();

    private static GameManager Instance;

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

    public Player player;
    public Map map;
    public int PlayerX;
    public int PlayerY;

    public  GameManager() {
        ItemStorage.LoadAllItems();
        MonsterStorage.LoadAllMonsters();
        AbilityStorage.LoadAllAbilities();
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
            getCurrentCell().isRevealed = true;
            GlobalTurn();
        }
    }

    public void GoUp() {
        if (PlayerY == 0) {
            renderer.WrongGlobalTurn();
        } else {
            PlayerY -= 1;
            getCurrentCell().isRevealed = true;
            GlobalTurn();
        }
    }

    public void GoRight() {
        if (PlayerX == (map.Height - 1)) {
            renderer.WrongGlobalTurn();
        } else {
            PlayerX += 1;
            getCurrentCell().isRevealed = true;
            GlobalTurn();
        }
    }

    public void GoLeft() {
        if (PlayerX == 0) {
            renderer.WrongGlobalTurn();
        } else {
            PlayerX -= 1;
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
}
