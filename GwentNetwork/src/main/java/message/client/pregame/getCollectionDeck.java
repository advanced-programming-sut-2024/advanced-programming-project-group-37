package message.client.pregame;

import message.client.ClientMessage;

/**
 * @author iliya
 *
 * give me cardCollection and Deck with Arraylist<Card>
 */
public class getCollectionDeck extends ClientMessage {
    public getCollectionDeck(String token) {
        this.token = token;
    }
}
