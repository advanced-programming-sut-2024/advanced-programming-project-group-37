package message.client;

public class ClientMessage {
    protected String token;
    protected MessageType type;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public MessageType getType() {
        return type;
    }
}
