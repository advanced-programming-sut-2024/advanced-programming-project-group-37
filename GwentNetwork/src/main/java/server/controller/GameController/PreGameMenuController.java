package server.controller.GameController;

import server.model.User;
import server.model.UserPreGameInfo;
import message.enums.card.Card;
import message.enums.card.CardType;
import message.enums.card.Leaders;
import message.enums.gameMenu.Factions;
import server.model.toolClasses.Pair;
import server.model.toolClasses.Result;

import java.util.ArrayList;

public class PreGameMenuController {
    public static ArrayList<PreGameMenuController> preGameMenuControllers = new ArrayList<>();

    public PreGameMenuController(User currentUser, User opponentUser) {
        this.currentUser = currentUser;
        this.opponentUser = opponentUser;
        preGameMenuControllers.add(this);
    }

    public User currentUser = User.getLoggedInUser();
    public User opponentUser;

    public static PreGameMenuController getPregame(User userByToken) {
        for (PreGameMenuController pg : preGameMenuControllers) {
            if (pg.currentUser == userByToken || pg.opponentUser == userByToken) {
                return pg;
            }
        }
        return null;
    }

    // create a game
    public Result createGame(String player2Username) {
        //check if player2Username is valid or not
        User secondUser = User.getUserByUsername(player2Username);
        if (secondUser == null)
            return new Result(false, "Second player username is invalid!");

        //check if current user wants to play with himself or not
        if (secondUser.getUsername().equals(currentUser.getUsername()))
            return new Result(false, "You can't play with yourself!");

        //run the game menu in view
        opponentUser = secondUser;
        return new Result(true, "Game started");
    }

    // show factions
    // it returns the faction that the user selected before
    public Result showFactions(String playerNum) {
        User user;
        if (playerNum.equals("1"))
            user = User.getLoggedInUser();
        else user = opponentUser;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Monster\nEmpire Nilfgaardian\nRealms Northern\nScoiatael\nSkellige\n");
        stringBuilder.append("selected Faction: " + user.getUserPreGameInfo().getFaction().name + "\n");
        return new Result(true, stringBuilder.toString());
    }

    // select faction
    public Result selectFaction(String factionName, User user) {
        Factions factions = null;
        factions = Factions.getFaction(factionName);
        if (factions == null)
            return new Result(false, "No faction found with this name!");

        UserPreGameInfo userPreGameInfo = user.getUserPreGameInfo();
        userPreGameInfo.setFaction(factions);
        userPreGameInfo.setCardCollection(userPreGameInfo.getFaction().getDeepCopyOfArraylist());
        user.setUserPreGameInfo(userPreGameInfo);
        User.setLoggedInUser(user);

        return new Result(true, "Faction changed successfully!");
    }

    // show Cards
    public Result showCards(String playerNum) {
        User user;
        if (playerNum.equals("1")) user = currentUser;
        else user = opponentUser;

        Factions factions = user.getUserPreGameInfo().getFaction();
        ArrayList<Pair<Card, Integer>> factionCards = factions.getDeepCopyOfArraylist();
        // printing all cards
        StringBuilder toPrint = new StringBuilder();
        for (int i = 0; i < factionCards.size(); i++) {
            Pair<Card, Integer> cardIntegerPair = factionCards.get(i);
            Card card = cardIntegerPair.getFirst();
            int numberOfCard = cardIntegerPair.getSecond();
            //Printing Number
            toPrint.append(card.getName()).append(":\n");

            //TYPE
            toPrint.append("Type").append(card.getType().name()).append("\n");

            //IS HERO OR NOT
            toPrint.append("Is Hero:").append(card.getIsHero()).append("\n");

            //PLACE IN BOARD
            toPrint.append("Place in board:").append(i + 1).append("\n");

            //ABILITY
            toPrint.append("Ability: ");
            String ability = (card.getAbility() == null) ? "no ability" : card.getAbility().name();
            toPrint.append(ability).append("\n");

            //NUMBER OF CARD IN DECK
            int numberOfThisCardInDeck = searchForCardInDeck(card, user);
            toPrint.append("Number Of Cards in Deck: ").append(numberOfCard).append("\n");

            //NUMBER OF TOTAL CARDS
            toPrint.append("Number Of Cards to Pick: ").append(card.getNumberOfCardInGame() - numberOfCard).append("\n");

            //CARD POWER
            toPrint.append("Card Power: ").append(card.getPower()).append("\n");
        }
        return new Result(true, toPrint.toString());
    }

