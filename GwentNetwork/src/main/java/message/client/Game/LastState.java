package message.client.Game;

import javafx.scene.image.Image;
import message.client.ClientMessage;
import message.client.MessageType;

public class LastState extends ClientMessage {
    public LastState(String token) {
        this.type = MessageType.LAST_STATE;
        this.token = token;
    }
}
