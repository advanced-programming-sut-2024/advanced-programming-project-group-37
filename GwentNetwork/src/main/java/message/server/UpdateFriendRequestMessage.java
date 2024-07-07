package message.server;

import server.model.toolClasses.Result;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateFriendRequestMessage extends ServerMessage{
    private ArrayList<String> friends;
    private ArrayList<String> friendRequests;

    public UpdateFriendRequestMessage(ArrayList<String> friends, ArrayList<String> friendRequests, Result result) {
        super(result);
        this.friends = friends;
        this.friendRequests = friendRequests;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public ArrayList<String> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(ArrayList<String> friendRequests) {
        this.friendRequests = friendRequests;
    }
}
