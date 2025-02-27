package pl.psi.creatures;

import java.util.Map;

public class InfernoFactionConfig implements FactionConfig {
    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    private static final Map<Integer, CreatureStatistic> NORMAL_STATS = Map.of(
            1, CreatureStatistic.IMP,
            2, CreatureStatistic.GOG,
            3, CreatureStatistic.HELL_HOUND,
            4, CreatureStatistic.DEMON,
            5, CreatureStatistic.PIT_FIEND,
            6, CreatureStatistic.EFREETI,
            7, CreatureStatistic.DEVIL
    );

    private static final Map<Integer, CreatureStatistic> UPGRADED_STATS = Map.of(
            1, CreatureStatistic.FAMILIAR,
            2, CreatureStatistic.MAGOG,
            3, CreatureStatistic.CERBERUS,
            4, CreatureStatistic.HORNED_DEMON,
            5, CreatureStatistic.PIT_LORD,
            6, CreatureStatistic.EFREET_SULTAN,
            7, CreatureStatistic.ARCH_DEVIL
    );

    @Override
    public CreatureStatistic getStatistic(boolean isUpgraded, int tier) {
        CreatureStatistic stat = isUpgraded ? UPGRADED_STATS.get(tier) : NORMAL_STATS.get(tier);
        if (stat == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        return stat;
    }

    @Override
    public Creature transform(Creature creature, boolean isUpgraded, int tier) {
        return creature;
    }
}