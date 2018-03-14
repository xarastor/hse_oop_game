/**
 * Created by titaninus on 14.03.18.
 */
public class Modifier {

    public enum modifiedPoint {
        Strength,
        Agility,
        Intelligence,
        Wisdom,
        ManaRegen,
        HealthRegen,
        StaminaRegen,
        Luck
    }
    public modifiedPoint type;
    public boolean isTemporary;
    public int durationBattle;
    public int durationGlobal;
    public int value;

}
