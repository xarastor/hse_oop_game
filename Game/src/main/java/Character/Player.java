package Character;

import Ability.AbilityStorage;
import Manager.GameManager;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by titaninus on 14.03.18.
 */
public class Player extends GameCharacter {
    public static final ArrayList<Integer> XPLevels = new ArrayList<Integer>(
            Arrays.asList(0, 100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600, 51200, 102400, 999999999)
    );
    private int Level = 1;
    private int XP = 0;
    private int AbilityPoint = 0;
    private int CharacterPoint = 0;

    public static final int CharacterPointsOnLevelUp = 10;
    public static final int AbilityPointsOnLevelUp = 5;
    public static final int StrengthOnLevelUp = 2;
    public static final int AgilityOnLevelUp = 2;
    public static final int IntelligenceOnLevelUp = 2;
    public static final int WisdomOnLevelUp = 2;
    public static final int LuckOnLevelUp = 1;



    public void AddExperience(int amount) {
        XP = getXP() + amount;
        while (getXP() >= XPLevels.get(getLevel())) {
            Level = getLevel() + 1;
            LevelUp();
        }
    }

    private Inventory inventory = new Inventory(this);

    @Override
    public void onBattleEnd() {
        super.onBattleEnd();
    }

    public void LevelUp() {
        GameManager.getInstance().LevelUp();
        CharacterPoint = getCharacterPoint() + CharacterPointsOnLevelUp;
        AbilityPoint = getAbilityPoint() + AbilityPointsOnLevelUp;
        Strength += StrengthOnLevelUp * (getLevel() / 2 + 1);
        Agility += AgilityOnLevelUp * (getLevel() / 2 + 1);
        Intelligence += IntelligenceOnLevelUp * (getLevel() / 2 + 1);
        Wisdom += WisdomOnLevelUp * (getLevel() / 2 + 1);
        Luck += LuckOnLevelUp;
    }

    public boolean StrengthUp() {
        if (getCharacterPoint() > 0) {
            permamentRaiseStrength(1);
            CharacterPoint = getCharacterPoint() - 1;
            return true;
        } else {
            GameManager.getInstance().NotEnoughCharacterPoints();
            return false;
        }
    }

    public boolean AgilityUp() {
        if (getCharacterPoint() > 0) {
            permamentRaiseAgility(1);
            CharacterPoint = getCharacterPoint() - 1;
            return true;
        } else {
            GameManager.getInstance().NotEnoughCharacterPoints();
            return false;
        }
    }

    public boolean IntelligenceUp() {
        if (getCharacterPoint() > 0) {
            permamentRaiseIntelligence(1);
            CharacterPoint = getCharacterPoint() - 1;
            return true;
        } else {
            GameManager.getInstance().NotEnoughCharacterPoints();
            return false;
        }
    }

    public boolean WisdomUp() {
        if (getCharacterPoint() > 0) {
            permamentRaiseWisdom(1);
            CharacterPoint = getCharacterPoint() - 1;
            return true;
        } else {
            GameManager.getInstance().NotEnoughCharacterPoints();
            return false;
        }
    }

    public int getLevel() {
        return Level;
    }

    public int getXP() {
        return XP;
    }

    public int getAbilityPoint() {
        return AbilityPoint;
    }

    public int getCharacterPoint() {
        return CharacterPoint;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean BuyAbility(int index) {
        if (getAbilityPoint() > 0) {
            if (!getAbilities().contains(index)) {
                if (AbilityStorage.Abilities.containsKey(index)) {
                    if (AbilityStorage.Abilities.get(index).Level <= Level) {
                        addAbility(index);
                        AbilityPoint = getAbilityPoint() - AbilityStorage.Abilities.get(index).Level;
                        return true;
                    } else {
                        GameManager.getInstance().TooLowLevelForAbility();
                        return false;
                    }
                } else {
                    GameManager.getInstance().AbilityDoesntExist();
                    return false;
                }
            } else {
                GameManager.getInstance().AlreadyHaveAbility();
                return false;
            }
        } else {
            GameManager.getInstance().NotEnoughAbilityPoints();
            return false;
        }
    }
}
