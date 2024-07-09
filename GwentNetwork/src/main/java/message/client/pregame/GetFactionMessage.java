package message.client.pregame;

import message.client.ClientMessage;
import message.client.MessageType;

public class GetFactionMessage extends ClientMessage {

    public GetFactionMessage(String token) {
        this.token = token;
        this.type = MessageType.GET_FACTION;
    }
}