    private int searchForCardInDeck(Card card, User user) {
        int n = 0;
        UserPreGameInfo userPreGameInfo = user.getUserPreGameInfo();
        ArrayList<Pair<Card, Integer>> deck = userPreGameInfo.getCardsInDeck();
        for (Pair<Card, Integer> cardInDeck : deck) {
            if (cardInDeck.getFirst().equals(card)) {
                n = cardInDeck.getSecond();
                break;
            }
        }
        return n;
    }


    // show information current user
    public Result showInfoCurrentUser(String playerNum) {
        //set the user we want to print its information
        User user;
        if (playerNum.equals("1")) user = currentUser;
        else user = opponentUser;

        StringBuilder information = new StringBuilder();

        //NAME
        information.append("Username: ").append(user.getUsername()).append("\n");
        //FACTION NAME
        information.append("Faction: ").append(user.getUserPreGameInfo().getFaction().name).append("\n");
        //NUMBER OF CARDS IN DECK
        int numberOfCardsInDeck = calculateNumberOfCardsInDeck(user);
        information.append("Number of cards in deck:").append(numberOfCardsInDeck).append("\n");


        //TODO Soldier cards
        //TODO Special cards

        //HERO CARDS
        int heroCards = calculateNumberOfHerosInDeck(user);
        information.append("Number of Hero Cards in deck: ").append(heroCards).append("\n");

        //Power of cards
        int powerSum = calculatePowerOfAllCardsInDeck(user);
        information.append("Power Of Cards in Deck: ").append(powerSum).append("\n");
        return new Result(true, information.toString());
    }

    private int calculatePowerOfAllCardsInDeck(User user) {
        int powerSum = 0;
        for (Pair<Card, Integer> pair : user.getUserPreGameInfo().getCardsInDeck()) {
            powerSum += pair.getSecond() * pair.getFirst().getPower();
        }
        return powerSum;
    }

    private int calculateNumberOfHerosInDeck(User user) {
        int n = 0;
        for (Pair<Card, Integer> pair : user.getUserPreGameInfo().getCardsInDeck()) {
            if (pair.getFirst().getIsHero()) n += pair.getSecond();
        }
        return n;
    }

    private int calculateNumberOfCardsInDeck(User user) {
        int n = 0;
        for (Pair<Card, Integer> pair : user.getUserPreGameInfo().getCardsInDeck()) {
            n += pair.getSecond();
        }
        return n;
    }

    // save deck
    public Result saveDeckWithName(String address) {
        return new Result(true, "");
    }

    public Result saveDeckWithAddress(String address) {
        return new Result(true, "");
    }

    public Result loadDeckWithAddress(String address) {
        return new Result(true, "");
    }

    public Result loadDeckWithName(String address) {
        return new Result(true, "");
    }

    // leader commands
    public Result showLeaders(String playerNum) {
        //Set user
        User user;
        if (playerNum.equals("1")) user = currentUser;
        else user = opponentUser;

        StringBuilder toPrint = new StringBuilder();
        int n = 1;

        for (Leaders leaders : Leaders.values()) {
            if (leaders.getFaction().equals(user.getUserPreGameInfo().getFaction())) {
                toPrint.append(n).append("- ").append(leaders.name());
                if (user.getUserPreGameInfo().getLeader().equals(leaders))
                    toPrint.append(" : Selected Leader\n");
                else toPrint.append("\n");
            }
        }

        return new Result(true, toPrint.toString());
    }

    public Result selectLeader(String leaderName, String playerNum) {
        User user = null;
        if (playerNum.equals("1")) user = currentUser;
        else if (playerNum.equals("2")) user = opponentUser;

        Leaders leader = null;
        for (Leaders leaders : Leaders.values()) {
            if (leaders.name().equals(leaderName)) {
                leader = leaders;
            }
        }
        if (leader == null)
            return new Result(false, "not found!");

        UserPreGameInfo userPreGameInfo = user.getUserPreGameInfo();
        // leaderNumber - 1 because the leaderNumber that passed to this function is 1 based
        userPreGameInfo.setLeader(leader);
        user.setUserPreGameInfo(userPreGameInfo);

        return new Result(true, "Leader " + user.getUserPreGameInfo().getLeader().name() +
                " set for player " + user.getUsername());
    }

