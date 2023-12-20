package com.example;

public class Question {
    String question;
    QuestionType questionType;
    String[] options;
    private int correct_option;
    private String correct_ans;
    int timerDuration;

    // for question type MCQ
    public Question(String question, String option1, String option2, String option3, int correct_option,
            int timerDuration) {
        this.question = question;
        this.options = new String[3];
        options[0] = option1;
        options[1] = option2;
        options[2] = option3;
        this.correct_option = correct_option;
        this.timerDuration = timerDuration;
        this.questionType = QuestionType.MCQ;
    }

    // for question type open ended
    public Question(String question, String correct_ans, int timerDuration) {
        this.question = question;
        this.correct_ans = correct_ans;
        this.timerDuration = timerDuration;
        this.questionType = QuestionType.OPEN_ENDED;
    }

    public boolean checkAnswer(int choice) {
        return choice == this.correct_option;
    }

    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase(this.correct_ans);
    }

    public int getAnswerMCQ() {
        return this.correct_option;
    }

    public String getAnswerOpen() {
        return this.correct_ans;
    }

    public QuestionType getQuestionType() {
        return this.questionType;
    }

    @Override
    public String toString() {
        if (this.questionType.equals(QuestionType.MCQ)) {
            return this.question
                    + "\n\t1) " + this.options[0]
                    + "\n\t2) " + this.options[1]
                    + "\n\t3) " + this.options[2];
        } else {
            return this.question;
        }
    }
}
