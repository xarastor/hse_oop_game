import java.util.ArrayList;
import java.util.List;

/**
 * Created by titaninus on 14.03.18.
 */
public class Character extends IEventable{
    /**
     * Basic characterictics
     */

    private int Strength;
    private int Agility;
    private int Intelligence;
    private int Wisdom;
    private int Luck;

    private int ModifyStrength;
    private int ModifyAgility;
    private int ModifyIntelligence;
    private int ModifyWisdom;
    private int ModifyLuck;

    public int getStrength() {
        return Strength + ModifyStrength;
    }

    public int getAgility() {
        return Agility + ModifyAgility;
    }

    public int getIntelligence() {
        return Intelligence + ModifyIntelligence;
    }

    public int getWisdom() {
        return Wisdom + ModifyWisdom;
    }

    public int getLuck() {
        return Luck + ModifyLuck;
    }

    public void permamentRaiseStrength(int amount) {
        Strength = Strength + amount;
    }

    public void permamentLowerStrength(int amount) {
        Strength = Math.max(Strength - amount, 0);
    }

    public void permamentRaiseAgility(int amount) {
        Agility = Agility + amount;
    }

    public void permamentLowerAgility(int amount) {
        Agility = Math.max(Agility - amount, 0);
    }

    public void permamentRaiseIntelligence(int amount) {
        Intelligence = Intelligence + amount;
    }

    public void permamentLowerIntelligence(int amount) {
        Intelligence = Math.max(Intelligence - amount, 0);
    }

    public void permamentRaiseWisdom(int amount) {
        Wisdom = Wisdom + amount;
    }

    public void permamentLowerWisdom(int amount) {
        Wisdom = Math.max(Wisdom - amount, 0);
    }



    /**
     * Advanced characteristics
     */
    private int HealthPoints;
    private int ManaPoints;
    private int StaminaPoints;
    private int HealthRegen;
    private int ManaRegen;
    private int StaminaRegen;
    private int CurrentHealth;
    private int CurrentMana;
    private int CurrentStamina;
    private int ModifyHealthRegen;
    private int ModifyManaRegen;
    private int ModifyStaminaRegen;

    public int getHealthPoints() {
        return getStrength() * 10 + getAgility() * 5 + getWisdom() * 2;
    }

    public int getManaPoints() {
        return getWisdom() * 20 + getIntelligence() * 10;
    }

    public int getStaminaPoints() {
        return getAgility() * 10 + getStrength() * 5 + getIntelligence() * 2;
    }

    public int getHealthRegen() {
        return getWisdom() * 2 + getStrength();
    }

    public int getManaRegen() {
        return getWisdom() * 2 + getIntelligence();
    }

    public int getStaminaRegen() {
        return getAgility() * 2 + getIntelligence();
    }

    public int getCurrentHealth() {
        return CurrentHealth;
    }

    public int getCurrentMana() {
        return CurrentMana;
    }

    public int getCurrentStamina() {
        return CurrentStamina;
    }

    /**
     * Modifiers functionality
     */

    private ArrayList<Modifier> modifiers = new ArrayList<Modifier>();

    public ArrayList<Modifier> getModifiers() {
        return modifiers;
    }

    private void removeModifier(Modifier modifier) {
        switch (modifier.type) {
            case Agility:
                ModifyAgility -= modifier.value;
            case Strength:
                ModifyStrength -= modifier.value;
            case Intelligence:
                ModifyIntelligence -= modifier.value;
            case Wisdom:
                ModifyWisdom -= modifier.value;
            case HealthRegen:
                ModifyHealthRegen -= modifier.value;
            case ManaRegen:
                ModifyManaRegen -= modifier.value;
            case StaminaRegen:
                ModifyStaminaRegen -= modifier.value;
            case Luck:
                ModifyLuck -= modifier.value;
        }
    }

    public boolean addModifier(Modifier modifier) {
        if (!inBattle && modifier.durationGlobal <= 0) {
            return false;
        }
        switch (modifier.type) {
            case Agility:
                ModifyAgility += modifier.value;
            case Strength:
                ModifyStrength += modifier.value;
            case Intelligence:
                ModifyIntelligence += modifier.value;
            case Wisdom:
                ModifyWisdom += modifier.value;
            case HealthRegen:
                ModifyHealthRegen += modifier.value;
            case ManaRegen:
                ModifyManaRegen += modifier.value;
            case StaminaRegen:
                ModifyStaminaRegen += modifier.value;
            case Luck:
                ModifyLuck += modifier.value;
        }
        modifiers.add(modifier);
        return true;
    }


    /**
     * Events for updating characteristics
     */

    private boolean inBattle;

    @Override
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
        CurrentHealth = Math.min(getHealthPoints(), CurrentHealth + 10 * getHealthRegen());
        CurrentMana = Math.min(getManaPoints(), CurrentMana + 10 * getManaRegen());
        CurrentStamina = Math.min(getStaminaPoints(), CurrentStamina + 10 * getStaminaRegen());

    }
    @Override
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
    @Override
    public void onBattleStart() {
        inBattle = true;
    }
    @Override
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



    /**
     * Battle functionality
     */

    public boolean Damage(int amount) {
        CurrentHealth -= amount;
        return CurrentHealth > 0;
    }

}
