package server.model.gameTable;

import server.model.User;

public class UserInGame {
    public int totalScore = 0;
    private User user;
    private GameTable gameTable;
    private boolean passed = false;
    private boolean siegeDouble = false; // for commander horn and some leaders
    private boolean closeCombatDouble = false; //for commander horn and some leaders
    private boolean rangedDouble = false; //for commander horn and some leaders
    private boolean spyDouble = false; //for some leaders
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

    public boolean isSiegeDouble() {
        return siegeDouble;
    }

    public void setSiegeDouble(boolean siegeDouble) {
        this.siegeDouble = siegeDouble;
    }

    public boolean isCloseCombatDouble() {
        return closeCombatDouble;
    }

    public void setCloseCombatDouble(boolean closeCombatDouble) {
        this.closeCombatDouble = closeCombatDouble;
    }

    public boolean isSpyDouble() {
        return spyDouble;
    }

    public void setSpyDouble(boolean spyDouble) {
        this.spyDouble = spyDouble;
    }

    public boolean isRangedDouble() {
        return rangedDouble;
    }

    public void setRangedDouble(boolean rangedDouble) {
        this.rangedDouble = rangedDouble;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}