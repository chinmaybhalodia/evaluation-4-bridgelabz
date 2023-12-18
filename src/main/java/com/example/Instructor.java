package com.example;

import java.util.HashMap;
import java.util.Scanner;

public class Instructor {
    int instructorID;
    String name;
    HashMap<Integer, Quiz> quizzes;

    public Instructor(int instructorID, String name) {
        this.instructorID = instructorID;
        this.name = name;
        quizzes = new HashMap<>();
    }

    public void addQuiz(int quizID, Quiz quiz) {
        this.quizzes.put(quizID, quiz);
    }

    public void manageQuiz(int quizID, Scanner sc) {
        if (this.quizzes.containsKey(quizID)) {
            Quiz quiz = quizzes.get(quizID);
            System.out.println("\tFollowing can be done for a quiz.");
            System.out.println("\t[1] Add new question.");
            System.out.println("\t[2] Edit existing question.");
            System.out.println("\t[3] Delete existing question.");
            System.out.print("\tEnter your choice (enter 0 to exit): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    return;

                case 1:
                    quiz.addNewQuestion(sc);
                    break;

                case 2:
                    System.out.println("Following is the quiz currently:");
                    System.out.println(quiz);
                    System.out.print("\nWhich question do you want to edit (enter number): ");
                    int edit_ques = sc.nextInt();
                    sc.nextLine();
                    quiz.editQuestion(edit_ques, sc);
                    break;

                case 3:
                    System.out.println("\nFollowing is the quiz currently:");
                    System.out.println(quiz);
                    System.out.print("\nWhich question do you want to delete (enter number): ");
                    int del_ques = sc.nextInt();
                    sc.nextLine();
                    quiz.deleteQuestion(del_ques);
                    System.out.println("Question deleted successfully!\n");
                    break;

                default:
                    break;
            }
        } else {
            System.out.println("Instructor does not have this quiz.");
        }
    }

    @Override
    public String toString() {
        return this.instructorID + "," + this.name;
    }
}
