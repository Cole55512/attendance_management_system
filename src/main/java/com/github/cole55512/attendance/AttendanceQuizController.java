package com.github.cole55512.attendance;

import com.github.cole55512.attendance.entity.*;
import com.github.cole55512.attendance.repository.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("quiz")
public class AttendanceQuizController {
    private final quiz_questions_repo quiz_questions_repo;
    private final attendance_record_repo attendance_record_repo;
    public AttendanceQuizController(quiz_questions_repo quiz_questions_repo, attendance_record_repo attendance_record_repo) {
        this.quiz_questions_repo = quiz_questions_repo;
        this.attendance_record_repo = attendance_record_repo;
    }

    @GetMapping("/attendance-quiz")
    public String showQuiz(Model model, HttpSession session) {
        student_info student = (student_info) session.getAttribute("student");
        class_info student_class = (class_info) session.getAttribute("class");
        quiz_info quiz = (quiz_info) session.getAttribute("quiz");
        if (student != null) {
            model.addAttribute("student", student);
        }
        if (student_class != null) {
            model.addAttribute("class", student_class);
        }
        if (quiz != null) {
            model.addAttribute("quiz", quiz);
            // Get quiz questions
            List <quiz_questions> quizQuestions = quiz_questions_repo.findQuizQuestions(quiz.get_quiz_id());
            model.addAttribute("quiz_questions", quizQuestions);
        }
        return "AttendanceQuiz";
    }

    @PostMapping("/quiz-submit")
    public String submitQuiz(@RequestParam Map<String, String> formData, HttpSession session, HttpServletRequest request) {
        student_info student = (student_info) session.getAttribute("student");
        quiz_info quiz = (quiz_info) session.getAttribute("quiz");

        // Calculate Grade
        List<quiz_questions> quizQuestions = quiz_questions_repo.findQuizQuestions(quiz.get_quiz_id());
        double correctAnswers = 0;
        for (quiz_questions question : quizQuestions) {
            String selectedAnswer = formData.get("question_" + question.get_question_id());
            if (selectedAnswer != null && selectedAnswer.equals(question.get_correct_answer())) {
                correctAnswers++;
            }
        }
        double quizGrade = (correctAnswers / quizQuestions.size()) * 100;

        // Create and Save 'attendance_record'
        attendance_record record = new attendance_record();
        // Set Key
        attendance_record_key key = new attendance_record_key();
        key.set_student_id(student.get_key().get_student_id());
        key.set_quiz_id(quiz.get_quiz_id());
        record.set_key(key);
        // Set Quiz Grade
        record.set_quiz_grade(quizGrade);
        // Set Quiz DateTime
        record.set_quiz_datetime(Timestamp.from(Instant.now()));    // Time of submission
        // Set IP Address & User-Agent (Device Name)
        record.set_ip_address(getClientIP(request));    // Get IP
        record.set_device_name(request.getHeader("User-Agent"));    // Get User-Agent (Device Name)
        // Save Record
        attendance_record_repo.save(record);

        return "QuizSubmit";
    }

    private String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
