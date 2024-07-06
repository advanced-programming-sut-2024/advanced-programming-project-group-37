package message.client;

import java.util.regex.Matcher;

public class SetNewPasswordMessage extends ClientMessage {
    Matcher matcher;
    String username;

    public SetNewPasswordMessage(Matcher matcher, String username) {
        this.matcher = matcher;
        this.username = username;
        this.type = MessageType.SET_NEW_PASSWORD;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public String getUsername() {
        return username;
    }
}
