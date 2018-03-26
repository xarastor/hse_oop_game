package com.Game.Character;

import com.Game.Manager.IEventable;

import java.util.ArrayList;


/**
 * Класс реализующий базовый функционал игровых персонажей
 * @author titaninus
 * @version 1.1
 */
public class GameCharacter implements IEventable {

    /*
     * Basic characterictics
     */
    /** Поле показателя силы */
    protected int Strength;

    /** Поле показателя ловкости */
    protected int Agility;

    /** Поле показателя интеллекта */
    protected int Intelligence;

    /** Поле показателя мудрости */
    protected int Wisdom;

    /** Поле показателя удачи */
    protected int Luck;

    /** Поле изменения силы при использовании модификаторов */
    protected int ModifyStrength;

    /** Поле изменения ловкости при использовании модификаторов */
    protected int ModifyAgility;

    /** Поле изменения интеллекта при использовании модификаторов */
    protected int ModifyIntelligence;

    /** Поле изменения мудрости при использовании модификаторов */
    protected int ModifyWisdom;

    /** Поле изменения удачи при использовании модификаторов */
    protected int ModifyLuck;

    /**
     * Геттер для силы
     * @return текущий показатель силы с учетом модификаторов
     */
    public int getStrength() {
        return Strength + ModifyStrength;
    }

    /**
     * Геттер для ловкоси
     * @return текущий показатель ловкости с учетом модификаторов
     */
    public int getAgility() {
        return Agility + ModifyAgility;
    }

    /**
     * Геттер для интеллекта
     * @return текущий показатель интеллекта с учетом модификаторов
     */
    public int getIntelligence() {
        return Intelligence + ModifyIntelligence;
    }

    /**
     * Геттер для мудрости
     * @return текущий показатель мудрости с учетом модификаторов
     */
    public int getWisdom() {
        return Wisdom + ModifyWisdom;
    }

    /**
     * Геттер для удачи
     * @return текущий показатель удачи с учетом модификаторов
     */
    public int getLuck() {
        return Luck + ModifyLuck;
    }

    /**
     * Функция постоянного увеличения силы
     * @param amount - количество на которое необходимо увеличить
     */
    public void permamentRaiseStrength(int amount) {
        Strength = Strength + amount;
    }

    /**
     * Функция постоянного увеличения ловкости
     * @param amount - количество на которое необходимо увеличить
     */
    public void permamentRaiseAgility(int amount) {
        Agility = Agility + amount;
    }

    /**
     * Функция постоянного увеличения интеллекта
     * @param amount - количество на которое необходимо увеличить
     */
    public void permamentRaiseIntelligence(int amount) {
        Intelligence = Intelligence + amount;
    }

    /**
     * Функция постоянного увеличения мудрости
     * @param amount - количество на которое необходимо увеличить
     */
    public void permamentRaiseWisdom(int amount) {
        Wisdom = Wisdom + amount;
    }



    /*
     * Advanced characteristics
     */

    /** Поле показателя текущего здоровья */
    protected int CurrentHealth;

    /** Поле показателя текущей маны */
    protected int CurrentMana;

    /** Поле показателя текущей выносливости */
    protected int CurrentStamina;

    /** Поле изменения регенерации здоровья при использовании модификаторов */
    protected int ModifyHealthRegen;

    /** Поле изменения регенерации маны при использовании модификаторов */
    protected int ModifyManaRegen;

    /** Поле изменения регенерации выносливости при использовании модификаторов */
    protected int ModifyStaminaRegen;

    /**
     * Геттер для максимальных очков здоровья
     * @return максимальный показатель здоровья с учетом модификаторов
     */
    public int getHealthPoints() {
        return getStrength() * 10 + getAgility() * 5 + getWisdom() * 2;
    }

    /**
     * Геттер для максимальных очков маны
     * @return максимальный показатель маны с учетом модификаторов
     */
    public int getManaPoints() {
        return getWisdom() * 20 + getIntelligence() * 10;
    }

    /**
     * Геттер для максимальных очков выносливости
     * @return максимальный показатель выносливости с учетом модификаторов
     */
    public int getStaminaPoints() {
        return getAgility() * 10 + getStrength() * 5 + getIntelligence() * 2;
    }

