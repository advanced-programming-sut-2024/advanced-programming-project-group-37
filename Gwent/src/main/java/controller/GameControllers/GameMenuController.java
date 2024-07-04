package controller.GameControllers;

import model.User;
import model.enums.card.Ability;
import model.enums.card.Card;
import model.enums.card.Leaders;
import model.enums.gameMenu.Factions;
import model.gameTable.GameTable;
import model.gameTable.UserInGame;
import model.toolClasses.Pair;
import model.toolClasses.Result;

import java.nio.channels.AlreadyBoundException;
import java.util.*;

public class GameMenuController {
    private ArrayList<Card> spells = new ArrayList<>();

    public void setSpells(ArrayList<Card> spells) {
        this.spells = spells;
    }

    private int roundNumber = 1;

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    private UserInGame player1, player2;
    private UserInGame userTurn;

    public UserInGame getPlayer1() {
        return player1;
    }

    public UserInGame getPlayer2() {
        return player2;
    }

    public UserInGame getUserTurn() {
        return userTurn;
    }

    public void setPlayers(User user1, User user2) {
        player1 = new UserInGame(user1);
        player2 = new UserInGame(user2);
        userTurn = player1;
    }

    public Result changeTurn() {
        if (userTurn == player1) userTurn = player2;
        else userTurn = player1;
        return new Result(true, "turn changed to" + userTurn.getUser().getUsername());
    }

    //
    public Result vetoCard(String cardNumber) {
        //check if player has vetoed before ot not
        if (userTurn.getNumberOfVeto() == 2)
            return new Result(false, "you can't veto card");

        int n = Integer.parseInt(cardNumber);
        GameTable gameTable = userTurn.getGameTable();

        ArrayList<Card> cardsInDeck = gameTable.getDeckCards();
        ArrayList<Card> cardsInHand = gameTable.getInHandsCards();
        //n is 1 based but arraylist is 0 based
        Card cardToRemove = cardsInHand.get(n - 1);

        cardsInHand.remove(n - 1);

        //replace with another one
        Random random = new Random();
        int randomNum = random.nextInt(cardsInDeck.size());

        Card cardToAdd = cardsInDeck.get(randomNum);

        // add to hand and remove from deck
        cardsInHand.add(cardToAdd);
        cardsInDeck.add(cardToRemove);
        cardsInDeck.remove(cardToAdd);  // todo : what

        //set everything
        gameTable.setDeckCards(cardsInDeck);
        gameTable.setInHandsCards(cardsInHand); // todo what

        userTurn.setGameTable(gameTable);

        //increase number of veto times
        userTurn.setNumberOfVeto(userTurn.getNumberOfVeto() + 1);
        return new Result(true, "card replaced successfully " + userTurn.getNumberOfVeto());
    }

    //creating random cards in hand from deck
    public static ArrayList<Card> createRandomCardsInHand(ArrayList<Card> cards) {
        ArrayList<Card> handCards = new ArrayList<Card>();

        //set upperBound and LowerBound
        int lowerBound = 0;
        int upperBound = cards.size();

        //new Random Object
        Random random = new Random();

        // Create a set to store unique random numbers
        Set<Integer> uniqueNumbers = new HashSet<>();

        // Generate random numbers until we have 10 unique numbers
        while (uniqueNumbers.size() < 10) {
            int randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;
            uniqueNumbers.add(randomNumber);
        }

        //create a hand
        for (Integer integer : uniqueNumbers) handCards.add(cards.get(integer));


        //remove from deck
        // Convert the Set to a List
        List<Integer> list = new ArrayList<>(uniqueNumbers);

        // Sort the List
        Collections.sort(list);

        for (int i = list.size() - 1; i > 0; i--) {
            int a = list.get(i);
            cards.remove(a);
        }

        return handCards;
    }

    // showing hand
    public Result inHandDeck(int place) {
        //check if it has an option or not
        StringBuilder toPrint = new StringBuilder();

        if (place == -1) {
            //no having option
            toPrint.append("cards in hand:\n");
            int n = 1;
            for (Card card : userTurn.getGameTable().getInHandsCards())
                toPrint.append(n).append("- ").append(card.name()).append("\n");
        } else {
            Card card = userTurn.getGameTable().getInHandsCards().get(place - 1);
            toPrint.append(place).append("- ").append(card.name()).append("\n");
        }
        return new Result(true, toPrint.toString());
    }

