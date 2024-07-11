package model;

import model.toolClasses.Pair;

import java.time.LocalDate;
import java.util.ArrayList;

public class GameHistory {
    private User winner;
    private User losser;
    private LocalDate dateOfGame;
    private double losserFinalPoint;
    private double winnerTotalPoint;
    private ArrayList<Pair<Double, Double>> pointsInRound;
    public GameHistory(User winner, User opponentPlayer, LocalDate dateOfGame, double opponentFinalPoint, double myFinalPoint) {
        this.winner = winner;
        this.losser = opponentPlayer;
        this.dateOfGame = dateOfGame;
        this.losserFinalPoint = opponentFinalPoint;
        this.winnerTotalPoint = myFinalPoint;
    }

    public User getWinner() {
        return winner;
    }

    public User getLosser() {
        return losser;
    }

    public LocalDate getDateOfGame() {
        return dateOfGame;
    }

    public double getLosserFinalPoint() {
        return losserFinalPoint;
    }

    public double getWinnerTotalPoint() {
        return winnerTotalPoint;
    }
}
