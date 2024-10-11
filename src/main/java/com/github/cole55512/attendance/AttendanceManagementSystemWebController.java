package com.github.cole55512.attendance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttendanceManagementSystemWebController {
    @GetMapping("/attendance-login")
    public String home() {
        return "AttendanceLogin";
    }
    @GetMapping("/admin-login")
    public String quiz() {
        return "AdminLogin";
    }
}
