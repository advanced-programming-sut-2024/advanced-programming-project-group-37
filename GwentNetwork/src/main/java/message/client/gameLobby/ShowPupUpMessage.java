package message.client.gameLobby;

import message.client.ClientMessage;

/**
 * seyyed you have to check friend like to go to game with this username
 */
public class ShowPupUpMessage extends ClientMessage {

    private String username; // نام حریفه سید

    public ShowPupUpMessage(String token, String username) {

    }

    public String getUsername() {
        return username;
    }
}
