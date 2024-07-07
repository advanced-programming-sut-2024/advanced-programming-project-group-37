package message.server;

import server.model.toolClasses.Result;

import java.util.ArrayList;

public class UpdateFriendRequestMessage extends ServerMessage {
    private ArrayList<String> friends;
    private ArrayList<String> fromWho;
    private ArrayList<String> date;
    private ArrayList<String> state;

    public UpdateFriendRequestMessage(ArrayList<String> friends, ArrayList<String> fromWho, ArrayList<String> date, ArrayList<String> state) {
        this.friends = friends;
        this.fromWho = fromWho;
        this.date = date;
        this.state = state;
    }

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
