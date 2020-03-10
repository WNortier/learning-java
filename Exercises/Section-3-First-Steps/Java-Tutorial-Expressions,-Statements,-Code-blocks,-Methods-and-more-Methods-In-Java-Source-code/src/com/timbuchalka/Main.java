package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        int highScore = calculateScore(gameOver, score, levelCompleted, bonus);
        System.out.println("Your final score was " + highScore);

        score = 10000;
        levelCompleted = 8;
        bonus = 200;

        highScore = calculateScore(gameOver, score, levelCompleted, bonus);
        System.out.println("Your final score was " + highScore);


        int highScorePosition = calculateHighScorePosition(1500);
        displayHighScorePosition("Tim", highScorePosition);


    }

    public static int calculateScore(boolean gameOver,int highScore, int levelCompleted, int bonus) {
        if(gameOver) {
            int score = highScore + (levelCompleted * bonus);
            score += 2000;
            return score;
        }
        return -1;
    }




    public static void displayHighScorePosition(String name, int highScorePosition) {
        System.out.println(name + " managed to get into position " + highScorePosition);
    }

    public static int calculateHighScorePosition(int score) {
        if (score >= 1000) {
            return 1;
        }
        else if (score >= 500 && score < 1000) {
            return 2;
        }
        else if (score >= 100 && score < 500) {
            return 3;
        }
        else {
            return 4;
        }
    }


}
