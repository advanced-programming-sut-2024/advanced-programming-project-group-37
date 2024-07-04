package model.gameTable;

import model.enums.card.Card;
import model.enums.card.Leaders;
import model.toolClasses.Pair;

import java.util.ArrayList;

public class GameTable {
    int HP = 2;
    private Leaders leader;
    private ArrayList<Card> spsells = new ArrayList<>();
    private ArrayList<Pair<Card, Integer>> deckPairs = new ArrayList<>();
    private ArrayList<Card> deckCards = new ArrayList<>();
    private ArrayList<Card> deadCards = new ArrayList<>();
    private ArrayList<Card> inHandsCards = new ArrayList<>();
    public Pair<Card, ArrayList<Card>>[] CardsOfRow = new Pair[3]; //siege 1 ranged 2 close 3

    public GameTable(ArrayList<Pair<Card, Integer>> deckPairs, Leaders leader) {
        this.deckPairs = deckPairs;
        this.leader = leader;

        for (int i = 0; i < 3; i++) {
            this.CardsOfRow[i] = new Pair<>(null, new ArrayList<>());
        }
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setLeader(Leaders leader) {
        this.leader = leader;
    }

    public int getHP() {
        return HP;
    }

    public Leaders getLeader() {
        return leader;
    }

    public ArrayList<Pair<Card, Integer>> getDeckPairs() {
        return deckPairs;
    }

    public ArrayList<Card> getDeadCards() {
        return deadCards;
    }

    public ArrayList<Card> getInHandsCards() {
        return inHandsCards;
    }

    public void setDeckPairs(ArrayList<Pair<Card, Integer>> deckPairs) {
        this.deckPairs = deckPairs;
    }

    public void setDeadCards(ArrayList<Card> deadCards) {
        this.deadCards = deadCards;
    }

    public void setInHandsCards(ArrayList<Card> inHandsCards) {
        this.inHandsCards = inHandsCards;
    }

    public void setCardsOfRow(Pair<Card, ArrayList<Card>>[] cardsOfRow) {
        CardsOfRow = cardsOfRow;
    }

    public void setDeckCards(ArrayList<Card> deckCards) {
        this.deckCards = deckCards;
    }

    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }

    public Pair<Card, ArrayList<Card>>[] getCardsOfRow() {
        return CardsOfRow;
    }

    public ArrayList<Card> getSpsells() {
        return spsells;
    }

    public void setSpsells(ArrayList<Card> spsells) {
        this.spsells = spsells;
    }
}