    // add to deck
    public Result addToDeck(String cardName, int count, String playerNum) {
        User user;
        if (playerNum.equals("1")) user = currentUser;
        else user = opponentUser;

        //get card by name
        Card card = Card.getCardByName(cardName);
        if (card == null) {
            return new Result(false, "invalid card name");
        }


        //get card in collection for calculating the number of cards available
        ArrayList<Pair<Card, Integer>> cardCollection = user.getUserPreGameInfo().getCardCollection();
        Pair<Card, Integer> cardInCollection = null;
        for (Pair<Card, Integer> pair : cardCollection) {
            if (pair.getFirst().equals(card))
                cardInCollection = pair;
        }


        //get card in deck
        ArrayList<Pair<Card, Integer>> cardsInDeck = user.getUserPreGameInfo().getCardsInDeck();
        Pair<Card, Integer> cardInDeck = null;
        for (Pair<Card, Integer> pair : cardsInDeck) {
            if (pair.getFirst().equals(card))
                cardInDeck = pair;
        }

        //check if count is valid or not
        if (count > cardInCollection.getSecond())
            return new Result(false, "not enough card available");

        //check special cards
        if ((card.getType().equals(CardType.SPELL) || card.getType().equals(CardType.WEATHER)) &&
                user.getUserPreGameInfo().calculateSpecialCardsInDeck() + count > 10) {
            return new Result(false, "more than 10 special cards");
        }


        //add card to deck
        //if there wasn't this card before
        if (cardInDeck == null) {
            //add to deck
            cardsInDeck.add(new Pair<>(card, count));
            UserPreGameInfo userPreGameInfo = user.getUserPreGameInfo();
            userPreGameInfo.setCardsInDeck(cardsInDeck);

        } else {
            int n = cardInDeck.getSecond();
            cardInDeck.setSecond(n + count);
        }
        // remove from cordCollection
        int n = cardInCollection.getSecond();
        if (n == count) {
            cardCollection.remove(cardInCollection);
        } else {
            cardInCollection.setSecond(n - count);
        }

        return new Result(false, "card added to deck successfully");
    }

    // delete card from deck
    public Result deleteFromDeck(String cardName, int count, String playerNum) {
        User user;
        if (playerNum.equals("1")) user = currentUser;
        else user = opponentUser;

        //get card by name
        Card card = Card.getCardByName(cardName);
        if (card == null) {
            return new Result(false, "invalid card name");
        }


        //get card in collection for calculating the number of cards available
        ArrayList<Pair<Card, Integer>> cardCollection = user.getUserPreGameInfo().getCardCollection();
        Pair<Card, Integer> cardInCollection = null;
        for (Pair<Card, Integer> pair : cardCollection) {
            if (pair.getFirst().equals(card))
                cardInCollection = pair;
        }


        //get card in deck
        ArrayList<Pair<Card, Integer>> cardsInDeck = user.getUserPreGameInfo().getCardsInDeck();

        Pair<Card, Integer> cardInDeck = null;
        for (Pair<Card, Integer> pair : cardsInDeck) {
            if (pair.getFirst().equals(card))
                cardInDeck = pair;
        }

        //check if count is valid or not
        if (count > cardInDeck.getSecond())
            return new Result(false, "not enough card available In deck");


        //add card to deck
        //if there wasn't this card before
        if (cardInCollection == null) {
            //add to deck
            cardCollection.add(new Pair<>(card, count));
            UserPreGameInfo userPreGameInfo = user.getUserPreGameInfo();
            userPreGameInfo.setCardCollection(cardCollection);
            //remove from collection


        } else {
            int n = cardInDeck.getSecond();

            cardInCollection.setSecond(n + count);
        }
        int n = cardInDeck.getSecond();
        if (n == count) {
            cardsInDeck.remove(cardInDeck);
        } else {
            cardInDeck.setSecond(n - count);
        }


        if (playerNum.equals("1")) User.setLoggedInUser(user);

        return new Result(true, "card deleted from deck successfully");
    }

    public boolean isCountValid(int count) {
        return true;
    }

    public boolean isCardNameValid(String cardName) {
        return true;
    }

    public boolean checkNumberOfCardInDeck() {
        return true;
    } // TODO: ???

    public boolean checkSpecialCardInDeck() {
        return true;
    }


    public boolean checkDeckIsOk(User user) {
        //check if soldier cards are more than 22
        return user.getUserPreGameInfo().calculateSoldierCardsInDeck() >= 22;
    }


    // start game
    public Result startGame() {
        //check first player deck
        if (!checkDeckIsOk(currentUser))
            return new Result(false, "first player deck is not complete.");
        //check second player deck
        if (!checkDeckIsOk(opponentUser))
            return new Result(false, "second player deck is not complete.");

        return new Result(true, "game begins");
    }

    public Result showDeck(String playerNum) {
        User user;
        if (playerNum.equals("1")) user = currentUser;
        else user = opponentUser;

        ArrayList<Pair<Card, Integer>> deckCards = user.getUserPreGameInfo().getCardsInDeck();
        StringBuilder cards = new StringBuilder();

        for (Pair<Card, Integer> cardIntegerPair : deckCards) {
            cards.append(cardIntegerPair.getFirst().name()).append(" ").append(cardIntegerPair.getSecond()).append("\n");
        }

        return new Result(true, cards.toString());

    }

}
