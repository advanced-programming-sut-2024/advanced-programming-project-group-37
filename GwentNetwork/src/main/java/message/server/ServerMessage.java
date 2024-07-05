package message.server;

public class ServerMessage {
    boolean success;
    String info;

    public ServerMessage(Boolean success, String info) {
        this.success = success;
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getInfo() {
        return info;
    }
}
