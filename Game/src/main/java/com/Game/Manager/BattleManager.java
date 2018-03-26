package com.Game.Manager;

import com.Game.Character.*;
import com.Game.Ability.*;
import com.Game.Ability.Ability;
import com.Game.Ability.AbilityStorage;
import com.Game.Character.GameCharacter;
import com.Game.Character.Modifier;
import com.Game.Character.Monster;
import com.Game.Character.MonsterStorage;

import java.util.Random;

public class BattleManager {

    public double criticalChance = 9;
    public Monster Enemy;
    Random random = new Random();
    public void StartBattle(int monsterIndex) {
        Enemy = MonsterStorage.Monsters.get(monsterIndex).Copy();
        Enemy.onBattleStart();
        GameManager.getInstance().player.onBattleStart();
        GameManager.getInstance().onBattleStart();
    }

    public void onBattleTurn() {
        Enemy.onBattleTurn();
        GameManager.getInstance().player.onBattleTurn();
    }

    public void onBattleEnd() {
        Enemy.onBattleEnd();
        GameManager.getInstance().player.onBattleEnd();
    }

    public void EnemyTurn() {
        int amountAbilities = Enemy.getAbilities().size();
        int index = random.nextInt(amountAbilities);
        Integer ability = Enemy.getAbilities().get(index);
        if (ApplyAbility(ability, Enemy, GameManager.getInstance().player)) {
            GameManager.getInstance().renderer.EnemyCast(AbilityStorage.Abilities.get(ability));
            if (GameManager.getInstance().player.getCurrentHealth() <= 0) {
                GameManager.getInstance().renderer.PlayerDied();
                GameManager.getInstance().LoseGame();
                onBattleEnd();
            }
        } else {
            GameManager.getInstance().renderer.EnemyFailCast();
        }

    }

    public boolean ApplyAbility(int abilityIndex, GameCharacter caster, GameCharacter target) {
        Ability ability = AbilityStorage.Abilities.get(abilityIndex);
        boolean isCritical = (random.nextDouble() * caster.getLuck()) > criticalChance;
        switch (ability.Type) {
            case SelfImposed: {
                if (caster.getCurrentHealth() > ability.HealthCost &&
                        caster.getCurrentMana() > ability.ManaCost &&
                        caster.getCurrentStamina() > ability.StaminaCost) {
                    caster.Damage(ability.HealthCost);
                    caster.SpendMana(ability.ManaCost);
                    caster.SpendStamina(ability.StaminaCost);
                    for(Modifier modifier : ability.Modifiers) {
                        caster.addModifier(Modifier.Copy(modifier));
                    }
                    return true;
                }
                return false;

            }
            case EnemyImposed: {
                if (caster.getCurrentHealth() > ability.HealthCost &&
                        caster.getCurrentMana() > ability.ManaCost &&
                        caster.getCurrentStamina() > ability.StaminaCost) {
                    caster.Damage(ability.HealthCost);
                    caster.SpendMana(ability.ManaCost);
                    caster.SpendStamina(ability.StaminaCost);
                    for(Modifier modifier : ability.Modifiers) {
                        target.addModifier(Modifier.Copy(modifier));
                    }
                    target.Damage(ability.Damage * (isCritical ? 3: 1));
                    return true;
                }
                return false;
            }
        }
        return false;
    }

}
