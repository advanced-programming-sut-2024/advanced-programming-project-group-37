package model.gameTable;

import model.enums.card.Card;
import model.enums.card.Leaders;
import model.toolClasses.Pair;

import java.util.ArrayList;

public class GameTable {
    int HP;
    private Leaders leader;
    private ArrayList<Pair<Card, Integer>> deckPairs = new ArrayList<>();
    private ArrayList<Card> deckCards = new ArrayList<>();
    private ArrayList<Card> deadCards = new ArrayList<>();
    private ArrayList<Card> inHandsCards = new ArrayList<>();
    private ArrayList<Pair<Card, ArrayList<Card>>> CardsOfRow = new ArrayList<>();

    public GameTable(ArrayList<Pair<Card, Integer>> deckPairs, Leaders leader) {
        this.deckPairs = deckPairs;
        this.leader = leader;
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

    public void setCardsOfRow(ArrayList<Pair<Card, ArrayList<Card>>> cardsOfRow) {
        CardsOfRow = cardsOfRow;
    }

    public void setDeckCards(ArrayList<Card> deckCards) {
        this.deckCards = deckCards;
    }

    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }

    public ArrayList<Pair<Card, ArrayList<Card>>> getCardsOfRow() {
        return CardsOfRow;
    }
}
