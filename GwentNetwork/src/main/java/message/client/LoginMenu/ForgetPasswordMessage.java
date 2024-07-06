package message.client.LoginMenu;

import message.client.ClientMessage;
import message.client.MessageType;

import java.util.regex.Matcher;

public class ForgetPasswordMessage extends ClientMessage {

    Matcher matcher;

    public ForgetPasswordMessage(Matcher matcher) {
        this.matcher = matcher;
        this.type = MessageType.FORGET_PASSWORD;
    }

    public Matcher getMatcher() {
        return matcher;
    }
}
