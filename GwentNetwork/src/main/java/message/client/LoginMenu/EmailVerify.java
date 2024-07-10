package message.client.LoginMenu;

import message.client.ClientMessage;
import message.client.MessageType;

public class EmailVerify extends ClientMessage {
    String code;
    String email;
    public EmailVerify(String code, String email) {
        this.email = email;
        this.code = code;
        this.type = MessageType.VERIFY_EMAIL;
    }

    public String getCode() {
        return code;
    }

    public String getEmail() {
        return email;
    }
}
