package model.enums.gameMenu;

import model.enums.card.Card;
import model.toolClasses.Pair;
import model.toolClasses.Result;

import java.util.ArrayList;

public enum Factions {
    MONSTER("", setForMonster()),
    EMPIRE_NILFGARDEN("", setForEmpireNilfGaarden()),
    REALMS_NORTHERN("", setForRealmsNorthern()),
    SCOIATEAL("", setForScoiaTael()),
    SKELLIGE("", setForSkellige());

    public final String name;
    private final ArrayList<Pair<Card, Integer>> cardCollection;

    Factions(String name, ArrayList<Pair<Card, Integer>> cardCollection) {
        this.name = name;
        this.cardCollection = cardCollection;
    }

    // return deepCopy of cardCollection
    public ArrayList<Pair<Card, Integer>> getDeepCopyOfArraylist() {
        return null;
    }

    // methods for fill default arraylist
    private static ArrayList<Pair<Card, Integer>> setForMonster() {
        return null;
    }
    private static ArrayList<Pair<Card, Integer>> setForEmpireNilfGaarden() {
        return null;
    }
    private static ArrayList<Pair<Card, Integer>> setForRealmsNorthern() {
        return null;
    }
    private static ArrayList<Pair<Card, Integer>> setForScoiaTael() {
        return null;
    }
    private static ArrayList<Pair<Card, Integer>> setForSkellige() {
        return null;
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
