package message.client;

public class LoginMessage extends ClientMessage{
    String username, password;

    public LoginMessage(String username, String password) {
        this.username = username;
        this.password = password;

        this.type = MessageType.LOGIN;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
