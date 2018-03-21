import javafx.application.Application;

/**
 * Created by titaninus on 13.03.18.
 */
public class GameManager {

    IView renderer = new ConsoleView();
    IController controller = new ConsoleController();

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

    public Player player;
    public Map map;
    public int PlayerX;
    public int PlayerY;

    public  GameManager() {
        ItemStorage.LoadAllItems();
        MonsterStorage.LoadAllMonsters();
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
    }

    public void ShowMap() {
        ResetMenus();
        inMapMenu = true;
        renderer.ShowMap();
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
        }
    }

    public void GoUp() {
        if (PlayerY == 0) {
            renderer.WrongGlobalTurn();
        } else {
            PlayerY -= 1;
            getCurrentCell().isRevealed = true;
        }
    }

    public void GoRight() {
        if (PlayerX == (map.Height - 1)) {
            renderer.WrongGlobalTurn();
        } else {
            PlayerX += 1;
            getCurrentCell().isRevealed = true;
        }
    }

    public void GoLeft() {
        if (PlayerX == 0) {
            renderer.WrongGlobalTurn();
        } else {
            PlayerX -= 1;
            getCurrentCell().isRevealed = true;
        }
    }

}
