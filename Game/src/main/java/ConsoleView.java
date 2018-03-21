import java.util.ArrayList;

public class ConsoleView implements IView {

    private final String NotRevealed = "# ";
    private final String PlayerPos = "X ";
    private final String ArtifactPos = "A ";
    private final String EnemyPos = "E ";
    private final String EmptyPos = "O ";

    public static void clearConsole() {
        for (int i = 0; i < 50; ++i) {
            System.out.println("\n");
        }
    }

    public void Print(String message) {
        System.out.print(message);
    }

    public void Start() {
        Print("Начата новая игра\n");
    }

    public void GlobalTurn() {

    }

    public void PlayerBattleTurn() {

    }

    public void EnemyBattleTurn() {

    }

    public void HardStop() {
        Print("Игра аварийно завершена\n");
    }

    public void WrongInput() {
        Print("Введен неверный символ\n");
    }

    public void AwakeApplication() {
        Print("Для того чтобы начать игру введите 'S'\n");
    }

    public void GameNotStarted() {
        Print("Игра еще не начата\n");
    }

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
                Print("На этой клетке есть монстр\n");
                Print("Чтобы вступить в сражение нажмите 'B'\n");
                break;
            }
        }
    }

    private void ShowGlobalHelp() {
        ShowCellDefinition();
        Print(  "Чтобы открыть карту нажмите 'M'\n" +
                "Чтобы открыть инвентарь нажмите 'I'\n" +
                "Чтобы завершить игру нажмите 'E'\n");
    }

    private void ShowMapHelp() {
        Print("Чтобы пойти вверх введите 'W'\n");
        Print("Чтобы пойти вниз введите 'S'\n");
        Print("Чтобы пойти налево введите 'A'\n");
        Print("Чтобы пойти направо введите 'D'\n");
        Print("Чтобы вернуться назад введите 'B'\n");
    }

    private void ShowBattleHelp() {

    }

    private void ShowInventoryHelp() {
    }

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
            }
            Print("Неизвестное состоние игры\n");
            GameManager.getInstance().HardStop();
        } else {
            AwakeApplication();
        }
    }


    public void WrongGlobalTurn() {
        Print("Нельзя пойти в эту сторону!\n");
    }

    public void ItemsPickedUp(ArrayList<Integer> ItemsId) {
        for (Integer Id: ItemsId) {
            Print("Поднят предмет " + ItemStorage.Items.get(Id).Name + "\n");
        }
    }

    public void ShowInventory() {
        Print("В вашем инвентаре " + GameManager.getInstance().player.inventory.items.size() + " предметов\n");
    }


    public void ShowMap() {
        //clearConsole();
        Map map = GameManager.getInstance().map;
        ShowCellDefinition();
        Print("Карта\n");
        Print(  "'#' - неразведанная территория\n" +
                "'X' - положение Игрока\n");
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

    public void WinGame() {
        Print("Вы выиграли!!\n");
    }

    public void LoseGame() {
        Print("Вы проиграли!!\n");
    }

    public void GameEnded() {
        Print("Игра завершена\n");
    }

}
