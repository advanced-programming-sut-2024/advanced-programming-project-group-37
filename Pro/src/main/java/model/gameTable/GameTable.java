package model.gameTable;

import model.enums.card.Card;
import model.enums.card.Leaders;
import model.toolClasses.Pair;

import java.util.ArrayList;

public class GameTable {
    int HP;
    private Leaders leader;
    private ArrayList<Pair<Card, Integer>> deck = new ArrayList<>();
    private ArrayList<Card> deadCards = new ArrayList<>();
    private ArrayList<Card> inHandsCards = new ArrayList<>();
    private ArrayList<Pair<Card, ArrayList<Card>>> CardsOfRow = new ArrayList<>();

    public GameTable() {

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

    public ArrayList<Pair<Card, Integer>> getDeck() {
        return deck;
    }

    public ArrayList<Card> getDeadCards() {
        return deadCards;
    }

    public ArrayList<Card> getInHandsCards() {
        return inHandsCards;
    }
}
