package message.client.pregame;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * @author iliya
 *
 * change faction with factionName
 */
public class ChangeFaction extends ClientMessage {

    private String factionName;

    public ChangeFaction(String token, String factionName) {
        this.token = token;
        this.type = MessageType.CHANGE_FACTION;
        this.factionName = factionName;
    }

    public String getFactionName() {
        return factionName;
    }
}
