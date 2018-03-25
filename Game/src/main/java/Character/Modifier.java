package Character;

import java.util.ArrayList;

/**
 * Created by titaninus on 14.03.18.
 */
public class Modifier {

    public ModifierPoint type;
    public boolean isTemporary;
    public int durationBattle;
    public int durationGlobal;
    public int value;

    /*
    * Completly full constructor for modifiers
     */
    public Modifier(ModifierPoint mType, boolean temp, int dBatle, int dGlobal, int val) {
        type = mType;
        isTemporary = temp;
        durationBattle = dBatle;
        durationGlobal = dGlobal;
        value = val;
    }

    /*
    * Constructor for Items and Abilities
     */

    public Modifier(ModifierPoint mType, int val) {
        type = mType;
        isTemporary = false;
        value = val;
    }

    public Modifier Copy(Modifier prototype) {
        return new Modifier(prototype.type, prototype.isTemporary, prototype.durationBattle, prototype.durationGlobal, prototype.value);
    }

    @Override
    public String toString() {
        if (isTemporary) {
            return String.format("Временный модификатор с типом %s, длительность в бою: %d, длительность на карте: %d, значение: %d; ", type.toString(), durationBattle, durationGlobal, value);
        } else {
            return String.format("Постоянный модификатор с типом %s, значение: %d", type.toString(), value);
        }
    }

    public static String ModifiersArrayToString(ArrayList<Modifier> modifiers) {
        String res = "";
        for(Modifier modifier : modifiers) {
            res += "\t" + modifier.toString() + "\n";
        }
        return res;
    }
}
