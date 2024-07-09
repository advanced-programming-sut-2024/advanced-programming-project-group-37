package message.client.pregame;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * @author iliya
 *
 * give me cardCollection and Deck with Arraylist<Card>
 */
public class GetCollectionDeck extends ClientMessage {
    public GetCollectionDeck(String token) {
        this.token = token;
        this.type = MessageType.GET_CARD_COLLECTION_DECK;
    }
}