    //remaining cards to play
    public Result remainingCardsToPlay() {
        StringBuilder toPrint = new StringBuilder();
        int n = 1;
        for (Card card : userTurn.getGameTable().getDeckCards()) {
            toPrint.append(n).append("- ");
            toPrint.append(card.name()).append("\n");
            n++;
        }
        return new Result(true, toPrint.toString());
    }

    //show out of play cards
    public Result outOfPlayCards() {
        StringBuilder toPrint = new StringBuilder();
        int n = 1;
        for (Card card : userTurn.getGameTable().getDeadCards()) {
            toPrint.append(n).append("- ");
            toPrint.append(card.name()).append("\n");
            n++;
        }
        return new Result(true, toPrint.toString());
    }

    //show cards in one row
    public Result cardsInRow(int rowNumber) {
        StringBuilder toPrint = new StringBuilder();
        if (rowNumber != 1 && rowNumber != 2 && rowNumber != 3)
            return new Result(false, "invalid row number");

        //check if there is a horn
        GameTable gameTable = userTurn.getGameTable();
        Card horn = gameTable.getCardsOfRow()[rowNumber - 1].getFirst();
        if (horn == null) toPrint.append("no horn in this row | ");
        else toPrint.append(horn.name()).append("|");

        //append all cards
        ArrayList<Card> cardsInRow = gameTable.getCardsOfRow()[rowNumber - 1].getSecond();
        for (Card card : cardsInRow) {
            toPrint.append(card.name()).append("| ");
        }
        return new Result(true, toPrint.toString());
    }

    //spells in play
    public Result spellsInPlay() {
        StringBuilder toPrint = new StringBuilder();
        ArrayList<Card> spellCards = spells;
        if (spellCards.isEmpty()) toPrint.append("No spell card");
        else {
            toPrint.append("| ");
            for (Card card : spellCards)
                toPrint.append(card.name()).append(" | ");
        }
        return new Result(true, toPrint.toString());
    }

    public Result placeCard(String cardName, int rowNumber) {
        userTurn.setPassed(false);

        //get card
        Card card = Card.getCardByName(cardName);
        //check if card is commander horn
        if (card == Card.CommanderHorn) {
            //get row
            Pair<Card, ArrayList<Card>> row = userTurn.getGameTable().getCardsOfRow()[rowNumber - 1];
            //get cards and add to row
            row.setFirst(card);
            ability(card, rowNumber);
            return new Result(true, "set");
        }

        //if card is not special
        //get row
        Pair<Card, ArrayList<Card>> row = userTurn.getGameTable().getCardsOfRow()[rowNumber - 1];
        //get cards and add to row
        ArrayList<Card> rowCards = row.getSecond();
        rowCards.add(card);

        //if card is special and number is 4
        if (rowNumber == 4)
            spells.add(card);

        return ability(card, rowNumber);

    }

    public Result checkroundWinner() {
        if (player1.isPassed() && player2.isPassed()) {
            UserInGame winner = null;
            if (calculateTotalScore(player1) > calculateTotalScore(player2)) {
//                player1.getUser().setWonGames(player1.getUser().getWonGames());
                GameTable gameTable = player1.getGameTable();
                gameTable.setHP(gameTable.getHP() - 1);
                winner = player1;
                return new Result(true, player1.getUser().getUsername() + " won round");
            }
            if (calculateTotalScore(player2) > calculateTotalScore(player1)) {
//                player2.getUser().setWonGames(player2.getUser().getWonGames());
                GameTable gameTable = player2.getGameTable();
                gameTable.setHP(gameTable.getHP() - 1);
                winner = player2;
                return new Result(true, player2.getUser().getUsername() + " won");
            }
            if (calculateTotalScore(player1) == calculateTotalScore(player2)) {
                niflgaardianPower(player1, player2);
            }
            maximizeScore(player1);
            maximizeScore(player2);


            //check Faction Abilities
            factionAbility(player1, player2, winner);



            //make everything empty and round++
            roundNumber++;
            spells = new ArrayList<>();
            Pair<Card, ArrayList<Card>>[] pair = new Pair[3];
            for (int i = 0; i < 3; i++) {
                pair[i] = new Pair<>(null, new ArrayList<>());
            }
            player1.getGameTable().setCardsOfRow(pair);
            player2.getGameTable().setCardsOfRow(pair);
        }
        return new Result(false, "no winner");
    }

    private void factionAbility(UserInGame player1, UserInGame player2, UserInGame winner) {
        Factions.realmsNorthernAbility(player1, player2, winner);
        Factions.scoiaTaelAbility(player1, player2, userTurn);
        Factions.skelligeAbility(player1 , player2);
    }

