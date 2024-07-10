package message.client.LoginMenu;

import message.client.ClientMessage;
import message.client.MessageType;

import java.util.regex.Matcher;

public class ForgetPasswordMessage extends ClientMessage {

    String matcher;

    public ForgetPasswordMessage(String matcher) {
        this.matcher = matcher;
        this.type = MessageType.FORGET_PASSWORD;
    }

    public String getMatcher() {
        return matcher;
    }
}
