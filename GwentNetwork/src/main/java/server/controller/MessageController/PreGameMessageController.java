package server.controller.MessageController;

import message.client.pregame.ChangeFaction;
import message.client.pregame.GetFactionMessage;
import message.enums.gameMenu.Factions;
import message.server.ServerMessage;
import server.controller.GameController.PreGameMenuController;
import server.model.User;

public class PreGameMessageController {
    public static void changeFactionNetwork(ChangeFaction msg) {
        User user;
    }

    public static ServerMessage getFaction(GetFactionMessage msg) {
        User user = User.getUserByToken(msg.getToken());
        Factions factions = user.getUserPreGameInfo().getFaction();
        return new ServerMessage(factions);
    }
}