    private void maximizeScore(UserInGame player) {
        User user = player.getUser();
        int highestScore = user.getHighestScore();
        if (highestScore < calculateTotalScore(player))
            user.setHighestScore(highestScore);
    }

    private void niflgaardianPower(UserInGame player1, UserInGame player2) {
        UserInGame losser = null;
        if (player1.getGameTable().getLeader().getFaction() == Factions.EMPIRE_NILFGARDEN) losser = player1;
        else if (player2.getGameTable().getLeader().getFaction() == Factions.EMPIRE_NILFGARDEN) losser = player2;
        if (losser == null) return;
        GameTable gameTable = losser.getGameTable();
        gameTable.setHP(gameTable.getHP() - 1);
    }

    private Result ability(Card card, int rowNumber) {
        Result result = null;
        if (card.getAbility() == Ability.SPY) {
            result = Card.spy(userTurn);
        } else if (card.getAbility() == Ability.BERSERKER) {
            result = Card.berserker();
        } else if (card.getAbility() == Ability.DECOY) {
            //todo
        } else if (card.getAbility() == Ability.MEDIC) {
            result = Card.medic(userTurn);
        } else if (card.getAbility() == Ability.MARDROEME) {
            result = Card.mardroeme();
        } else if (card.getAbility() == Ability.COMMANDER_HORN) {
            result = Card.commandsHorn(userTurn, rowNumber);
        } else if (card.getAbility() == Ability.TRANSFORMERS) {
            result = Card.transformers();
        } else if (card.getAbility() == Ability.MUSTER) {
            result = Card.muster(userTurn, card, rowNumber);
        }
        return result;
    }

    public Result showCommander() {
        Leaders leaders = userTurn.getGameTable().getLeader();
//        if (leaders.name().equals())
        return new Result(true, leaders.name());
    }

    public Result commanderPowerPLay() {
        UserInGame current = userTurn;
        UserInGame opponent = null;
        if (userTurn == player1) {
            opponent = player2;
        } else {
            opponent = player1;
        }
        Leaders leader = userTurn.getGameTable().getLeader();

        return leaderPowerPLay(leader, current, opponent);
    }

    private Result leaderPowerPLay(Leaders leader, UserInGame current, UserInGame opponent) {
        Result result = null;
        if (leader == Leaders.BringerOfDeath) {
            result = leader.passiveBeringerOfDeath(current);
        } else if (leader == Leaders.CommanderOfTheRedRiders) {
            result = leader.passiveCommanderOfTheRedRiders(current, this);
        } else if (leader == Leaders.CrachAnCraite) {
            result = leader.passiveCrachAnCraite(current, opponent);
        } else if (leader == Leaders.DaisyOfTheValley) {
            result = leader.passiveDaisyOfTheValley(current);
        } else if (leader == Leaders.DestroyerOfWorlds) {
            result = leader.passiveDestroyerOfWorlds();
        } else if (leader == Leaders.EmperorOfNilfgaard) {
            result = leader.passiveEmperorOfNifgaard();
        } else if (leader == Leaders.HisImperialMajesty) {
            result = leader.passiveHisImperialMajesty(current);
        } else if (leader == Leaders.HopeOfTheAenSeidhe) {
            result = leader.passiveHopeOfTheAenSeidhe();
        } else if (leader == Leaders.InvaderOfTheNorth) {
            result = leader.passiveInvaderOfTheNorth(current, opponent);
        } else if (leader == Leaders.KingBran) {
            result = leader.passiveKingBran();
        } else if (leader == Leaders.KingOfTemeria) {
            result = leader.passiveKingOfTemeria(current);
        } else if (leader == Leaders.KingOfTheWildHunt) {
            result = leader.passiveKingOfTheWildHunt();
        } else if (leader == Leaders.TheWhiteFlame) {
            result = leader.passiveTheWhiteFalme(current, this);
        } else if (leader == Leaders.TheTreacherous) {
            result = leader.passiveTheTreacherous(current, opponent);
        } else if (leader == Leaders.TheSiegemaster) {
            result = leader.passiveTheSiegemaster(current, this);
        } else if (leader == Leaders.TheRelentless) {
            result = leader.passiveTheRelentless();
        } else if (leader == Leaders.TheBeautiful) {
            result = leader.passiveTheBeautiful(current);
        } else if (leader == Leaders.SonOfMedell) {
            result = leader.passiveSonOfMedell(opponent);
        } else if (leader == Leaders.QueenOfDolBlathanna) {
            result = leader.passiveQueenOfDolBlathanna(opponent);
        } else if (leader == Leaders.LordCommanderOfTheNorth) {
            result = leader.passiveLordCommanderOfNorth(opponent);
        } else if (leader == Leaders.PurebloodElf) {
            result = leader.passivePurebloodElf(this);
        }
        return result;
    }

