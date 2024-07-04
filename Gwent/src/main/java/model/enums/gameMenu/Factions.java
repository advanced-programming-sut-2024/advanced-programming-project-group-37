package model.enums.gameMenu;

import javafx.scene.paint.RadialGradient;
import model.enums.card.Card;
import model.gameTable.GameTable;
import model.gameTable.UserInGame;
import model.toolClasses.Pair;
import model.toolClasses.Result;

import java.util.*;

public enum Factions {
    MONSTER("Monster", setForMonster()),
    EMPIRE_NILFGARDEN("Empire NilfGaarden", setForEmpireNilfGaarden()),
    REALMS_NORTHERN("Realm Northern", setForRealmsNorthern()),
    SCOIATEAL("Scoiateal", setForScoiaTael()),
    SKELLIGE("Skellige", setForSkellige()),
    NEUTRAL("Neutral", setForNeutal());

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


    private static void setCommonCards() {
    }


    // methods for ability of each faction
    public static Result monsterAbility() {
        return null;
    }

    public static Result empireNilfGaardenAbility() {
        return null;
    }

    public static Result realmsNorthernAbility(UserInGame player1, UserInGame player2, UserInGame winner) {
        if (winner == null)
            return new Result(false, "no winner");

        if (winner.getGameTable().getLeader().getFaction() != REALMS_NORTHERN)
            return new Result(false, "winner faction is not REALM NORTHERN");

        ArrayList<Card> inHand = winner.getGameTable().getInHandsCards();
        ArrayList<Card> deck = winner.getGameTable().getDeckCards();
        //remove one card from deck to in hand
        inHand.add(deck.get(0));
        deck.remove(0);
        return new Result(true, "one card to deck");
    }

    public static Result scoiaTaelAbility(UserInGame player1, UserInGame player2, UserInGame userTurn) {
        UserInGame userInGame = null;
        if (player1.getGameTable().getLeader().getFaction() == SCOIATEAL)
            userInGame = player1;
        else if (player2.getGameTable().getLeader().getFaction() == SCOIATEAL)
            userInGame = player2;
        return null;
    }

    public static Result skelligeAbility(UserInGame player1, UserInGame player2) {
        if (player1.getGameTable().getLeader().getFaction() == SKELLIGE) {
            deadTodeck(player1);
        }
        if (player2.getGameTable().getLeader().getFaction() == SKELLIGE) {
            deadTodeck(player2);
        }
        return null;
    }

    public static void deadTodeck(UserInGame player) {
        GameTable gameTable = player.getGameTable();
        ArrayList<Card> dead = gameTable.getDeadCards();
        ArrayList<Card> inHand = gameTable.getInHandsCards();
        if (gameTable.getDeadCards().size() == 1) {
            inHand.add(dead.get(0));
            dead.remove(0);
        } else if (dead.size() >= 2) {
            //choose 2 numbers
            Random random = new Random();
            Set<Integer> ranomNum = new HashSet<>();
            while (ranomNum.size() < 2)
                ranomNum.add(random.nextInt(dead.size()) - 1);
            //convert to list
            List<Integer> list = new ArrayList<>(ranomNum);
            Collections.sort(list);
            for (int i = 0; i < 2; i++) {
                inHand.add(dead.get(list.get(i)));
            }
            for (int i = 2; i >= 0; i++) {
                dead.remove(dead.get(i));
            }
        }
    }
}
