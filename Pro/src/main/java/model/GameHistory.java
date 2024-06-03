package model;

import model.toolClasses.Pair;

import java.util.ArrayList;
import java.util.Date;

public class GameHistory {
    private User winner;
    private User opponentPlayer;
    private Date dateOfGame;
    private double opponentFinalPoint;
    private double myFinalPoint;
    private ArrayList<Pair<Double, Double>> pointsInRound;
    public GameHistory(User winner, User opponentPlayer, Date dateOfGame, double opponentFinalPoint, double myFinalPoint,
                       ArrayList<Pair<Double, Double>> pointsInRound) {
        this.winner = winner;
        this.opponentPlayer = opponentPlayer;
        this.dateOfGame = dateOfGame;
        this.opponentFinalPoint = opponentFinalPoint;
        this.myFinalPoint = myFinalPoint;
        this.pointsInRound = pointsInRound;
    }
}
