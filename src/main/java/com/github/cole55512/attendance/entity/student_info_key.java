package com.github.cole55512.attendance.entity;

import java.io.Serializable;
import java.util.Objects;

public class student_info_key implements Serializable {
    // ----- COMPOSITE KEY COLUMNS -----
    private String student_id;
    private int class_id;

    // ----- CONSTRUCTOR -----
    public student_info_key() {}

    // ----- GETTERS AND SETTERS -----
    // STUDENT_ID
    public String get_student_id() {
        return student_id;
    }
    public void set_student_id(String student_id) {
        this.student_id = student_id;
    }
    // CLASS_ID
    public int get_class_id() {
        return class_id;
    }
    public void set_class_id(int class_id) {
        this.class_id = class_id;
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
        student_info_key that = (student_info_key) o;
        return student_id.equals(that.student_id) && class_id == that.class_id;
    }
    // HASH METHOD
    @Override
    public int hashCode() {
        return Objects.hash(student_id, class_id);
    }
}
