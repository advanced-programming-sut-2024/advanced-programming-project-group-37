package message.server;

import message.enums.card.Card;
import message.enums.card.Leaders;
import message.enums.gameMenu.Factions;
import server.model.toolClasses.Pair;
import server.model.toolClasses.Result;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServerMessage {
    ServerType type;
    boolean success;
    String info;
    String token;
    // for friend requests
    private ArrayList<String> friends = new ArrayList<>();
    private ArrayList<String> fromWho = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private ArrayList<String> state = new ArrayList<>();


    public ServerMessage(Boolean success, String info) {
        this.success = success;
        this.info = info;
    }

    public ServerMessage(Result result) {
        this.success = result.isSuccessful();
        this.info = result.getMessage();
    }

    public ServerMessage(Result result, String token) {
        this.success = result.isSuccessful();
        this.info = result.getMessage();
        this.token = token;
    }

    public ServerMessage(ArrayList<String> friends, ArrayList<String> fromWho, ArrayList<String> date, ArrayList<String> state) {
        this.friends = friends;
        this.fromWho = fromWho;
        this.date = date;
        this.state = state;
    }

    public ServerMessage() {
        //an empty constructor
    }

    public ServerMessage(ServerType serverType) {
        this.type = serverType;
    }

    public ServerMessage(Factions factions) {
        this.factions = factions;
    }

    public ServerMessage(ServerType serverType, String username, boolean equals) {
        this.type = serverType;
        this.opponent = username;
        this.success = equals;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getInfo() {
        return info;
    }

    public String getToken() {
        return token;
    }

    //for friend requests
    public ArrayList<String> getFriends() {
        return friends;
    }


    public ArrayList<String> getFromWho() {
        return fromWho;
    }

    public ArrayList<String> getDate() {
        return date;
    }

    public ArrayList<String> getState() {
        return state;
    }


    /**
     * @author FOAD
     * server message for
     * SarchMessage request return a list of string of people with substring
     * and GiveMeOnlineFriend message returns a list of String of friends that are online
     */

    public ServerMessage(ArrayList<String> friendWithStr) {
        this.friends = friendWithStr;
    }

    /**
     * @author Foad
     * server message for
     * sending that the user has a new game request
     * sending that if the rand game created or not
     */
    String opponent;

    public ServerMessage(ServerType type, String username) {
        this.type = type;
        this.opponent = username;
    }


    //some getter
    public ServerType getType() {
        return type;
    }

    public String getOpponent() {
        return opponent;
    }

    Factions factions;

    public Factions getFactions() {
        return factions;
    }


    ArrayList<Pair<Card, Integer>> deck = new ArrayList<>();
    ArrayList<Pair<Card, Integer>> collection = new ArrayList<>();


    public ServerMessage(ArrayList<Pair<Card, Integer>> deck, ArrayList<Pair<Card, Integer>> collection) {
        this.collection = collection;
        this.deck = deck;
    }

    public ArrayList<Pair<Card, Integer>> getDeck() {
        return deck;
    }

    public ArrayList<Pair<Card, Integer>> getCollection() {
        return collection;
    }

    //show leader
    public ServerMessage(Leaders yourLeader, Leaders opponnetLeader) {
        this.yourLeader = yourLeader;
        this.opponnetLeader = opponnetLeader;
    }

    public Leaders yourLeader;
    public Leaders opponnetLeader;

    public Leaders getYourLeader() {
        return yourLeader;
    }

    public Leaders getOpponnetLeader() {
        return opponnetLeader;
    }


    //get in game table
    private Pair<Card, ArrayList<Card>>[] myCards;
    private Pair<Card, ArrayList<Card>>[] opponentCards;
    private ArrayList<Card> spells;
    private int myHp;
    private int opponentHp;
    private ArrayList<Card> myInHand;
    private ArrayList<Card> opponentInHand;
    private ArrayList<Integer> myScores;
    private ArrayList<Integer> opponentScores;
    private ArrayList<Card> myDeck;
    private ArrayList<Card> opponentDeck;
    private ArrayList<Card> myDead;
    private ArrayList<Card> opponentDead;


    public ServerMessage(Pair<Card, ArrayList<Card>>[] myCards, Pair<Card, ArrayList<Card>>[] opponentCards, ArrayList<Card> spells,
                         int myHp, int opponentHp, ArrayList<Card> myInHand, ArrayList<Card> opponentInHand, ArrayList<Integer> myScores,
                         ArrayList<Integer> opponentScores, ArrayList<Card> myDeck, ArrayList<Card> opponentDeck,
                         ArrayList<Card> myDead, ArrayList<Card> opponentDead) {
        this.myCards = myCards;
        this.opponentCards = opponentCards;
        this.spells = spells;
        this.myHp = myHp;
        this.opponentHp = opponentHp;
        this.myInHand = myInHand;
        this.opponentInHand = opponentInHand;
        this.myScores = myScores;
        this.opponentScores = opponentScores;
        this.myDeck = myDeck;
        this.opponentDeck = opponentDeck;
        this.myDead = myDead;
        this.opponentDead = opponentDead;
    }

    public Pair<Card, ArrayList<Card>>[] getMyCards() {
        return myCards;
    }

    public Pair<Card, ArrayList<Card>>[] getOpponentCards() {
        return opponentCards;
    }

    public ArrayList<Card> getSpells() {
        return spells;
    }

    public int getMyHp() {
        return myHp;
    }

    public int getOpponentHp() {
        return opponentHp;
    }

    public ArrayList<Card> getMyInHand() {
        return myInHand;
    }

    public ArrayList<Card> getOpponentInHand() {
        return opponentInHand;
    }

    public ArrayList<Integer> getMyScores() {
        return myScores;
    }

    public ArrayList<Integer> getOpponentScores() {
        return opponentScores;
    }

    public ArrayList<Card> getMyDeck() {
        return myDeck;
    }

    public ArrayList<Card> getOpponentDeck() {
        return opponentDeck;
    }

    public ArrayList<Card> getMyDead() {
        return myDead;
    }

    public ArrayList<Card> getOpponentDead() {
        return opponentDead;
    }

    //get  number of veto
    private int numberOfVeto;
    public ServerMessage(int numberOfVeto) {
        this.numberOfVeto = numberOfVeto;
    }

    public int getNumberOfVeto() {
        return numberOfVeto;
    }
}