    /**
     * Геттер для регенерации здоровья
     * @return текущий показатель регенерации здоровья с учетом модификаторов
     */
    public int getHealthRegen() {
        return getWisdom() * 2 + getStrength() + ModifyHealthRegen;
    }

    /**
     * Геттер для регенерации маны
     * @return текущий показатель регенерации маны с учетом модификаторов
     */
    public int getManaRegen() {
        return getWisdom() * 2 + getIntelligence() + ModifyManaRegen;
    }

    /**
     * Геттер для регенерации выносливости
     * @return текущий показатель регенерации выносливости с учетом модификаторов
     */
    public int getStaminaRegen() {
        return getAgility() * 2 + getIntelligence() + ModifyStaminaRegen;
    }

    /**
     * Геттер для текущего здоровья
     * @return текущий показатель здоровья с учетом модификаторов
     */
    public int getCurrentHealth() {
        return CurrentHealth;
    }

    /**
     * Геттер для текущей маны
     * @return текущий показатель маны с учетом модификаторов
     */
    public int getCurrentMana() {
        return CurrentMana;
    }

    /**
     * Геттер для текущей выносливости
     * @return текущий показатель выносливости с учетом модификаторов
     */
    public int getCurrentStamina() {
        return CurrentStamina;
    }

    /*
     * Modifiers functionality
     */

    /** Поле модификаторов примененных к персонажу */
    private ArrayList<Modifier> modifiers = new ArrayList<Modifier>();

    /** Геттер для модификаторов примененных к персонажу */
    public ArrayList<Modifier> getModifiers() {
        return modifiers;
    }

    /**
     * Функция обновляющая состояние здоровья, маны и выносливости игрока, согласно максимальным показателям
     */
    protected void CharacterRefresh() {
        if (getCurrentHealth() > getHealthPoints()) {
            CurrentHealth = getHealthPoints();
        }
        if (getCurrentMana() > getManaPoints()) {
            CurrentMana = getManaPoints();
        }
        if (getCurrentStamina() > getStaminaPoints()) {
            CurrentStamina = getStaminaPoints();
        }
    }

    /**
     * Внешняя функция (например для инвентаря) удаляющая влияние модификатора с персонажа
     * @param modifier - удаляемый модификатор
     */
    public void removeModifierExternal(Modifier modifier) {
        if (!modifiers.contains(modifier)) {
            return;
        }
        modifiers.remove(modifier);
        removeModifier(modifier);
        CharacterRefresh();
    }

    /**
     * Функция удаляющая влияние модификаторов на персонажа
     * @param modifier - удаляемый модификатор
     */
    protected void removeModifier(Modifier modifier) {
        switch (modifier.type) {
            case Agility:
                ModifyAgility -= modifier.value;
                break;
            case Strength:
                ModifyStrength -= modifier.value;
                break;
            case Intelligence:
                ModifyIntelligence -= modifier.value;
                break;
            case Wisdom:
                ModifyWisdom -= modifier.value;
                break;
            case HealthRegen:
                ModifyHealthRegen -= modifier.value;
                break;
            case ManaRegen:
                ModifyManaRegen -= modifier.value;
                break;
            case StaminaRegen:
                ModifyStaminaRegen -= modifier.value;
                break;
            case Luck:
                ModifyLuck -= modifier.value;
                break;
        }
        CharacterRefresh();
    }

    /**
     * Функция добавляющая модификатор к персонажу
     * @param modifier - добавляемый модификатор
     * @return возвращает успешность добавления модификатора (согласно его применимости в текущей ситуации)
     */
    public boolean addModifier(Modifier modifier) {
        if (modifier.isTemporary) {
            if (!inBattle && modifier.durationGlobal <= 0) {
                return false;
            }
        }
        switch (modifier.type) {
            case Agility:
                ModifyAgility += modifier.value;
                break;
            case Strength:
                ModifyStrength += modifier.value;
                break;
            case Intelligence:
                ModifyIntelligence += modifier.value;
                break;
            case Wisdom:
                ModifyWisdom += modifier.value;
                break;
            case HealthRegen:
                ModifyHealthRegen += modifier.value;
                break;
            case ManaRegen:
                ModifyManaRegen += modifier.value;
                break;
            case StaminaRegen:
                ModifyStaminaRegen += modifier.value;
                break;
            case Luck:
                ModifyLuck += modifier.value;
                break;
        }
        modifiers.add(modifier);
        return true;
    }


