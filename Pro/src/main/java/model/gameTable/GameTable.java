package model.gameTable;

import model.enums.card.Card;
import model.cards.leaders.LeaderCards;
import model.toolClasses.Pair;

import java.util.ArrayList;

public class GameTable {
    int HP;
    private LeaderCards leader;
    private ArrayList<Pair<Card, Integer>> deck;
    private ArrayList<Card> deadCards;
    private ArrayList<Card> inHandsCards;
    private ArrayList<Pair<Card, ArrayList<Card>>> CardsOfRow;

    public GameTable() {

    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setLeader(LeaderCards leader) {
        this.leader = leader;
    }

    public int getHP() {
        return HP;
    }

    public LeaderCards getLeader() {
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
