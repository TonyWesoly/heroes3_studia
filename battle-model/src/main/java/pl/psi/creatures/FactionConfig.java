package pl.psi.creatures;

public interface FactionConfig {
    CreatureStatistic getStatistic(boolean isUpgraded, int tier);


    default Creature transform(Creature creature, boolean isUpgraded, int tier) {
        return creature;
    }
}
