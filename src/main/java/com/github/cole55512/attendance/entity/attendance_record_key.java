package com.github.cole55512.attendance.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class attendance_record_key implements Serializable{
    // ----- COMPOSITE KEY COLUMNS -----
    private String student_id;
    private int quiz_id;

    // ----- CONSTRUCTOR -----
    public attendance_record_key() {}

    // ----- GETTERS AND SETTERS -----
    // STUDENT_ID
    public String get_student_id() {
        return student_id;
    }
    public void set_student_id(String student_id) {
        this.student_id = student_id;
    }
    // QUIZ_ID
    public int get_quiz_id() {
        return quiz_id;
    }
    public void set_quiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    // ----- OVERRIDE METHODS -----
    // EQUALS METHOD
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        attendance_record_key that = (attendance_record_key) o;
        return student_id.equals(that.student_id) && quiz_id == that.quiz_id;
    }
    // HASH METHOD
    @Override
    public int hashCode() {
        return Objects.hash(student_id, quiz_id);
    }
}
