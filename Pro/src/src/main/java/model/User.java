package model;

import model.enums.loginMenu.ConfirmQuestions;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String answer;
    private ConfirmQuestions confirmQuestions;
    private static User loggedInUser;
    private double highestScore;
    private int wonGames;
    private int lostGames;
    private int drawGames;
    private int totalPlayedGames;
    private UserPreGameInfo userPreGameInfo;
    private static ArrayList<User> allUsers = new ArrayList<>();
    private ArrayList<GameHistory> allPlayedGamesHistory; // for command "GameHistory history -n <n>"

    public User(ConfirmQuestions confirmQuestions, String... input) {
        username = input[0];
        password = input[1];
        nickname = input[2];
        email = input[3];
        answer = input[4];

        this.confirmQuestions = confirmQuestions;
        this.highestScore = 0;
        this.wonGames = 0;
        this.lostGames = 0;
        this.drawGames = 0;
        this.totalPlayedGames = 0;
        this.userPreGameInfo = new UserPreGameInfo();
        this.allPlayedGamesHistory = new ArrayList<>();

        allUsers.add(this);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }

    //get user rank
    public int getRank(){return 0;}
    public static User getUserByUsername(String username){
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getUsername().equals(username))
                return allUsers.get(i);
        }
        return null;
    }


    //getter and setter

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ConfirmQuestions getConfirmQuestions() {
        return confirmQuestions;
    }

    public void setConfirmQuestions(ConfirmQuestions confirmQuestions) {
        this.confirmQuestions = confirmQuestions;
    }

    public double getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(double highestScore) {
        this.highestScore = highestScore;
    }

    public int getWonGames() {
        return wonGames;
    }

    public void setWonGames(int wonGames) {
        this.wonGames = wonGames;
    }

    public int getLostGames() {
        return lostGames;
    }

    public void setLostGames(int lostGames) {
        this.lostGames = lostGames;
    }

    public int getDrawGames() {
        return drawGames;
    }

    public void setDrawGames(int drawGames) {
        this.drawGames = drawGames;
    }

    public int getTotalPlayedGames() {
        return totalPlayedGames;
    }

    public void setTotalPlayedGames(int totalPlayedGames) {
        this.totalPlayedGames = totalPlayedGames;
    }

    public UserPreGameInfo getUserPreGameInfo() {
        return userPreGameInfo;
    }

    public void setUserPreGameInfo(UserPreGameInfo userPreGameInfo) {
        this.userPreGameInfo = userPreGameInfo;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        User.allUsers = allUsers;
    }

    public ArrayList<GameHistory> getAllPlayedGamesHistory() {
        return allPlayedGamesHistory;
    }

    public void setAllPlayedGamesHistory(ArrayList<GameHistory> allPlayedGamesHistory) {
        this.allPlayedGamesHistory = allPlayedGamesHistory;
    }
}
