package com.Game.Character;

import com.Game.Ability.AbilityStorage;
import com.Game.Manager.GameManager;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс игрока
 * @author titaninus
 * @version 1.1
 */
public class Player extends GameCharacter {
    /** Статическое поле границ уровней игрока */
    public static final ArrayList<Integer> XPLevels = new ArrayList<Integer>(
            Arrays.asList(0, 100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600, 51200, 102400, 999999999)
    );

    /** Статическое поле увеличения очков характеристик игрока при повышении уровня игрока */
    public static final int CharacterPointsOnLevelUp = 10;

    /** Статическое поле увеличения очков навыков игрока при повышении уровня игрока */
    public static final int AbilityPointsOnLevelUp = 5;

    /** Статическое поле увеличения очков силы игрока при повышении уровня игрока */
    public static final int StrengthOnLevelUp = 2;

    /** Статическое поле увеличения очков ловкости игрока при повышении уровня игрока */
    public static final int AgilityOnLevelUp = 2;

    /** Статическое поле увеличения очков интеллекта игрока при повышении уровня игрока */
    public static final int IntelligenceOnLevelUp = 2;

    /** Статическое поле увеличения очков мудрости игрока при повышении уровня игрока */
    public static final int WisdomOnLevelUp = 2;

    /** Статическое поле увеличения очков удачи игрока при повышении уровня игрока */
    public static final int LuckOnLevelUp = 1;

    /** Поле уровня игрока */
    private int Level = 1;

    /** Поле опыта игрока */
    private int XP = 0;

    /** Поле свободных очков навыков игрока */
    private int AbilityPoint = 0;

    /** Поле свободных очков характеристик игрока */
    private int CharacterPoint = 0;

    /**
     * Геттер для уровня игрока
     * @return возвращает уровень игрока
     */
    public int getLevel() {
        return Level;
    }

    /**
     * Геттер для опыта игрока
     * @return возвращает опыт игрока
     */
    public int getXP() {
        return XP;
    }

    /**
     * Геттер для очков навыков игрока
     * @return возвращает очки навыка игрока
     */
    public int getAbilityPoint() {
        return AbilityPoint;
    }

    /**
     * Геттер для очков характеристик игрока
     * @return возвращает очки характеристик игрока
     */
    public int getCharacterPoint() {
        return CharacterPoint;
    }

    /**
     * Функция добавления опыта игроку
     * @param amount - количество опыта для добавления
     */
    public void AddExperience(int amount) {
        XP = getXP() + amount;
        while (getXP() >= XPLevels.get(getLevel())) {
            Level = getLevel() + 1;
            LevelUp();
        }
    }

    /**
     * Функция повышения характеристик при повышении уровня игрока
     */
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

    /**
     * Функция увеличения силы игрока на 1
     * @return возвращает успешность увеличения
     */
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

    /**
     * Функция увеличения ловкости игрока на 1
     * @return возвращает успешность увеличения
     */
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

    /**
     * Функция увеличения интеллекта игрока на 1
     * @return возвращает успешность увеличения
     */
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

    /**
     * Функция увеличения мудрости игрока на 1
     * @return возвращает успешность увеличения
     */
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


    /** Поле инвентаря игрока */
    private Inventory inventory = new Inventory(this);

    /**
     * Геттер для инвентаря игрока
     * @return инвентарь игрока
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Функция покупки навыков игрока
     * @param index - идентификатор навыка
     * @return успешность покупки навыка
     */
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
