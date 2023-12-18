package com.example;

import java.util.HashMap;

public class Player {
    int playerID;
    String name;
    HashMap<Integer, Double> player_quizzes;

    public Player(int playerID, String name) {
        this.playerID = playerID;
        this.name = name;
        this.player_quizzes = new HashMap<>();
    }

    public void updateScore(int quizID, double score) {
        this.player_quizzes.put(quizID, score);
    }

    public double getScore(int quizID) {
        if (this.player_quizzes.containsKey(quizID)) {
            return this.player_quizzes.get(quizID);
        } else {
            System.out.println("Player did not take this quiz.\n");
            return Double.MIN_VALUE;
        }
    }

    @Override
    public String toString() {
        return this.playerID + "," + this.name;
    }
}
