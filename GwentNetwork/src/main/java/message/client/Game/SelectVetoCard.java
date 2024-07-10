package message.client.Game;

import message.client.ClientMessage;
import message.client.MessageType;

public class SelectVetoCard extends ClientMessage {

    private String indexOfVetoCard;

    public SelectVetoCard(String token, String indexOfVetoCard) {
        this.token = token;
        this.type = MessageType.SELECT_VETO_CARD;
        this.indexOfVetoCard = indexOfVetoCard;
    }

    public String getIndexOfVetoCard() {
        return indexOfVetoCard;
    }
}
