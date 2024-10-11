package com.github.cole55512.attendance.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class attendance_record {
    // ----- TABLE COLUMNS -----
    @EmbeddedId
    private attendance_record_key key; // Composite Primary Key (student_id, quiz_id)
    private double quiz_grade;
    private String ip_address;
    private String device_name;
    private Timestamp quiz_datetime;    // Timestamp == MySQL Datetime

    // ----- CONSTRUCTOR -----
    public attendance_record() {}

    // ----- GETTERS AND SETTERS -----
    // KEY
    public attendance_record_key get_key() {
        return key;
    }
    public void set_key(attendance_record_key key) {
        this.key = key;
    }
    // QUIZ_GRADE
    public double get_quiz_grade() {
        return quiz_grade;
    }
    public void set_quiz_grade(double quiz_grade) {
        this.quiz_grade = quiz_grade;
    }
    // IP_ADDRESS
    public String get_ip_address() {
        return ip_address;
    }
    public void set_ip_address(String ip_address) {
        this.ip_address = ip_address;
    }
    // DEVICE_NAME
    public String get_device_name() {
        return device_name;
    }
    public void set_device_name(String device_name) {
        this.device_name = device_name;
    }
    // QUIZ_DATETIME
    public Timestamp get_quiz_datetime() {
        return quiz_datetime;
    }
    public void set_quiz_datetime(Timestamp quiz_datetime) {
        this.quiz_datetime = quiz_datetime;
    }
}
