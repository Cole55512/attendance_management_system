package com.github.cole55512.attendance.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class student_info {
    // ----- TABLE COLUMNS -----
    @EmbeddedId
    private student_info_key key;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String student_email;
    private String student_career;

    // ----- CONSTRUCTOR -----
    public student_info() {}

    // ----- GETTERS AND SETTERS -----
    // KEY
    public student_info_key get_key() {
        return key;
    }
    public void set_key(student_info_key key) {
        this.key = key;
    }
    // FIRST_NAME
    public String get_first_name() {
        return first_name;
    }
    public void set_first_name(String first_name) {
        this.first_name = first_name;
    }
    // MIDDLE_NAME
    public String get_middle_name() {
        return middle_name;
    }
    public void set_middle_name(String middle_name) {
        this.middle_name = middle_name;
    }
    // LAST_NAME
    public String get_last_name() {
        return last_name;
    }
    public void set_last_name(String last_name) {
        this.last_name = last_name;
    }
    // STUDENT_EMAIL
    public String get_student_email() {
        return student_email;
    }
    public void set_student_email(String student_email) {
        this.student_email = student_email;
    }
    // STUDENT_CAREER
    public String get_student_career() {
        return student_career;
    }
    public void set_student_career(String student_career) {
        this.student_career = student_career;
    }
}
