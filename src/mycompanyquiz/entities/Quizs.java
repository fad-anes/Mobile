package com.mycompany.entities;

public class Quizs {
    private int idQuiz;
    private String quizTitle;
    private String quizDescription;
    private int score;

    public Quizs() {
    }

    public Quizs(int idQuiz, String quizTitle, String quizDescription, int score) {
        this.idQuiz = idQuiz;
        this.quizTitle = quizTitle;
        this.quizDescription = quizDescription;
        this.score = score;
    }

    public Quizs(String quizTitle, String quizDescription, int score) {
        this.quizTitle = quizTitle;
        this.quizDescription = quizDescription;
        this.score = score;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
