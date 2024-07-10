package server.controller.MessageController;

import message.client.Game.GetHand;
import message.client.Game.GiveMeLeader;
import message.enums.card.Card;
import message.server.ServerMessage;
import server.controller.GameController.GameMenuController;
import server.model.User;
import server.model.gameTable.GameTable;
import server.model.gameTable.UserInGame;
import server.model.toolClasses.Pair;

import java.util.ArrayList;

public class GameMessageController {
    public static ServerMessage passLeader(GiveMeLeader msg) {
        GameMenuController game = GameMenuController.getGame(msg.getToken());
        User user = User.getUserByToken(msg.getToken());

        if (game.getPlayer1().getUser() == user) {
            return new ServerMessage(game.getPlayer1().getGameTable().getLeader(), game.getPlayer2().getGameTable().getLeader());
        } else {
            return new ServerMessage(game.getPlayer2().getGameTable().getLeader(), game.getPlayer1().getGameTable().getLeader());
        }
    }

    public static ServerMessage getGameTable(GetHand msg) {
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
}
