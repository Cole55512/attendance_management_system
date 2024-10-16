package com.github.cole55512.attendance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("admin")
public class AdminPortalController {
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
}
