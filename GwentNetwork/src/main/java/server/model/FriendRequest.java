package server.model;

import java.time.LocalDate;

public class FriendRequest {
    private LocalDate date;
    private User fromUser;
    private User toUser;
    private String state = new String(); // accepted, rejected, waiting
    public FriendRequest(LocalDate date, User fromUser, User toUser){
        this.date = date;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.state = "waiting";
    }

    public User getFromUser() {
        return fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public String getState() {
        return state;
    }

    public LocalDate getDate() {
        return date;
    }
}
