package com.github.cole55512.attendance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("admin")
public class AdminPortalController {
    @GetMapping("/admin-portal")
    public String goto_admin_home() {
        return "AdminPortalHome";
    }
    @GetMapping("/admin-portal-class-management")
    public String goto_class_mgmt() {
        return "ClassManagement";
    }
    @GetMapping("/admin-portal-quiz-management")
    public String goto_quiz_mgmt() {
        return "QuizManagement";
    }
    @GetMapping("/admin-portal-password-management")
    public String goto_password_mgmt() {
        return "PasswordManagement";
    }
    @GetMapping("/admin-portal-attendance-report")
    public String goto_attendance_report() {
        return "AttendanceReport";
    }
    @GetMapping("/admin-portal-help")
    public String goto_help() {
        return "AdminPortalHelp";
    }
}
