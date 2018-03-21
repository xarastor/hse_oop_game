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

    /*
    * Completly full constructor for modifiers
     */
    public Modifier(modifiedPoint mType, boolean temp, int dBatle, int dGlobal, int val) {
        type = mType;
        isTemporary = temp;
        durationBattle = dBatle;
        durationGlobal = dGlobal;
        value = val;
    }

    /*
    * Constructor for Items and Abilities
     */

    public Modifier(modifiedPoint mType, int val) {
        type = mType;
        isTemporary = false;
        value = val;
    }

    public Modifier Copy(Modifier prototype) {
        return new Modifier(prototype.type, prototype.isTemporary, prototype.durationBattle, prototype.durationGlobal, prototype.value);
    }

}
