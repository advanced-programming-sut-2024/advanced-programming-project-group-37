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
}
