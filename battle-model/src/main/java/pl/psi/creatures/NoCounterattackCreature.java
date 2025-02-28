package pl.psi.creatures;

public class NoCounterattackCreature extends Creature {

    public NoCounterattackCreature(Creature base) {
        super(base.getStats(), base.getCalculator(), base.getAmount());
        setCurrentHp(base.getCurrentHp());
    }

    @Override
    public void attack(final Creature aDefender) {
        if (isAlive()) {
            final int damage = getCalculator().calculateDamage(this, aDefender);
            final int damageWithBonus = getAttackWithBonus();
            System.out.println("Damage: " + damage + "\nDamage with attack bonus: " + damageWithBonus);
            aDefender.applyDamage(damageWithBonus);
        }
    }
}
