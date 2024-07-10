package message.client.Game;

import message.client.ClientMessage;
import message.client.MessageType;

/*
 سید این پس ترنه نه چینج ترن
 سید باید بهم مثل ریزالتی که قبلا میدادی بدی
 یعنی isSuccess = true if game have winner or end with draw
 و info = result.getMessage
 */
public class PassTurn extends ClientMessage {

    public PassTurn(String token) {
        this.token = token;
        this.type = MessageType.PASS_TURN;
    }
}
