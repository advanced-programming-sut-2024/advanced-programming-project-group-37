package message.client.gameLobby;

import message.client.ClientMessage;
import message.client.MessageType;

/**
سید لیست بازی ها رو به صورت دوتا اری لیست از استرینگ برای دوتا بازی کن
یعنی ایندکس 0 یکی با اون اری لیست دیگه با هم بازی کنن

بهم بده

این دوتا اری لیست رو هم از قببل داری
<friends>, <fromWho>
 */
public class GetListOfGame extends ClientMessage {

    public GetListOfGame(String token) {
        this.token = token;
        this.type = MessageType.GET_LIST_OF_GAMES;
    }
}
