package server.controller.MessageController;

import message.client.Game.GetHand;
import message.client.Game.GiveMeLeader;
import message.client.Game.SelectVetoCard;
import message.client.MessageType;
import message.enums.card.Card;
import message.server.ServerMessage;
import server.controller.GameController.GameMenuController;
import server.model.User;
import server.model.gameTable.GameTable;
import server.model.gameTable.UserInGame;
import server.model.toolClasses.Pair;

import java.util.ArrayList;

public class GameMessageController {
    public static ServerMessage passLeaderNetwork(GiveMeLeader msg) {
        GameMenuController game = GameMenuController.getGame(msg.getToken());
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
        UserInGame userInGame;
        if (game.getPlayer1().getUser() == user) {
            userInGame = game.getPlayer1();
        } else {
            userInGame = game.getPlayer2();
        }
        GameTable gameTable = userInGame.getGameTable();
        Pair<Card, ArrayList<Card>>[] cards = gameTable.getCardsOfRow();
        int hp = gameTable.getHP();
        ArrayList<Card> inHand = gameTable.getInHandsCards();
        ArrayList<Card> spells = gameTable.getSpsells();

        return new ServerMessage(cards, hp, inHand, spells);
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
}
