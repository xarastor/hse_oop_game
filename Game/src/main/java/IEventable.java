/**
 * Created by titaninus on 14.03.18.
 */
public interface IEventable {
    void onGlobalTurn();

    void onBattleTurn();

    void onBattleStart();

    void onBattleEnd();
}