    public Result showPlayersInfo() {
        StringBuilder info = new StringBuilder();
        info.append("player: ").append(player1.getUser().getUsername()).append(" Faction:")
                .append(player1.getUser().getUserPreGameInfo().getFaction()).append("\n");
        info.append("player: ").append(player2.getUser().getUsername()).append(" Faction:")
                .append(player2.getUser().getUserPreGameInfo().getFaction()).append("\n");
        return new Result(true, info.toString());
    }

    public Result showPlayersLives() {
        StringBuilder toPrint = new StringBuilder();
        toPrint.append(player1.getUser().getUsername()).append(" Hp :").append(player1.getGameTable().getHP()).append("\n");
        toPrint.append(player2.getUser().getUsername()).append(" Hp :").append(player2.getGameTable().getHP()).append("\n");
        return new Result(true, toPrint.toString());
    }

    public Result showNumberOfCardsInHand() {
        StringBuilder toPrint = new StringBuilder();
        toPrint.append("first player: ").append(player1.getUser().getUsername()).append(" number of cards in hand: ")
                .append(player1.getGameTable().getInHandsCards().size());
        toPrint.append("second player: ").append(player2.getUser().getUsername()).append(" number of cards in hand: ")
                .append(player2.getGameTable().getInHandsCards().size());
        return new Result(true, toPrint.toString());
    }

    public Result showTurnInfo() {
        return new Result(true, userTurn.getUser().getUsername() + "'s Turn");
    }

    public Result showTotalScore() {
        int firstPlayerTotalScore = calculateTotalScore(player1);
        int secondPlayerTotalScore = calculateTotalScore(player2);
        StringBuilder toOutout = new StringBuilder();
        toOutout.append(player1.getUser().getUsername()).append("'s total score").append(firstPlayerTotalScore).append("\n");
        toOutout.append(player2.getUser().getUsername()).append("'s total score").append(secondPlayerTotalScore).append("\n");

        return new Result(true, toOutout.toString());
    }

    public int calculateTotalScore(UserInGame player) {
        int sum = 0;
        sum += calculateScoreInRow(player, 1);
        sum += calculateScoreInRow(player, 2);
        sum += calculateScoreInRow(player, 3);
        return sum;
    }

    public int calculateScoreInRow(UserInGame player1, int i) {

        ArrayList<Card> cardInRow = player1.getGameTable().getCardsOfRow()[i - 1].getSecond();
        int sumPower = 0;
        for (Card card : cardInRow) {
            sumPower += card.getPower();
        }
        return sumPower;
    }

    public Result showTotalScoreOfRow(int rowNumber) {
        return null;
    }

    public Result passRound(UserInGame player) {
        player.setPassed(true);
        //check if game is over
        if (checkroundWinner().isSuccessful())
            return checkroundWinner();
        return changeTurn();
    }

    public boolean isOver() {
        boolean over = false;
        if (player1.getGameTable().getHP() == 0) {
            player2.getUser().setWonGames(player2.getUser().getWonGames() + 1);
            over = true;
        } else if (player2.getGameTable().getHP() == 0) {
            player1.getUser().setWonGames(player1.getUser().getWonGames() + 1);
            over = true;
        }
        return over;
    }

    public void dealCards() {
        //for first user
        ArrayList<Card> cardsInDeck1 = deckToArrayList(player1);
        GameTable gameTable1 = player1.getGameTable();
        gameTable1.setInHandsCards(createRandomCardsInHand(cardsInDeck1));
        gameTable1.setDeckCards(cardsInDeck1);


        //for second player
        ArrayList<Card> cardsInDeck2 = deckToArrayList(player2);
        GameTable gameTable2 = player2.getGameTable();
        gameTable2.setInHandsCards(createRandomCardsInHand(cardsInDeck2));
        gameTable2.setDeckCards(cardsInDeck2);
    }

    private ArrayList<Card> deckToArrayList(UserInGame player1) {
        //make an arrayList of cards in deck
        ArrayList<Card> cards = new ArrayList<>();
        for (Pair<Card, Integer> pair : player1.getUser().getUserPreGameInfo().getCardsInDeck()) {
            for (int j = 0; j < pair.getSecond(); j++) {
                cards.add(pair.getFirst());
            }
        }
        return cards;
    }

    public ArrayList<Card> getSpells() {
        return spells;
    }
}