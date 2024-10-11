package com.github.cole55512.attendance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class admin_info {
    // ----- TABLE COLUMNS -----
    @Id
    private int admin_id;
    private String admin_first_name;
    private String admin_last_name;
    private String admin_email;
    private String admin_password;

    // ----- CONSTRUCTOR -----
    public admin_info() {}

    // ----- GETTERS AND SETTERS -----
    // ADMIN_ID
    public int get_admin_id() {
        return admin_id;
    }
    public void set_admin_id(int admin_id) {
        this.admin_id = admin_id;
    }
    // ADMIN_FIRST_NAME
    public String get_admin_first_name() {
        return admin_first_name;
    }
    public void set_admin_first_name(String admin_first_name) {
        this.admin_first_name = admin_first_name;
    }
    // ADMIN_LAST_NAME
    public String get_admin_last_name() {
        return admin_last_name;
    }
    public void set_admin_last_name(String admin_last_name) {
        this.admin_last_name = admin_last_name;
    }
    // ADMIN_EMAIL
    public String get_admin_email() {
        return admin_email;
    }
    public void set_admin_email(String admin_email) {
        this.admin_email = admin_email;
    }
    // ADMIN_PASSWORD
    public String get_admin_password() {
        return admin_password;
    }
    public void set_admin_password(String admin_password) {
        this.admin_password = admin_password;
    }
}
