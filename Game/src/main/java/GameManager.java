/**
 * Created by titaninus on 13.03.18.
 */
public class GameManager {

    private static GameManager Instance;

    public static GameManager getInstance() {
        if (Instance == null) {
            Instance = new GameManager();
        }
        return Instance;
    }

    public Player player;
    public Map map;
    public  GameManager() {
        ItemStorage.LoadAllItems();
        MonsterStorage.LoadAllMonsters();
        player = new Player();
        map = MapGenerator.GenerateMap(50, 50);
    }

    public void Start() {
        System.out.println("Начата новая игра");
    }

}
