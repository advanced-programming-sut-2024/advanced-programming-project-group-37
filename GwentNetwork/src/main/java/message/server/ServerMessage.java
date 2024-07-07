package message.server;

import server.model.toolClasses.Result;

import java.util.ResourceBundle;

public class ServerMessage {
    boolean success;
    String info;
    String token;

    public ServerMessage(Boolean success, String info) {
        this.success = success;
        this.info = info;
    }
    public ServerMessage(Result result) {
        this.success = result.isSuccessful();
        this.info = result.getMessage();
    }
    public ServerMessage(Result result, String token){
        this.success = result.isSuccessful();
        this.info = result.getMessage();
        this.token = token;
    }
    public ServerMessage(){
        //an empty constructor
    }

    public boolean isSuccess() {
        return success;
    }

    public String getInfo() {
        return info;
    }

    public String getToken() {
        return token;
    }
}
