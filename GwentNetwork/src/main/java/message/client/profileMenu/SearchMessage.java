package message.client.profileMenu;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * @author iliya
 *
 * I need ArrayList<String>  of usernames that contain <str>
 *     give me them in friends in <ServerMessage>
 */
public class SearchMessage extends ClientMessage {
    private String str;

    public SearchMessage(String str) {
        this.type = MessageType.SEARCH;
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