    /*
     * Ability functionality
     */

    /** Поле навыков доступных персонажу */
    protected ArrayList<Integer> Abilities = new ArrayList<Integer>();

    /** Геттер навыков персонажа */
    public ArrayList<Integer> getAbilities() {
        return Abilities;
    }

    /**
     * Функция добавляющая навык персонажу
     * @param index - идентификатор навыка
     */
    protected void addAbility(int index) {
        Abilities.add(index);
    }

    /*
     * Events for updating characteristics
     */

    /** Поле присутствия персонажа в режиме битвы */
    protected boolean inBattle;

    /**
     * Функция реализующая метод интерфейса IEventable
     * @see IEventable#onGlobalTurn()
     */
    public void onGlobalTurn() {
        for (int i = 0; i < modifiers.size(); ++i) {
            Modifier modifier = modifiers.get(i);
            if (modifier.isTemporary) {
                modifier.durationGlobal -= 1;
                if (modifier.durationGlobal <= 0) {
                    removeModifier(modifier);
                    modifiers.remove(i);
                    i--;
                }
            }
        }
        CurrentHealth = Math.min(getHealthPoints(), CurrentHealth + 2 * getHealthRegen());
        CurrentMana = Math.min(getManaPoints(), CurrentMana + 2 * getManaRegen());
        CurrentStamina = Math.min(getStaminaPoints(), CurrentStamina + 2 * getStaminaRegen());

    }

    /**
     * Функция реализующая метод интерфейса IEventable
     * @see IEventable#onBattleTurn()
     */
    public void onBattleTurn() {
        if (inBattle) {
            for (int i = 0; i < modifiers.size(); ++i) {
                Modifier modifier = modifiers.get(i);
                if (modifier.isTemporary) {
                    if (modifier.durationBattle > 0) {
                        modifier.durationBattle -= 1;
                    }
                    if (modifier.durationBattle <= 0) {
                        removeModifier(modifier);
                        modifiers.remove(i);
                        i--;
                    }
                }
            }
        }
        CurrentHealth = Math.min(getHealthPoints(), CurrentHealth + getHealthRegen());
        CurrentMana = Math.min(getManaPoints(), CurrentMana + getManaRegen());
        CurrentStamina = Math.min(getStaminaPoints(), CurrentStamina + getStaminaRegen());
    }

    /**
     * Функция реализующая метод интерфейса IEventable
     * @see IEventable#onBattleStart()
     */
    public void onBattleStart() {
        inBattle = true;
    }

    /**
     * Функция реализующая метод интерфейса IEventable
     * @see IEventable#onBattleEnd()
     */
    public void onBattleEnd() {
        for (int i = 0; i < modifiers.size(); ++i) {
            Modifier modifier = modifiers.get(i);
            if (modifier.isTemporary) {
                if (modifier.durationGlobal <= 0) {
                    removeModifier(modifier);
                    modifiers.remove(i);
                    i--;
                }
            }
        }
        inBattle = false;
    }



    /*
     * Battle functionality
     */

    /**
     * Функция наносящая урон персонажу
     * @param amount - количество урона
     * @return возвращает остается ли жив персонаж после нанесения урона
     */
    public boolean Damage(int amount) {
        CurrentHealth -= amount;
        return CurrentHealth > 0;
    }

    /**
     * Функция отнимающая ману у персонажа
     * @param amount - количество маны, которое будет отнято
     */
    public void SpendMana(int amount) {
        CurrentMana -= amount;
    }

    /**
     * Функция отнимающая выносливость у персонажа
     * @param amount - количество выносливости, которое будет отнято
     */
    public void SpendStamina(int amount) {
        CurrentStamina -= amount;
    }

    /*
     * Constructors
     */

    /**
     * Констуктор для класса
     */
    public GameCharacter() {
        Strength = 10;
        Agility = 10;
        Intelligence = 5;
        Wisdom = 5;
        Luck = 2;
        CurrentHealth = getHealthPoints();
        CurrentMana = getManaPoints() / 2;
        CurrentStamina = getStaminaPoints() / 2;
        addAbility(0);
        addAbility(15);
    }
}
