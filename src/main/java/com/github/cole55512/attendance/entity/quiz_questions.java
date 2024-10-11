package com.github.cole55512.attendance.entity;

import jakarta.persistence.*;

@Entity
public class quiz_questions {
    // ----- TABLE COLUMNS -----
    @Id
    private int question_id;    // Primary Key

    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "quiz_id")
    private quiz_info quiz_id;  // Foreign Key

    private String quiz_question;

    @Lob
    private byte[] question_img;

    private String correct_answer;
    private String option_1;
    private String option_2;
    private String option_3;
    private String option_4;

    // ----- CONSTRUCTOR -----
    public quiz_questions() {}

    // ----- GETTERS AND SETTERS -----
    // QUESTION_ID
    public int get_question_id() {
        return question_id;
    }
    public void set_question_id(int question_id) {
        this.question_id = question_id;
    }
    // QUIZ_ID
    public quiz_info get_quiz_id() {
        return quiz_id;
    }
    public void set_quiz_id(quiz_info quiz_id) {
        this.quiz_id = quiz_id;
    }
    // QUIZ_QUESTION
    public String get_quiz_question() {
        return quiz_question;
    }
    public void set_quiz_question(String quiz_question) {
        this.quiz_question = quiz_question;
    }
    // QUESTION_IMG
    public byte[] get_question_img() {
        return question_img;
    }
    public void set_question_img(byte[] question_img) {
        this.question_img = question_img;
    }
    // CORRECT_ANSWER
    public String get_correct_answer() {
        return correct_answer;
    }
    public void set_correct_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }
    // OPTION_1
    public String get_option_1() {
        return option_1;
    }
    public void set_option_1(String option_1) {
        this.option_1 = option_1;
    }
    // OPTION_2
    public String get_option_2() {
        return option_2;
    }
    public void set_option_2(String option_2) {
        this.option_2 = option_2;
    }
    // OPTION_3
    public String get_option_3() {
        return option_3;
    }
    public void set_option_3(String option_3) {
        this.option_3 = option_3;
    }
    // OPTION_4
    public String get_option_4() {
        return option_4;
    }
    public void set_option_4(String option_4) {
        this.option_4 = option_4;
    }
}
