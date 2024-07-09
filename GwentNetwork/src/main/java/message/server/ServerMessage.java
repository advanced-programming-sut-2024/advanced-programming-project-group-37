package message.server;

import server.model.toolClasses.Result;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServerMessage {
    ServerType type;
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


    /**
     * @author FOAD
     * server message for
     *  SarchMessage request return a list of string of people with substring
     * and GiveMeOnlineFriend message returns a list of String of friends that are online
     */

    public ServerMessage(ArrayList<String> friendWithStr) {
        this.friends = friendWithStr;
    }
    /**
     * @author Foad
     * server message for
     * sending that the user has a new game request
     * sending that if the rand game created or not
     */
    String opponent;
    public ServerMessage(ServerType type, String username) {
        this.type = type;
        this.opponent = username;
    }




    //some getter
    public ServerType getType() {
        return type;
    }

    public String getOpponent() {
        return opponent;
    }
}
