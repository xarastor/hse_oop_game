import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by titaninus on 14.03.18.
 */
public class Player extends Character {
    private static final ArrayList<Integer> XPLevels = new ArrayList<Integer>(
            Arrays.asList(100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600, 51200, 102400)
    );
    private int Level = 1;
    private int XP = 0;
    private int AbilityPoint = 0;
    private int CharacterPoint = 0;


}
