package pl.psi.creatures;

public class CreatureFactory {
    private final FactionConfig factionConfig;

    public CreatureFactory(FactionConfig factionConfig) {
        this.factionConfig = factionConfig;
    }

    public Creature create(boolean isUpgraded, int tier, int amount) {
        CreatureStatistic stat = factionConfig.getStatistic(isUpgraded, tier);
        Creature creature = new Creature.Builder()
                .statistic(stat)
                .amount(amount)
                .build();
        return factionConfig.transform(creature, isUpgraded, tier);
    }
}
