package message.client.Game;

import message.client.ClientMessage;
import message.client.MessageType;

public class Chat extends ClientMessage {

    private String message, time;
    public Chat(String token, String message, String time) {
        this.time = time;
        this.token = token;
        this.message = message;
        this.type = MessageType.CHAT;
    }

    // اسمشو عوض نکن خراب میشه نباید اسمش e داشته باشه
    public String getMassage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
