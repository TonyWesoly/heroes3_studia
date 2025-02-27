package pl.psi.creatures;

import java.util.Map;

public class NecropolisFactionConfig implements FactionConfig {
    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    private static final Map<Integer, CreatureStatistic> NORMAL_STATS = Map.of(
            1, CreatureStatistic.SKELETON,
            2, CreatureStatistic.WALKING_DEAD,
            3, CreatureStatistic.WIGHT,
            4, CreatureStatistic.VAMPIRE,
            5, CreatureStatistic.LICH,
            6, CreatureStatistic.BLACK_KNIGHT,
            7, CreatureStatistic.BONE_DRAGON
    );

    private static final Map<Integer, CreatureStatistic> UPGRADED_STATS = Map.of(
            1, CreatureStatistic.SKELETON_WARRIOR,
            2, CreatureStatistic.ZOMBIE,
            3, CreatureStatistic.WRAITH,
            4, CreatureStatistic.VAMPIRE_LORD,
            5, CreatureStatistic.POWER_LICH,
            6, CreatureStatistic.DREAD_KNIGHT,
            7, CreatureStatistic.GHOST_DRAGON
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
        if (isUpgraded && tier == 4) {
            return new ResurrectAfterAttackCreature(creature);
        }
        return creature;
    }
}
