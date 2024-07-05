package message.client;

public class ClientMessage {
    protected String token;
    protected MassageType type;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public MassageType getType() {
        return type;
    }
}
