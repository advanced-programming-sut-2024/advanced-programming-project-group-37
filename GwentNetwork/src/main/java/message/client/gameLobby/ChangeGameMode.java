package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

public class ChangeGameMode extends ClientMessage {

    private boolean mode = true; // public -> true & private -> false

    public ChangeGameMode(boolean mode) {
        this.mode = mode;
        this.type = MessageType.CHANGE_GAME_MODE;
    }

    public boolean isMode() {
        return mode;
    }
}
