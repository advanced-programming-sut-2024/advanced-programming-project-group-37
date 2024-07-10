package server.controller.MessageController;

import message.client.Game.*;
import message.client.gameLobby.CheckServerMessage;
import message.enums.card.Card;
import message.server.ServerMessage;
import message.server.ServerType;
import server.controller.GameController.GameMenuController;
import server.model.User;
import server.model.gameTable.GameTable;
import server.model.gameTable.UserInGame;
import server.model.toolClasses.Pair;
import server.model.toolClasses.Result;

import java.util.ArrayList;

public class GameMessageController {
    public static ServerMessage passLeaderNetwork(GiveMeLeader msg) {
        GameMenuController game = GameMenuController.getGame(msg.getToken());
        if (game == null){
            System.out.println("null");
        }
        User user = User.getUserByToken(msg.getToken());

        if (game.getPlayer1().getUser() == user) {
            return new ServerMessage(game.getPlayer1().getGameTable().getLeader(), game.getPlayer2().getGameTable().getLeader());
        } else {
            return new ServerMessage(game.getPlayer2().getGameTable().getLeader(), game.getPlayer1().getGameTable().getLeader());
        }
    }

    public static ServerMessage getGameTableNetwork(GetHand msg) {
        GameMenuController game = GameMenuController.getGame(msg.getToken());
        User user = User.getUserByToken(msg.getToken());
        UserInGame myUserInGame;
        UserInGame opponentUserInGame;
        if (game.getPlayer1().getUser() == user) {
            myUserInGame = game.getPlayer1();
            opponentUserInGame = game.getPlayer2();
        } else {
            myUserInGame = game.getPlayer2();
            opponentUserInGame = game.getPlayer1();
        }

        //first get both side game table
        GameTable mygameTable = myUserInGame.getGameTable();
        GameTable opponentGameTable = opponentUserInGame.getGameTable();

        //get card in row for both player
        Pair<Card, ArrayList<Card>>[] myCards = mygameTable.getCardsOfRow();
        Pair<Card, ArrayList<Card>>[] opponentCards = opponentGameTable.getCardsOfRow();

        //get hp
        int myHp = mygameTable.getHP();
        int opponentHp = opponentGameTable.getHP();

        //get card in hand
        ArrayList<Card> myInHand = mygameTable.getInHandsCards();
        ArrayList<Card> opponentHand = opponentGameTable.getInHandsCards();

        //get the points of all rows and total for each
        ArrayList<Integer> myScores = calculateScore(myUserInGame, game);
        ArrayList<Integer> opponentScores = calculateScore(opponentUserInGame, game);
        //get deck
        ArrayList<Card> myDeck = mygameTable.getDeckCards();
        ArrayList<Card> opponentDeck = opponentGameTable.getDeckCards();
        //get dead
        ArrayList<Card> myDead = mygameTable.getDeadCards();
        ArrayList<Card> opponentDead = opponentGameTable.getDeadCards();

        //get spell
        ArrayList<Card> spells = game.getSpells();

        return new ServerMessage(myCards, opponentCards, spells, myHp, opponentHp, myInHand, opponentHand, myScores, opponentScores, myDeck, opponentDeck, myDead, opponentDead);
    }

    private static ArrayList<Integer> calculateScore(UserInGame userInGame, GameMenuController game) {
        ArrayList<Integer> returnInt = new ArrayList<>();
        returnInt.add(game.calculateScoreInRow(userInGame, 1));
        returnInt.add(game.calculateScoreInRow(userInGame, 2));
        returnInt.add(game.calculateScoreInRow(userInGame, 3));
        returnInt.add(game.calculateTotalScore(userInGame));
        return returnInt;
    }

    public static ServerMessage selectVetoNetwork(SelectVetoCard msg) {
        //get user game
        GameMenuController game = GameMenuController.getGame(msg.getToken());
        User user = User.getUserByToken(msg.getToken());
        UserInGame userInGame;
        //get user userInGame
        if (game.getPlayer1().getUser() == user) {
            userInGame = game.getPlayer1();
        } else {
            userInGame = game.getPlayer2();
        }
        ArrayList<Card> inHand = userInGame.getGameTable().getInHandsCards();
        game.vetoCard(inHand.get(Integer.parseInt(msg.getIndexOfVetoCard())).getName());


        //return number of veto in message
        return new ServerMessage(userInGame.getNumberOfVeto());

    }

    public static ServerMessage changeTurn(ChangeTurn msg) {
        GameMenuController game = GameMenuController.getGame(msg.getToken());
        User user = User.getUserByToken(msg.getToken());
        game.changeTurn();
        return new ServerMessage();
    }

    public static ServerMessage passTurn(PassTurn msg) {
        GameMenuController game = GameMenuController.getGame(msg.getToken());
        User user = User.getUserByToken(msg.getToken());

        UserInGame myUserInGame;
        if (game.getPlayer1().getUser() == user) {
            myUserInGame = game.getPlayer1();
        } else {
            myUserInGame = game.getPlayer2();
        }
        Result result = game.passRound(myUserInGame);

        return new ServerMessage(result);
    }

    public static ServerMessage placeCardNetwork(PlayCard msg) {
        GameMenuController game = GameMenuController.getGame(msg.getToken());
        User user = User.getUserByToken(msg.getToken());

        game.placeCard(msg.getCard().getName(), msg.getRow());


        return new ServerMessage();
    }

    public static ServerMessage checkServer(CheckServerMessage msg) {
        GameMenuController game = GameMenuController.getGame(msg.getToken());
        User user = User.getUserByToken(msg.getToken());

        //check if game is over
        Pair<Boolean, UserInGame> over = game.isOver();
        if (over.getFirst()) {
            return new ServerMessage(ServerType.END_GAME, over.getSecond().getUser().getUsername(),
                    over.getSecond().getUser().getUsername().equals(user.getUsername()));
        }


        //check if winner was found
        int tempRound = game.getTempRound();
        int round = game.getRoundNumber();

        if (tempRound < round) {
            game.setTempRound(tempRound + 1);
            boolean bool = false;
            String winnerName = null;
            if (game.getRoundWinner() != null) {
                bool = user.getUsername().equals(game.getRoundWinner().getUser().getUsername());
                winnerName = game.getRoundWinner().getUser().getUsername();
            }
            return new ServerMessage(ServerType.NEW_ROUND, winnerName, false);
        }

        //check if turn backed to him or not
        if (user.isYourTurn()) {
            user.setYourTurn(false);
            return new ServerMessage(ServerType.YOUR_TURN);
        }


        return new ServerMessage();
    }

    public static ServerMessage whoseTurnServer(WhoseTrun msg) {
        GameMenuController game = GameMenuController.getGame(msg.getToken());
        User user = User.getUserByToken(msg.getToken());
        //deal cards to players
        game.dealCards();

        if (game.getUserTurn().getUser() == user) {
            return new ServerMessage(true);
        } else
            return new ServerMessage(false);
    }
}
