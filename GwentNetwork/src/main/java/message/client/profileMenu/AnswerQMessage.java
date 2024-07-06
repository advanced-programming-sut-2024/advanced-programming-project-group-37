package message.client.profileMenu;

import message.client.ClientMessage;
import message.client.MessageType;

import java.util.regex.Matcher;

public class AnswerQMessage extends ClientMessage {

    private Matcher matcher;

    public AnswerQMessage(Matcher matcher) {
        this.matcher = matcher;
        this.type = MessageType.ANSWER_Q;
    }

    public Matcher getMatcher() {
        return matcher;
    }
}
