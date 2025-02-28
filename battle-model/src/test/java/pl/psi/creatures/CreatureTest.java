package pl.psi.creatures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import pl.psi.TurnQueue;

import com.google.common.collect.Range;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Disabled
public class CreatureTest
{
    private static final int NOT_IMPORTANT = 100;
    private static final Range< Integer > NOT_IMPORTANT_DMG = Range.closed( 0, 0 );

    private static final int BASE_HP = 100;

    @Test
    void noCounterattackCreatureAttackingShouldNotTriggerCounterattack() {
        // given: creating a base attacker who would normally receive a counterattack,
        // but wrapping it in NoCounterattackCreature because attackers do not trigger counterattacks.
        Creature baseAttacker = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(BASE_HP)
                        .damage(Range.closed(10, 10))
                        .attack(50)
                        .armor(10)
                        .build())
                .amount(1)
                .build();
        baseAttacker.restoreCurrentHpToMax();
        Creature noCounterAttacker = new NoCounterattackCreature(baseAttacker);

        // defender – a normal unit that would counterattack if the attacker had no special ability.
        Creature defender = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(50)
                        .damage(Range.closed(10, 10))
                        .attack(10)
                        .build())
                .amount(1)
                .build();
        defender.restoreCurrentHpToMax();

        // when: noCounterAttacker attacks the defender
        noCounterAttacker.attack(defender);

        // then: when the attacker (NoCounterattackCreature) performs an attack, the defender's counterattack is not triggered.
        // As a result, the attacker should retain full HP (i.e., BASE_HP).
        assertThat(noCounterAttacker.getCurrentHp()).isEqualTo(BASE_HP);
    }

    @Test
    void noCounterattackCreatureDefendingShouldCounterattackNormally() {

        // given: normal attacker
        Creature attacker = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(BASE_HP)
                        .damage(Range.closed(10, 10))
                        .attack(50)
                        .armor(10)
                        .build())
                .amount(1)
                .build();
        attacker.restoreCurrentHpToMax();

        // given: defender – setting higher maxHp so the attack does not kill the unit
        Creature baseDefender = new Creature.Builder()
                .statistic(CreatureStats.builder()
                        .maxHp(100)   // increased HP so the unit survives the attack
                        .damage(Range.closed(10, 10))
                        .attack(10)
                        .build())
                .amount(1)
                .build();
        baseDefender.restoreCurrentHpToMax();
        Creature noCounterDefender = new NoCounterattackCreature(baseDefender);

        // when: attacker attacks noCounterDefender
        attacker.attack(noCounterDefender);

        // then: as a result of the defender's counterattack (which works normally when defending),
        // we should observe a 10 HP loss for the attacker.
        assertThat(attacker.getCurrentHp()).isEqualTo(BASE_HP - 10);
    }

    @Test
    void creatureShouldAttackProperly()
    {
        // given
        final Creature angel = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( NOT_IMPORTANT )
            .damage( Range.closed( 10, 10 ) )
            .attack( 50 )
            .armor( NOT_IMPORTANT )
            .build() )
            .build();
        final Creature dragon = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( NOT_IMPORTANT_DMG )
            .attack( NOT_IMPORTANT )
            .armor( 10 )
            .build() )
            .build();
        // when
        angel.attack( dragon );
        // then
        assertThat( dragon.getCurrentHp() ).isEqualTo( 70 );
    }

    @Test
    void creatureShouldNotHealCreatureEvenHasLowerAttackThanDefenderArmor()
    {
        final Creature angel = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( NOT_IMPORTANT )
            .damage( NOT_IMPORTANT_DMG )
            .attack( 1 )
            .armor( NOT_IMPORTANT )
            .build() )
            .build();
        final Creature dragon = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( NOT_IMPORTANT_DMG )
            .attack( NOT_IMPORTANT )
            .armor( 10 )
            .build() )
            .build();
        // when
        angel.attack( dragon );
        // then
        assertThat( dragon.getCurrentHp() ).isEqualTo( 100 );
    }

    @Test
    void defenderShouldCounterAttack()
    {
        final Creature attacker = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( NOT_IMPORTANT_DMG )
            .attack( NOT_IMPORTANT )
            .armor( 10 )
            .build() )
            .build();
        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( NOT_IMPORTANT )
            .damage( Range.closed( 10, 10 ) )
            .attack( 10 )
            .build() )
            .build();
        // when
        attacker.attack( defender );
        // then
        assertThat( attacker.getCurrentHp() ).isEqualTo( 90 );
    }

    @Test
    void defenderShouldNotCounterAttackWhenIsDie()
    {
        final Creature attacker = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( NOT_IMPORTANT_DMG )
            .attack( 1000 )
            .armor( 10 )
            .build() )
            .build();
        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( NOT_IMPORTANT )
            .damage( NOT_IMPORTANT_DMG )
            .attack( 20 )
            .armor( 5 )
            .build() )
            .build();
        // when
        attacker.attack( defender );
        // then
        assertThat( attacker.getCurrentHp() ).isEqualTo( 100 );
    }

    @Test
    void defenderShouldCounterAttackOnlyOncePerTurn()
    {
        final Creature attacker = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( NOT_IMPORTANT_DMG )
            .attack( NOT_IMPORTANT )
            .armor( 10 )
            .build() )
            .build();

        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( NOT_IMPORTANT )
            .damage( Range.closed( 10, 10 ) )
            .attack( 10 )
            .build() )
            .build();

        // when
        attacker.attack( defender );
        attacker.attack( defender );
        // then
        assertThat( attacker.getCurrentHp() ).isEqualTo( 90 );
    }

    @Test
    void counterAttackCounterShouldResetAfterEndOfTurn()
    {
        final Creature attacker = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( NOT_IMPORTANT_DMG )
            .build() )
            .build();

        final Creature defender = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( Range.closed( 10, 10 ) )
            .build() )
            .build();

        final TurnQueue turnQueue = new TurnQueue( List.of( attacker ), List.of( defender ) );

        attacker.attack( defender );
        attacker.attack( defender );
        assertThat( attacker.getCurrentHp() ).isEqualTo( 90 );
        turnQueue.next();
        turnQueue.next();
        attacker.attack( defender );
        assertThat( attacker.getCurrentHp() ).isEqualTo( 80 );
        // end of turn
    }

    @Test
    void creatureShouldResurrect()
    {
        Creature VampireLord = new CreatureFactory(new NecropolisFactionConfig()).create(true, 4, 10);

        final Creature dragon = new Creature.Builder().statistic( CreatureStats.builder()
                        .maxHp( NOT_IMPORTANT )
                        .damage( Range.closed( 10, 10 ) )
                        .attack( 30 )
                        .armor( 10 )
                        .build() )
                .build();

        int initialAmount = VampireLord.getAmount();
        VampireLord.setCurrentHp(20);
        //wykorzystac stałą, get mozę popsuć
        int initialHp = VampireLord.getCurrentHp();

        VampireLord.attack(dragon);


        assertThat(VampireLord.getAmount()).isEqualTo(initialAmount);
        assertThat(VampireLord.getCurrentHp()).isGreaterThan(initialHp);

    }

    // zapytac
    @Test
    void creatureShouldNotResurrectIfAttacksUndead()
    {
        CreatureFactory necroFactory = new CreatureFactory(new NecropolisFactionConfig());
        Creature VampireLord =
                necroFactory.create(true, 4, 1);
        Creature Zombie = necroFactory.create(true, 2, 30);

        int initialAmount = VampireLord.getAmount();

        VampireLord.attack(Zombie);

        assertThat(VampireLord.getAmount()).isLessThan( initialAmount ); // zly zapis -> konkreten wartosci jakich sie spodziewam
        // mozna podejrzec w debugu wartosc

    }

    @Test
    void creatureShouldHealAfterEndOfTurn()
    {
        final Creature attacker = new Creature.Builder().statistic( CreatureStats.builder()
            .maxHp( 100 )
            .damage( Range.closed( 10, 10 ) )
            .build() )
            .build();

        final Creature selfHealAfterEndOfTurnCreature = new SelfHealAfterTurnCreature( new Creature.Builder()
            .statistic( CreatureStats.builder()
                    .maxHp( NOT_IMPORTANT )
                    .damage( Range.closed( 10, 10 ) )
                    .attack( 50 )
                    .armor( NOT_IMPORTANT )
                    .build() )
                .build());

        final TurnQueue turnQueue =
            new TurnQueue( List.of( attacker ), List.of( selfHealAfterEndOfTurnCreature ) );

        attacker.attack( selfHealAfterEndOfTurnCreature );
        assertThat( selfHealAfterEndOfTurnCreature.getCurrentHp() ).isEqualTo( 90 );
        turnQueue.next();
        turnQueue.next();
        assertThat( selfHealAfterEndOfTurnCreature.getCurrentHp() ).isEqualTo( 100 );
    }
}
