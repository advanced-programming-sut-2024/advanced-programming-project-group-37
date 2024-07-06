package message.client;

import message.client.ClientMessage;
import message.client.MessageType;

import java.util.regex.Matcher;

public class AnswerQMessage extends ClientMessage {
    String username;

    private Matcher matcher;

    public AnswerQMessage(Matcher matcher, String username) {
        this.username = username;
        this.matcher = matcher;
        this.type = MessageType.ANSWER_Q;
    }

    public String getUsername() {
        return username;
    }

    public Matcher getMatcher() {
        return matcher;
    }
}
