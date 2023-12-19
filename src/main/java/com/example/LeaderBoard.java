package com.example;

import java.util.Arrays;

public class LeaderBoard {
    private int[] playerIDs;
    private double[] scores;
    private int size;

    public LeaderBoard() {
        this.playerIDs = new int[5];
        this.scores = new double[5];
        Arrays.fill(playerIDs, -1);
        Arrays.fill(scores, -1.0);
        this.size = 0;
    }

    public void updateLeaderboard(int playerID, double score) {
        if (size <= 5 || score > scores[0]) {
            int insertIndex = findInsertIndex(score);
            shiftElementsDown(insertIndex);
            playerIDs[insertIndex] = playerID;
            scores[insertIndex] = score;
            if (size < 5) {
                size++;
            }
        }
    }

    private int findInsertIndex(double score) {
        int index = 0;
        while (index <= size && score <= scores[index]) {
            index++;
        }
        return index;
    }

    private void shiftElementsDown(int startIndex) {
        for (int i = size - 1; i > startIndex; i--) {
            playerIDs[i] = playerIDs[i - 1];
            scores[i] = scores[i - 1];
        }
    }

    public void displayLeaderboard() {
        System.out.println("Leaderboard:");

        for (int i = 0; i < size; i++) {
            System.out.println("Rank " + (i + 1) + ") Player " + playerIDs[i] + " with score " + scores[i] + "%");
        }

        System.out.println();
    }
}
