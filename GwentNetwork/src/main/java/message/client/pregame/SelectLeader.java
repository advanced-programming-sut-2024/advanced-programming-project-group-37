package message.client.pregame;

import message.client.ClientMessage;
import message.client.MessageType;

public class SelectLeader extends ClientMessage {

    private String leaderName;

    public SelectLeader(String token, String leaderName) {
        this.token = token;
        this.type = MessageType.SELECT_LEADER;
        this.leaderName = leaderName;
    }

    public String getLeaderName() {
        return leaderName;
    }
}
