package model;

import model.enums.card.Card;
import model.enums.card.Leaders;
import model.enums.gameMenu.Factions;
import model.toolClasses.Pair;

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
}
