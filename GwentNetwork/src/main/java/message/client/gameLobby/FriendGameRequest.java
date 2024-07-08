package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * @author iliya
 *
 * سلام سید
 * این برای بازی دوستان هست و خود بازی کن رو با توکن و حریف رو با یوزرنیم فرستادم
 */
public class FriendGameRequest extends ClientMessage {

    private String username;
    public FriendGameRequest(String token, String username) {
        this.username = username;
        this.token = token;
        this.type = MessageType.FREAND_GAME_RQUEST;
    }

    public String getUsername() {
        return username;
    }
}
