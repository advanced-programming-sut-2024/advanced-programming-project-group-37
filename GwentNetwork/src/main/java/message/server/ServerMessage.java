package message.server;

import server.model.toolClasses.Result;

import java.util.ResourceBundle;

public class ServerMessage {
    boolean success;
    String info;
    Result result;

    public ServerMessage(Boolean success, String info) {
        this.success = success;
        this.info = info;
    }
    public ServerMessage(Boolean success, String info,Result result) {
        this.success = success;
        this.info = info;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getInfo() {
        return info;
    }
}
