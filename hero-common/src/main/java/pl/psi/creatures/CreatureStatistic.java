package pl.psi.creatures;

import com.google.common.collect.Range;

import lombok.Getter;

@Getter
public enum CreatureStatistic implements CreatureStatisticIf
{
    // NECROPILIS FRACTION
    SKELETON( "Skeleton", 5, 4, 6, 4, Range.closed( 1, 3 ), 1,
        "Average lvl1 foot soldier, but always in huge numbers thanks to necromancy skill and skeleton transformer.",
        false, true ), //
    WALKING_DEAD( "Walking Dead", 5, 5, 15, 3, Range.closed( 2, 3 ), 2,
        "Basically its the same skeleton with more hit points. I prefer buying 2 skeletons instead.", false, true ), //
    WIGHT( "Wight", 7, 7, 18, 5, Range.closed( 3, 5 ), 3,
        "Regenerating ability is really good when fighting weak enemies, especially shooters.\nSpecial: top wight of the stack regenerates all lost damage in the beginning of each round",
        false, true ), //
    VAMPIRE( "Vampire", 10, 9, 30, 6, Range.closed( 5, 8 ), 4,
        "NOTHING compared to their upgraded brothers. Keep the population growing and recruit after the upgrade.\nSpecial: no enemy retaliation.",
        false, true ), //
    LICH( "Lich", 13, 10, 30, 6, Range.closed( 11, 15 ), 5,
        "Now they last longer and are able to do more damage! A must for good necropolis army.\nSpecial: death cloud range attack - damages living creatures on adjacent hexes to target.\n",
        false, true ), //
    BLACK_KNIGHT( "Black Knight", 16, 16, 120, 7, Range.closed( 15, 30 ), 6,
        "Awesome ground unit. As any undead it cannot be blinded, so your enemies will have to look out.\nSpecial: 20% chance to curse enemy.\n",
        false, true ), //
    BONE_DRAGON( "Bone Dragon", 17, 15, 150, 9, Range.closed( 25, 50 ), 7,
        "They are truly fearsome for enemies with low morale. Simply keeping them on battlefield scares enemies.\nSpecial: -1 to enemy morale.\n",
        false, true ), //
    SKELETON_WARRIOR( "Skeleton Warrior", 6, 6, 6, 5, Range.closed( 1, 3 ), 1,
        "Numerous skeletons become even better, but running back to town and upgrading is a problem... If there is no room in your army for ordinary skeletons, necromancy skill will resurrect skeleton warriors, but there will be less of them than normal skeletons, so it might be a good idea not to upgrade cursed temple at all.",
        true, true ), //
    ZOMBIE( "Zombie", 5, 5, 20, 4, Range.closed( 2, 3 ), 2,
        "Attack ratings are way too low... In my opinion, necropolis has the worst lvl2 creature.\nSpecial: 20% chance to disease enemies (-2Att -2Def for 3 rounds)\n",
        true, true ), //
    WRAITH( "Wraith", 7, 7, 18, 5, Range.closed( 3, 5 ), 3,
        "Regenerating ability is really good when fighting weak enemies, especially shooters.\nSpecial: top wight of the stack regenerates all lost damage in the beginning of each round\n",
        true, true ), //
    VAMPIRE_LORD( "Vampire Lord", 10, 10, 40, 9, Range.closed( 5, 8 ), 4,
        "My favorite necropolis unit. Use them as main striking unit and you might end up with no losses!\nSpecial: no enemy retaliation ; resurrects members of their own stack by restoring health equal to the amount of damage they do to living enemies.\n",
        true, true ), //
    POWER_LICH( "Power Lich", 13, 10, 40, 7, Range.closed( 11, 15 ), 5,
        "Now they last longer and are able to do more damage! A must for good necropolis army.\nSpecial: death cloud range attack - damages living creatures on adjacent hexes to target.\n",
        true, true ), //
    DREAD_KNIGHT( "Dread Knight", 18, 18, 120, 9, Range.closed( 15, 30 ), 6,
        "I think it's the best lvl6 unit in the game! Double damage ability puts Dread Knights above Naga Queens.\nSpecial: 20% chance to curse enemy ; 20% chance to do double damage.\n",
        true, true ), //
    GHOST_DRAGON( "Ghost Dragon", 19, 17, 200, 14, Range.closed( 25, 50 ), 7,
        "When situation seems hopeless, take a chance on the best enemy stack! If you'll get lucky, half their hit points will be gone instantly!! Ageing ability makes ghost dragons as dangerous as other lvl7 creatures.\nSpecial: -1 to enemy morale ; 20% chance to age enemy (halve hit points of all stack members).\n",
        true, true ),
    // INFERNO FRACTION
    IMP( "Imp", 2, 3, 4, 5, Range.closed( 1, 2 ), 1,
            "Weakest level 1 unit in Heroes 3 which is also quite expensive. Useless all around :)",
            false, false ),
    GOG( "Gog", 6, 4, 13, 4, Range.closed( 2, 4 ), 2,
            "Very good shooter, nice damage range and hit points are a meaningful number to them. Speed is too slow before upgraded though.",
            false, false ),
    HELL_HOUND( "Hell Hound", 10, 6, 25, 7, Range.closed( 2, 7 ), 3,
            "Good offensive unit, low on defence. Strongly affected by bless and curse spells.",
            false, false ),
    DEMON( "Demon", 10, 10, 35, 5, Range.closed( 7, 9 ), 4,
            "Average unit for level 4, but the price is very reasonable.",
            false, false ),
    PIT_FIEND( "Pit Fiend", 13, 13, 45, 6, Range.closed( 13, 17 ), 5,
            "Not a very good level 5 creature, but costly. Demons and pit fiends kinda balance each outher out. Note how often number 13 is used :)",
            false, false ),
    EFREETI( "Efreeti", 16, 12, 90, 9, Range.closed( 16, 24 ), 6,
            "Reasonable creature, can be purchased early due to inferno's specific building plan. Good troop for armageddon spellcaster.",
            false, false ),
    DEVIL( "Devil", 19, 21, 160, 11, Range.closed( 30, 40 ), 7,
            "Makes a tough opponent because of speed and no enemy retaliation. Devil can beat an angel in one on one combat.",
            false, false ),
    // INFERNO FRACTION UPGRADED
    FAMILIAR( "Familiar", 4, 4, 4, 7, Range.closed( 1, 2 ), 1,
        "Upgrade is well worth doing: attack and defence become closer to medium for level 1, good speed and the mana chanelling abbility which makes it somewhat worthy having familiars present at the long battles. Think about it: he spends 20 mana and you'll get 4! Damage range does not reach 3 which is a big disadvantage. If you get the grail, you can get hordes of these guys :)",
        true, false ),
    MAGOG( "Magog", 7, 4, 13, 6, Range.closed( 2, 4 ), 2,
        "Magog cannot beat marksman's two shots, but if enemies are standing close together, the advantage can be even grater. Note that fireproof units do not suffer magog's adjacent damage.",
        true, false ),
    CERBERUS( "Cerberus", 10, 8, 25, 8, Range.closed( 2, 7 ), 3,
        "Great upgrade. This is the only case in the game where stats get downgraded, damage in this case because this damage can be done to 3 enemies at once. Better defence and with no retaliation it's a good idea to charge and attack the crowd.",
        true, false ),
    HORNED_DEMON( "Horned Demon", 10, 10, 40, 6, Range.closed( 7, 9 ), 4,
        "One of the smallest upgrades in the game: 5 hit points and 1 speed for 20 gold... reasonable, but horned demons are now below-average among level 4 upgrades, but their price still remains low.",
        true, false ),
    PIT_LORD( "Pit Lord", 13, 13, 45, 7, Range.closed( 13, 17 ), 5,
        "1 speed and demon resurrection abbility for 200 gold! Sounds darn expensive. Think this way: if you have lost 90 imps on a battle, 10 pit lords can resurrect them into about 10 demons... or if you lost 14 hell hounds, 10 pit lords can also turn them into about 10 demons. To raise 50 demons you will need 50 pit lords and a dead stack of about 70 hell hounds or 20 efreet or 440 imps. 50 Pit lords will cost 10000 gold to upgrade. 50 Demons cost 12500. Think for yourself... By the way, due to special-only upgrade, pit lords are weak among level 5. If you're low on money, pit lord costs almost like 3 demons who will make a stronger force.",
        true, false ),
    EFREET_SULTAN( "Efreet Sultan", 16, 14, 90, 13, Range.closed( 16, 24 ), 6,
        "Excellent upgrade. Their speed is only matched by some level 7 upgrades and... dragon flies. Enemy will think well before attacking efreet sultans: portion of the damage returns back to the attacker plus the retaliation. Good to use them against high level creatures because fire shield damage will surpass their mighty defence without being decreased.",
        true, false ),
    ARCH_DEVIL( "Arch Devil", 26, 28, 200, 17, Range.closed( 30, 40 ), 7,
        "Arch devil is no match for an arch angel. Otherways, a really powerful creature for non-retaliated attacks with outwaiting the opponent.",
        true, false );
    ;//

    private final String name;
    private int attack;
    private final int armor;
    private final int maxHp;
    private final int moveRange;
    private final Range< Integer > damage;
    private final int tier;
    private final String description;
    private final boolean isUpgraded;
    private final boolean isUndead;

    CreatureStatistic( final String aName, final int aAttack, final int aArmor, final int aMaxHp,
        final int aMoveRange, final Range< Integer > aDamage, final int aTier, final String aDescription,
        final boolean aIsUpgraded, final boolean aIsUndead )
    {
        name = aName;
        attack = aAttack;
        armor = aArmor;
        maxHp = aMaxHp;
        moveRange = aMoveRange;
        damage = aDamage;
        tier = aTier;
        description = aDescription;
        isUpgraded = aIsUpgraded;
        isUndead = aIsUndead;
    }

    public void changeAttack(int change){
        attack = attack + change;
    }
    String getTranslatedName()
    {
        return name;
    }
}
