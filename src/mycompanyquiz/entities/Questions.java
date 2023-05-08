/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author Ahmed
 */
public class Questions {
    private int idQuestion;
    private String questionText;
    private int idQuiz;

    public Questions() {
    }

    public Questions(int idQuestion, String questionText, int idQuiz) {
        this.idQuestion = idQuestion;
        this.questionText = questionText;
        this.idQuiz = idQuiz;
    }

    public Questions(String questionText) {
        this.questionText = questionText;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }
  
    
}
