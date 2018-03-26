package com.Game.Manager;

import com.Game.Ability.Ability;
import com.Game.Ability.AbilityStorage;
import com.Game.Character.GameCharacter;
import com.Game.Character.Modifier;
import com.Game.Character.Monster;
import com.Game.Character.MonsterStorage;

import java.util.Random;

/**
 * Менеджер битвы
 * @author titaninus
 * @version 1.1
 */
public class BattleManager {

    /** Вероятность срабатывания критического удара */
    public double criticalChance = 9;

    /** Монстр с которым происходит сражение */
    public Monster Enemy;

    /** Генератор рандомных чисел */
    Random random = new Random();

    /**
     * Начало битвы
     * @param monsterIndex - идентификатор монстра с которым будет происходить битва
     */
    public void StartBattle(int monsterIndex) {
        Enemy = MonsterStorage.Monsters.get(monsterIndex).Copy();
        Enemy.onBattleStart();
        GameManager.getInstance().player.onBattleStart();
        GameManager.getInstance().onBattleStart();
    }

    /**
     * Действие на переключение хода
     */
    public void onBattleTurn() {
        Enemy.onBattleTurn();
        GameManager.getInstance().player.onBattleTurn();
    }

    /**
     * Действие на конец битвы
     */
    public void onBattleEnd() {
        Enemy.onBattleEnd();
        GameManager.getInstance().player.onBattleEnd();
    }

    /**
     * Реализация хода противника в битве
     */
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

    /**
     * Применение навыка в битве
     * @param abilityIndex - идентификатор навыка
     * @param caster - кто исполнил навык
     * @param target - цель навыка
     * @return Возвращает успешность применения навыка
     */
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
