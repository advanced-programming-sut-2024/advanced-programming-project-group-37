package message.client.pregame;

import message.client.ClientMessage;
import message.client.MessageType;
import message.enums.card.Card;

public class RemoveFromDeck extends ClientMessage {
    private Card card;
    public RemoveFromDeck(String token, Card card) {
        this.token = token;
        this.type = MessageType.ADD_CARD_TO_DECK;
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
