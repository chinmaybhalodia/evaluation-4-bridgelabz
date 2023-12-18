package com.example;

import java.util.Scanner;

import com.exceptions.*;

public class Main {
    public static void main(String[] args)
            throws InstructorNotFoundException, QuizNotFoundException, PlayerNotFoundException {
        Scanner sc = new Scanner(System.in);
        QuizConsole.startQuizConsole(sc);
    }
}