package com.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Quiz {
    int quizID;
    ArrayList<Question> questions;
    int[] timers;
    LeaderBoard leaderBoard;
    QuizDifficulty difficulty;

    public Quiz(int quizID, QuizDifficulty difficulty, Scanner sc) {
        this.quizID = quizID;
        this.difficulty = difficulty;
        this.questions = new ArrayList<>();
        this.leaderBoard = new LeaderBoard();
        // create the quiz
        this.createQuiz(sc);
        this.timers = new int[this.questions.size()];
        initializeTimers();
    }

    private void createQuiz(Scanner sc) {
        System.out.print("Enter number of questions for quiz: ");
        int num_of_questions = sc.nextInt();
        sc.nextLine();

        for (int ques_num = 1; ques_num <= num_of_questions; ques_num++) {
            System.out.print("\nEnter question " + ques_num + ": ");
            String question = sc.nextLine();

            System.out.print("\tEnter option 1: ");
            String op_1 = sc.nextLine();

            System.out.print("\tEnter option 2: ");
            String op_2 = sc.nextLine();

            System.out.print("\tEnter option 3: ");
            String op_3 = sc.nextLine();

            System.out.print("Enter correct answer option (enter 1/2/3): ");
            int correct_option = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter timer duration for this question (in seconds): ");
            int timerDuration = sc.nextInt();
            sc.nextLine();

            Question q = new Question(question, op_1, op_2, op_3, correct_option, timerDuration);
            this.questions.add(q);
            System.out.println("Question added successfully!\n");
        }
        System.out.println("Quiz created successfully!\n");
    }

    public double playQuiz(Scanner sc, int playerID) {
        Collections.shuffle(questions);
        int[] choices = new int[this.questions.size()];
        for (int i = 0; i < this.questions.size(); i++) {
            System.out.println(questions.get(i));
            System.out.print("Enter your answer (1/2/3): ");
            choices[i] = sc.nextInt();
            sc.nextLine();
            System.out.println();
        }
        return scoreQuiz(choices, playerID);
    }

    // public double playQuiz(Scanner sc) {
    // int[] choices = new int[this.questions.size()];

    // for (int i = 0; i < this.questions.size(); i++) {
    // System.out.println(questions.get(i));
    // int timerDuration = this.questions.get(i).timerDuration;

    // int[] selectedChoice = new int[1];
    // Thread[] inputThread = new Thread[1];

    // Thread timerThread = new Thread(() -> {
    // int time = timerDuration;
    // while (time > 0) {
    // try {
    // Thread.sleep(1000);
    // time--;
    // // System.out.print("\rTime remaining: " + time + " seconds");
    // } catch (InterruptedException e) {
    // break;
    // }
    // }
    // if (time <= 0) {
    // System.out.println("\nTime's up! Automatically submitting the quiz for this
    // question.");
    // selectedChoice[0] = -1;
    // }
    // });

    // timerThread.setDaemon(true);
    // timerThread.start();

    // inputThread[0] = new Thread(() -> {
    // System.out.print("\nEnter your answer (1/2/3): ");
    // selectedChoice[0] = sc.nextInt();
    // sc.nextLine();
    // timerThread.interrupt();
    // });

    // inputThread[0].start();

    // while (timerThread.isAlive()) {
    // try {
    // Thread.sleep(100);
    // } catch (InterruptedException e) {
    // inputThread[0].interrupt();
    // }
    // }
    // timerThread.interrupt();
    // choices[i] = selectedChoice[0];
    // }
    // return scoreQuiz(choices);
    // }

    public double scoreQuiz(int[] choices, int playerID) {
        System.out.println("\nQuiz Feedback: ");
        double correct = 0;
        double questions = (double) choices.length;
        for (int i = 0; i < choices.length; i++) {
            System.out.print("Question " + (i + 1) + ") ");
            if (choices[i] != 0 && this.questions.get(i).checkAnswer(choices[i])) {
                correct += 1.0;
                System.out.print("Your answer is correct!\n");
            } else {
                System.out.print(
                        "Your answer is incorrect. Expected answer: " + this.questions.get(i).getAnswer() + "\n");
            }
        }
        double score = (correct / questions) * 100;
        this.leaderBoard.updateLeaderboard(playerID, score);
        return score;
    }

    private void initializeTimers() {
        for (int i = 0; i < this.timers.length; i++) {
            this.timers[i] = this.questions.get(i).timerDuration;
        }
    }

    public void addNewQuestion(Scanner sc) {
        System.out.print("\n\tEnter question: ");
        String question = sc.nextLine();

        System.out.print("\t\tEnter option 1: ");
        String op_1 = sc.nextLine();

        System.out.print("\t\tEnter option 2: ");
        String op_2 = sc.nextLine();

        System.out.print("\t\tEnter option 3: ");
        String op_3 = sc.nextLine();

        System.out.print("Enter correct answer option (enter 1/2/3): ");
        int correct_option = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter timer duration for this question (in seconds): ");
        int timerDuration = sc.nextInt();
        sc.nextLine();

        Question q = new Question(question, op_1, op_2, op_3, correct_option, timerDuration);
        this.questions.add(q);
        System.out.println("\tQuestion added successfully!\n");
    }

    public void editQuestion(int ques_num, Scanner sc) {
        deleteQuestion(ques_num);
        addNewQuestionAtIndex(ques_num - 1, sc);
    }

    public void addNewQuestionAtIndex(int index, Scanner sc) {
        System.out.print("\n\tEnter question: ");
        String question = sc.nextLine();

        System.out.print("\t\tEnter option 1: ");
        String op_1 = sc.nextLine();

        System.out.print("\t\tEnter option 2: ");
        String op_2 = sc.nextLine();

        System.out.print("\t\tEnter option 3: ");
        String op_3 = sc.nextLine();

        System.out.print("Enter correct answer option (enter 1/2/3): ");
        int correct_option = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter timer duration for this question (in seconds): ");
        int timerDuration = sc.nextInt();
        sc.nextLine();

        Question q = new Question(question, op_1, op_2, op_3, correct_option, timerDuration);
        this.questions.add(index, q);
        System.out.println("\tQuestion added successfully!\n");
    }

    public void deleteQuestion(int ques_num) {
        this.questions.remove(ques_num - 1);
    }

    @Override
    public String toString() {
        if (this.questions.isEmpty()) {
            return "Quiz is empty.";
        }
        String quiz = "";
        int ques_num = 1;
        for (Question question : this.questions) {
            quiz += "\n" + ques_num + ")\n" + question.toString();
            ques_num++;
        }
        return quiz;
    }
}
