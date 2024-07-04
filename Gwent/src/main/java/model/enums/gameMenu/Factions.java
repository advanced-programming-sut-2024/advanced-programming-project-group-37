package model.enums.gameMenu;

import model.enums.card.Card;
import model.toolClasses.Pair;
import model.toolClasses.Result;

import java.util.ArrayList;

public enum Factions {
    MONSTER("Monster", setForMonster()),
    EMPIRE_NILFGARDEN("Empire NilfGaarden", setForEmpireNilfGaarden()),
    REALMS_NORTHERN("Realm Northern", setForRealmsNorthern()),
    SCOIATEAL("Scoiateal", setForScoiaTael()),
    SKELLIGE("Skellige", setForSkellige()),
    NEUTRAL("Neutral" , setForNeutal());

    public final String name;

    private final ArrayList<Pair<Card, Integer>> cardCollection;

    Factions(String name, ArrayList<Pair<Card, Integer>> cardCollection) {
        this.name = name;
        this.cardCollection = cardCollection;
    }

    public String getName() {
        return name;
    }

    public static Factions getFaction(String targetName) {
        for (Factions faction : Factions.values()) {
            if (faction.getName().equals(targetName)) {
                return faction;
            }
        }
        // Throw an exception or handle the case where no matching faction is found
        return null;
    }
    public static Factions getInstance(String name) {
        for (Factions value : values()) {
            if (value.getName().equals(name)) return value;
        }
        return null;
    }

    // return deepCopy of cardCollection
    public ArrayList<Pair<Card, Integer>> getDeepCopyOfArraylist() {
        return new ArrayList<>(cardCollection);
    }

    // methods for fill default arraylist
    private static ArrayList<Pair<Card, Integer>> setForMonster() {
        ArrayList<Pair<Card, Integer>> list = new ArrayList<>();
        for (Card card : Card.values()) {
            if (card.getFaction().equals(FactionsName.MONSTER) || card.getFaction().equals(FactionsName.NEUTRAL))
                list.add(new Pair<>(card, card.getNumberOfCardInGame()));
        }

        return list;
    }
    private static ArrayList<Pair<Card, Integer>> setForEmpireNilfGaarden() {
        ArrayList<Pair<Card, Integer>> list = new ArrayList<>();
        for (Card card : Card.values()) {
            if (card.getFaction().equals(FactionsName.EMPIRE_NILFGARDEN) || card.getFaction().equals(FactionsName.NEUTRAL))
                list.add(new Pair<>(card, card.getNumberOfCardInGame()));
        }

        return list;
    }

    private static ArrayList<Pair<Card, Integer>> setForRealmsNorthern() {
        ArrayList<Pair<Card, Integer>> list = new ArrayList<>();
        for (Card card : Card.values()) {
            if (card.getFaction().equals(FactionsName.REALMS_NORTHERN) || card.getFaction().equals(FactionsName.NEUTRAL))
                list.add(new Pair<>(card, card.getNumberOfCardInGame()));
        }

        return list;
    }

    private static ArrayList<Pair<Card, Integer>> setForScoiaTael() {
        ArrayList<Pair<Card, Integer>> list = new ArrayList<>();
        for (Card card : Card.values()) {
            if (card.getFaction().equals(FactionsName.SCOIATEAL) || card.getFaction().equals(FactionsName.NEUTRAL))
                list.add(new Pair<>(card, card.getNumberOfCardInGame()));
        }

        return list;
    }

    private static ArrayList<Pair<Card, Integer>> setForSkellige() {
        ArrayList<Pair<Card, Integer>> list = new ArrayList<>();
        for (Card card : Card.values()) {
            if (card.getFaction().equals(FactionsName.SKELLIGE) || card.getFaction().equals(FactionsName.NEUTRAL))
                list.add(new Pair<>(card, card.getNumberOfCardInGame()));
        }

        return list;
    }

    private static ArrayList<Pair<Card, Integer>> setForNeutal() {
        ArrayList<Pair<Card, Integer>> list = new ArrayList<>();
        for (Card card : Card.values()) {
            if (card.getFaction().equals("Neutral"))
                list.add(new Pair<>(card, card.getNumberOfCardInGame()));
        }

        return list;
    }


    private static void setCommonCards() {}


    // methods for ability of each faction
    private static Result monsterAbility() {
        return null;
    }

    private static Result empireNilfGaardenAbility() {
        return null;
    }

    private static Result realmsNorthernAbility() {
        return null;
    }

    private static Result scoiaTaelAbility() {
        return null;
    }

    private static Result skelligeAbility() {
        return null;
    }
}
