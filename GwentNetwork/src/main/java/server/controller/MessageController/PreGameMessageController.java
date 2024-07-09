package server.controller.MessageController;

import message.client.pregame.*;
import message.enums.card.Card;
import message.enums.gameMenu.Factions;
import message.server.ServerMessage;
import server.controller.GameController.PreGameMenuController;
import server.model.User;
import server.model.toolClasses.Pair;

import java.util.ArrayList;

public class PreGameMessageController {
    public static ServerMessage changeFactionNetwork(ChangeFaction msg) {
        PreGameMenuController pregame = PreGameMenuController.getPregame(User.getUserByToken(msg.getToken()));
        User user = User.getUserByToken(msg.getToken());
        pregame.selectFaction(msg.getFactionName(), user);
        return new ServerMessage();
    }

    public static ServerMessage getFaction(GetFactionMessage msg) {
        User user = User.getUserByToken(msg.getToken());
        Factions factions = user.getUserPreGameInfo().getFaction();
        return new ServerMessage(factions);
    }

    public static ServerMessage selectLeader(SelectLeader msg) {
        User user = User.getUserByToken(msg.getToken());
        String leader = msg.getLeaderName();
        PreGameMenuController pregame = PreGameMenuController.getPregame(User.getUserByToken(msg.getToken()));

        pregame.selectLeader(leader, user);
        return new ServerMessage();
    }

    public static ServerMessage getCards(GetCollectionDeck msg) {
        PreGameMenuController pregame = PreGameMenuController.getPregame(User.getUserByToken(msg.getToken()));
        User user = User.getUserByToken(msg.getToken());

        ArrayList<Pair<Card, Integer>> deck = user.getUserPreGameInfo().getCardsInDeck();
        ArrayList<Pair<Card, Integer>> collection = user.getUserPreGameInfo().getCardCollection();

        return new ServerMessage(deck, collection);
    }

    public static ServerMessage addCardToDeck(AddToDeck msg) {
        PreGameMenuController pregame = PreGameMenuController.getPregame(User.getUserByToken(msg.getToken()));
        User user = User.getUserByToken(msg.getToken());
        Card card = msg.getCard();

        pregame.addToDeck(card.getName(), 1, user);

        return new ServerMessage();
    }

    public static ServerMessage checkDeckIsOkNetwork(CheckDeckIsOk msg) {
        PreGameMenuController pregame = PreGameMenuController.getPregame(User.getUserByToken(msg.getToken()));
        User user = User.getUserByToken(msg.getToken());

        pregame.checkDeckIsOk(user);
    }
}
