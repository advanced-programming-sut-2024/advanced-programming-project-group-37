package model.gameTable;

import model.User;

public class UserInGame {
    private User user;
    private GameTable gameTable;
    private int numberOfVeto = 0; //this number is maximum 2, for checking number of times that can be vetoed

    public UserInGame(User user) {
        this.user = user;
        gameTable = new GameTable(user.getUserPreGameInfo().getCardsInDeck(),
                user.getUserPreGameInfo().getLeader());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    public void setGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public int getNumberOfVeto() {
        return numberOfVeto;
    }

    public void setNumberOfVeto(int numberOfVeto) {
        this.numberOfVeto = numberOfVeto;
    }
}