package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * @author iliya
 *
 * user back to main menu from gameLobby
 * so  online or inGame -> offline
 */
public class BackToMainMenu extends ClientMessage {
    public BackToMainMenu(String toke) {
        this.token = toke;
        this.type = MessageType.BACK_OFFLINE;
    }
}
