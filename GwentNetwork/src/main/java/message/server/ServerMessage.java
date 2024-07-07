package message.server;

import server.model.toolClasses.Result;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServerMessage {
    boolean success;
    String info;
    String token;
    // for friend requests
    private ArrayList<String> friends = new ArrayList<>();
    private ArrayList<String> fromWho = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private ArrayList<String> state = new ArrayList<>();


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
    public ServerMessage(ArrayList<String> friends, ArrayList<String> fromWho, ArrayList<String> date, ArrayList<String> state) {
        this.friends = friends;
        this.fromWho = fromWho;
        this.date = date;
        this.state = state;
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

    //for friend requests
    public ArrayList<String> getFriends() {
        return friends;
    }


    public ArrayList<String> getFromWho() {
        return fromWho;
    }

    public ArrayList<String> getDate() {
        return date;
    }

    public ArrayList<String> getState() {
        return state;
    }
}
