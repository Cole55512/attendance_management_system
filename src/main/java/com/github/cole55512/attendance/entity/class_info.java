package com.github.cole55512.attendance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class class_info {
    // ----- TABLE COLUMNS -----
    @Id
    private int class_id;   // Primary Key
    private String class_name;
    private Date class_start_date;
    private Date class_end_date;
    private Time class_start_time;
    private Time class_end_time;
    private Time quiz_start_time;
    private Time quiz_end_time;
    private String class_days;
    // Foreign Key Uses
    @OneToMany(mappedBy = "class_id")   // "class_id" == "private class_info class_id" in 'quiz_info'
    private List<quiz_info> class_quizzes;  // List of all quizzes for a single class

    // ----- CONSTRUCTOR -----
    public class_info() {}

    // ----- GETTERS AND SETTERS -----
    // CLASS_ID
    public int get_class_id() {
        return class_id;
    }
    public void set_class_id(int class_id) {
        this.class_id = class_id;
    }
    // CLASS_NAME
    public String get_class_name() {
        return class_name;
    }
    public void set_class_name(String class_name) {
        this.class_name = class_name;
    }
    // CLASS_START_DATE
    public Date get_class_start_date() {
        return class_start_date;
    }
    public void set_class_start_date(Date class_start_date) {
        this.class_start_date = class_start_date;
    }
    // CLASS_END_DATE
    public Date get_class_end_date() {
        return class_end_date;
    }
    public void set_class_end_date(Date class_end_date) {
        this.class_end_date = class_end_date;
    }
    // CLASS_START_TIME
    public Time get_class_start_time() {
        return class_start_time;
    }
    public void set_class_start_time(Time class_start_time) {
        this.class_start_time = class_start_time;
    }
    // CLASS_END_TIME
    public Time get_class_end_time() {
        return class_end_time;
    }
    public void set_class_end_time(Time class_end_time) {
        this.class_end_time = class_end_time;
    }
    // QUIZ_START_TIME
    public Time get_quiz_start_time() {
        return quiz_start_time;
    }
    public void set_quiz_start_time(Time quiz_start_time) {
        this.quiz_start_time = quiz_start_time;
    }
    // QUIZ_END_TIME
    public String get_quiz_end_time() {
        LocalTime time24 = quiz_end_time.toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String time12 = time24.format(formatter).toLowerCase();
        time12 = time12.replace("am", "AM").replace("pm", "PM");
        return time12;
    }
    public void set_quiz_end_time(Time quiz_end_time) {
        this.quiz_end_time = quiz_end_time;
    }
    // CLASS_DAYS
    public String get_class_days() {
        return class_days;
    }
    public void set_class_days(String class_days) {
        this.class_days = class_days;
    }
    // CLASS_QUIZZES
    public List<quiz_info> get_class_quizzes() {
        return class_quizzes;
    }
    public void set_class_quizzes(List<quiz_info> class_quizzes) {
        this.class_quizzes = class_quizzes;
    }
}
