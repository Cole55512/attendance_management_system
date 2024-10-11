// ####################################################################################################################
// File: AttendanceQuizLoginController.java
//
// Author: Nicholas Krauter
// Date: 09/21/24
// Description: The 'AttendanceQuizLoginController' class handles the authentication process for students attempting to
// log in to their class's attendance quiz. This class compares the inputted student identification with the
// 'student_id' stored in the 'student_info' database table. This class also validates the quiz password with data
// the stored in the 'quiz_info' database table and appropriately redirects the user based on the outcome of the
// validation. If the login is successful, the user will be redirected to 'attendance-quiz'; otherwise, they will stay
// on 'attendance'.
//
// ####################################################################################################################
package com.github.cole55512.attendance;

import com.github.cole55512.attendance.entity.class_info;
import com.github.cole55512.attendance.entity.quiz_info;
import com.github.cole55512.attendance.entity.student_info;
import com.github.cole55512.attendance.repository.student_info_repo;
import com.github.cole55512.attendance.repository.quiz_info_repo;
import com.github.cole55512.attendance.repository.class_info_repo;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
public class AttendanceLoginController {

    // ########## REPOSITORIES ##########
    private final student_info_repo student_info_repo;
    private final quiz_info_repo quiz_info_repo;

    // ########## CONTROLLER CONSTRUCTOR ##########
    public AttendanceLoginController(student_info_repo student_info_repo, quiz_info_repo quiz_info_repo, class_info_repo class_info_repo) {
        this.student_info_repo = student_info_repo; // Initialize 'student_info_repo'
        this.quiz_info_repo = quiz_info_repo;   // Initialize 'quiz_info_repo'
    }

    // ########## VALIDATE LOGIN ##########
    // - Function Purpose: This function handles the validation of student_id and quiz password for 'attendance-login'
    //  - 'student_id': user inputted id (compared to 'student_info.student_id')
    //  - 'quiz_password': user inputted quiz password (compared to 'quiz_info.qui_password')
    //  - 'model': interface for passing data between web pages
    //  - 'redirectedAttributes': interface for passing data through redirects (errors, maintaining user input)
    // - RETURN STRING: IF login fails -> redirect to 'attendance-login' ELSE redirect to 'attendance-quiz'
    @PostMapping("/attendance-quiz")
    public String validateLogin(@RequestParam("student_id") String student_id,
                                @RequestParam("quiz_password") String quiz_password,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) {
        // ########## WEBSITE REDIRECT STRINGS ##########
        String goto_login_page = "redirect:/attendance-login";
        String goto_attendance_quiz = "redirect:/attendance-quiz";

        // ---------- STEP 1: VALIDATE STUDENT_ID ----------
        // Step 1.1: Check that 'student_id' is not empty
        if (student_id == null || student_id.trim().isEmpty()) {
            // IF 'student_id' is empty -> display empty id error
            redirectAttributes.addFlashAttribute("error", "Please Enter your NetID");
            // Maintain the users inputted id through redirect
            redirectAttributes.addFlashAttribute("student_id", student_id);
            return goto_login_page;  // Return to 'attendance-login'
        }
        // Step 1.2: Check if the student exists in the database using 'student_id'
        // 'students' is a list of rows from the 'student_info' database table that correspond to the 'student_id'
        List<student_info> students = student_info_repo.findStudent(student_id);
        if (students.isEmpty()) {
            // IF 'student_id' does not exist in database -> display invalid id error
            redirectAttributes.addFlashAttribute("error", "Please Enter a Valid NetID");
            // Maintain the users inputted id through redirect
            redirectAttributes.addFlashAttribute("student_id", student_id);
            return goto_login_page;    // Return to 'attendance-login'
        }
        // ---------- STEP 2: CHECK FOR ACTIVE QUIZ ----------
        // Step 2: Check for active quiz in student's class
        LocalDate currentDate = LocalDate.now();    // Gets current date of attempted login
        LocalTime currentTime = LocalTime.now();    // Gets current time of attempted login
        quiz_info activeQuiz = null;
        student_info activeQuizStudent = null;

        for (student_info student : students) {
            // Check for active quiz
            Optional<quiz_info> quiz = quiz_info_repo.findQuiz(student.get_key().get_class_id(),currentDate,currentTime);
            if (quiz.isPresent()) {
                activeQuiz = quiz.get();
                activeQuizStudent = student;
                break;
            }
        }
        // If no active quizzes for any of the student's classes
        if (activeQuiz == null) {
            redirectAttributes.addFlashAttribute("error", "No Quiz Found");
            // Maintain the users inputted id through redirect
            redirectAttributes.addFlashAttribute("student_id", student_id);
            return goto_login_page; // Return to 'attendance-login'
        }

        // ---------- STEP 3: VALIDATE QUIZ_PASSWORD ----------
        // 3.1: Check that 'quiz_password' is not empty
        if (quiz_password == null || quiz_password.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Please Enter a Password");
            // Maintain the users inputted id through redirect
            redirectAttributes.addFlashAttribute("student_id", student_id);
            return goto_login_page; // Return to 'attendance-login'
        }
        // 3.2: Check that 'quiz_password' exists in the database
        if (!activeQuiz.get_quiz_password().equals(quiz_password)) {
            redirectAttributes.addFlashAttribute("error", "Incorrect Password");
            // Maintain the users inputted id through redirect
            redirectAttributes.addFlashAttribute("student_id", student_id);
            return goto_login_page; // Return to 'attendance-login'
        }
        // ---------- STEP 4: LOGIN SUCCESSFUL ----------
        session.setAttribute("quiz", activeQuiz);
        session.setAttribute("student", activeQuizStudent);
        // Get class_info
        class_info studentClass = activeQuiz.get_class();
        session.setAttribute("class", studentClass);
        return goto_attendance_quiz;    // GOTO 'attendance-quiz'
    }
}
