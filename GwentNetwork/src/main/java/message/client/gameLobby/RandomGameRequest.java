package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

/**
 * @author iliya
 *
 * سید ببین این درخواست بازیه رندمه
 * تو باید تلاش کنی کسی رو پیدا کنی که همین درخواست رو اره و بعد
 * ServerMessage.isSuccess() -> true
 */
public class RandomGameRequest extends ClientMessage {

    public RandomGameRequest(String token) {
        this.token = token;
        this.type = MessageType.RANDOM_GAME_REQUEST;
    }
}
