package message.client.Game;

import message.client.ClientMessage;
import message.client.MessageType;
import message.enums.card.Card;

public class PlayCard extends ClientMessage {

    private Card card;
    private int row;

    public PlayCard(String token, Card card, int row) {
        this.token = token;
        this.type = MessageType.PLAY_CARD;
        this.card = card;
        this.row = row;
    }

    public Card getCard() {
        return card;
    }

    public int getRow() {
        return row;
    }
}
