package com.github.cole55512.attendance.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class quiz_info {
    // ----- TABLE COLUMNS -----
    @Id
    private int quiz_id;    // Primary Key
    @ManyToOne
    @JoinColumn(name = "quiz_class_id", referencedColumnName = "class_id")
    private class_info class_id;    // Foreign Key
    private Date quiz_date;
    private String quiz_password;
    // Foreign Key Uses
    @OneToMany(mappedBy = "quiz_id")    // "quiz_id" == "private quiz_info quiz_id" in 'quiz_questions'
    private List<quiz_questions> quiz_question_list;    // List of all questions for a single quiz

    // ----- CONSTRUCTOR -----
    public quiz_info() {}

    // ----- GETTERS AND SETTERS -----
    // QUIZ_ID
    public int get_quiz_id() {
        return quiz_id;
    }
    public void set_quiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }
    // CLASS_ID
    public class_info get_class() {
        return class_id;
    }
    public void set_class_id(class_info class_id) {
        this.class_id = class_id;
    }
    // QUIZ_DATE
    public String get_quiz_date() {
        LocalDate date = quiz_date.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return date.format(formatter);
    }
    public void set_quiz_date(Date quiz_date) {
        this.quiz_date = quiz_date;
    }
    // QUIZ_PASSWORD
    public String get_quiz_password() {
        return quiz_password;
    }
    public void set_quiz_password(String quiz_password) {
        this.quiz_password = quiz_password;
    }
}
