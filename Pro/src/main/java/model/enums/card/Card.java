package model.enums.card;

import model.enums.gameMenu.Factions;
import model.toolClasses.Result;

public enum Card {
    Mardoeme(Factions.SKELLIGE, 0, 3, CardType.SPELL, false, Ability.MARDROEME),
    Berserker(Factions.SKELLIGE, 4, 1, CardType.CLOSE_COMBAT, false, Ability.BERSERKER),
    AND_OTHER_CARDS(Factions.SKELLIGE, 4, 1, CardType.CLOSE_COMBAT, false, Ability.BERSERKER);

    private final Factions faction;
    private final int power;
    private final int numberOfCardInGame;
    private final CardType type;
    private final boolean isHero;
    private final Ability ability;

    Card(Factions faction, int power, int numberOfCardInGame, CardType type, boolean isHero, Ability ability) {
        this.faction = faction;
        this.power = power;
        this.numberOfCardInGame = numberOfCardInGame;
        this.type = type;
        this.isHero = isHero;
        this.ability = ability;
    }

    public Factions getFaction() {
        return faction;
    }

    public int getPower() {
        return power;
    }

    public int getNumberOfCardInGame() {
        return numberOfCardInGame;
    }

    public CardType getType() {
        return type;
    }

    public boolean getIsHero() {
        return isHero;
    }

    // methods for ability
    public static Result commandsHorn() {
        return null;
    }

    public static Result medic() {
        return null;
    }

    public static Result moralBoost() {
        return null;
    }

    public static Result muster() {
        return null;
    }

    public static Result spy() {
        return null;
    }

    public static Result tightBond() {
        return null;
    }

    public static Result scroch() {
        return null;
    }

    public static Result berserker() {
        return null;
    }

    public static Result mardroeme() {
        return null;
    }

    public static Result transformers() {
        return null;
    }


}
