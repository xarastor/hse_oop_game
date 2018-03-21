import java.util.ArrayList;

public interface IView {

    void Start();

    void WinGame();

    void LoseGame();

    void GameEnded();

    void ShowMap();

    void GlobalTurn();

    void PlayerBattleTurn();

    void EnemyBattleTurn();

    void HardStop();

    void WrongInput();

    void AwakeApplication();

    void GameNotStarted();

    void ShowCurrentHelp();

    void WrongGlobalTurn();

    void ItemsPickedUp(ArrayList<Integer> ItemsId);

    void ShowInventory();
}
