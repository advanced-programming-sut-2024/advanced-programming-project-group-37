package server.controller.MessageController;

import message.client.Game.GiveMeLeader;
import message.enums.card.Leaders;
import message.server.ServerMessage;
import server.controller.GameController.GameMenuController;
import server.model.User;

public class GameMessageController {
    public static ServerMessage passLeader(GiveMeLeader msg) {
        GameMenuController game = GameMenuController.getGame(msg.getToken());
        User user = User.getUserByToken(msg.getToken());

        if (game.getPlayer1().getUser() == user) {
            return new ServerMessage(game.getPlayer1().getGameTable().getLeader(), game.getPlayer2().getGameTable().getLeader());
        }
        else {
            return new ServerMessage(game.getPlayer2().getGameTable().getLeader(), game.getPlayer1().getGameTable().getLeader());
        }
    }
}
