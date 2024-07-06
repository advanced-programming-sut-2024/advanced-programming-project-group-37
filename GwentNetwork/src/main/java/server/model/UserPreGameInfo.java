package server.model;

import message.enums.card.Card;
import message.enums.card.CardType;
import message.enums.card.Leaders;
import message.enums.gameMenu.Factions;
import server.model.toolClasses.Pair;

import java.util.ArrayList;

public class UserPreGameInfo {
    private Factions faction;
    private Leaders leader;
    private ArrayList<Pair<Card, Integer>> cardCollection;
    private ArrayList<Pair<Card, Integer>> cardsInDeck;

    UserPreGameInfo() {
        // set default faction <REALMS_NORTHERN>
        this.faction = Factions.REALMS_NORTHERN;

        cardsInDeck = new ArrayList<>();
        cardCollection = new ArrayList<>();
    }

    public Factions getFaction() {
        return faction;
    }

    public void setFaction(Factions faction) {
        this.faction = faction;
    }

    public Leaders getLeader() {
        return leader;
    }

    public void setLeader(Leaders leader) {
        this.leader = leader;
    }

    public ArrayList<Pair<Card, Integer>> getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(ArrayList<Pair<Card, Integer>> cardCollection) {
        this.cardCollection = cardCollection;
    }

    public ArrayList<Pair<Card, Integer>> getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(ArrayList<Pair<Card, Integer>> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }

    public int calculateSpecialCardsInDeck() {
        int n = 0;
        for (Pair<Card, Integer> pair : this.cardsInDeck) {
            if (pair.getFirst().getType().equals(CardType.WEATHER) || pair.getFirst().getType().equals(CardType.SPELL)) {
                n+=pair.getSecond();
            }
        }
        return n;
    }

    public int calculateSoldierCardsInDeck() {
        int n = 0;
        for (Pair<Card, Integer> pair : this.cardsInDeck) {
            if (!pair.getFirst().getType().equals(CardType.WEATHER) && !pair.getFirst().getType().equals(CardType.SPELL)) {
                n+=pair.getSecond();
            }
        }
        return n;
    }
}
