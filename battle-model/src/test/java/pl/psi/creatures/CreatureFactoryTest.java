package pl.psi.creatures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class CreatureFactoryTest {

    @Test
    void necropolisFactoryCreatesNormalCreature() {
        // given: using the Necropolis faction configuration
        FactionConfig necroConfig = new NecropolisFactionConfig();
        CreatureFactory factory = new CreatureFactory(necroConfig);
        int amount = 10;

        // when: creating a regular (non-upgraded) tier 1 unit
        Creature creature = factory.create(false, 1, amount);

        // then: for tier 1, we expect a SKELETON unit (according to the mapping)
        assertThat(creature.getName()).isEqualTo(CreatureStatistic.SKELETON.getName());
        assertThat(creature.getAmount()).isEqualTo(amount);
    }

    @Test
    void necropolisFactoryCreatesUpgradedCreatureAndTransformsForTier4() {
        // given: Necropolis configuration and unit factory
        FactionConfig necroConfig = new NecropolisFactionConfig();
        CreatureFactory factory = new CreatureFactory(necroConfig);
        int amount = 5;

        // when: creating an upgraded tier 4 unit
        Creature creature = factory.create(true, 4, amount);

        // then: for upgraded tier 4, we expect the creature to be wrapped in ResurrectAfterAttackCreature
        // and its name to be Vampire Lord (according to the mapping)
        assertThat(creature).isInstanceOf(ResurrectAfterAttackCreature.class);
        assertThat(creature.getName()).isEqualTo(CreatureStatistic.VAMPIRE_LORD.getName());
        assertThat(creature.getAmount()).isEqualTo(amount);
    }

    @Test
    void infernoFactoryCreatesNormalCreature() {
        // given: Inferno configuration
        FactionConfig infernoConfig = new InfernoFactionConfig();
        CreatureFactory factory = new CreatureFactory(infernoConfig);
        int amount = 8;

        // when: creating a regular tier 1 unit
        Creature creature = factory.create(false, 1, amount);

        // then: for tier 1, we expect an IMP unit (according to the Inferno mapping)
        assertThat(creature.getName()).isEqualTo(CreatureStatistic.IMP.getName());
        assertThat(creature.getAmount()).isEqualTo(amount);
    }

    @Test
    void infernoFactoryCreatesUpgradedCreature() {
        // given: Inferno configuration
        FactionConfig infernoConfig = new InfernoFactionConfig();
        CreatureFactory factory = new CreatureFactory(infernoConfig);
        int amount = 3;

        // when: creating an upgraded tier 1 unit
        Creature creature = factory.create(true, 1, amount);

        // then: according to the Inferno mapping for upgraded tier 1, we expect a FAMILIAR unit
        // and the transformation should not wrap the creature
        assertThat(creature.getName()).isEqualTo(CreatureStatistic.FAMILIAR.getName());
        assertThat(creature.getAmount()).isEqualTo(amount);
        assertThat(creature).isNotInstanceOf(ResurrectAfterAttackCreature.class);
    }

    @Test
    void necropolisFactoryThrowsExceptionForInvalidTier() {
        // given: Necropolis configuration
        FactionConfig necroConfig = new NecropolisFactionConfig();
        CreatureFactory factory = new CreatureFactory(necroConfig);

        // when/then: for an unsupported tier (e.g., 8), an appropriate exception should be thrown
        assertThatThrownBy(() -> factory.create(false, 8, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("We support tiers from 1 to 7");
    }

    @Test
    void infernoFactoryThrowsExceptionForInvalidTier() {
        // given: Inferno configuration
        FactionConfig infernoConfig = new InfernoFactionConfig();
        CreatureFactory factory = new CreatureFactory(infernoConfig);

        // when/then: for an unsupported tier (e.g., 9), an exception should be thrown
        assertThatThrownBy(() -> factory.create(false, 9, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("We support tiers from 1 to 7");
    }
}
