// ####################################################################################################################
// File: AdminLoginController.java
//
// Author: Nicholas Krauter
// Date: 09/21/24
// Description: The 'AdminLoginController' handles the authentication process for administrators attempting to log in
// to the admin portal. It validates the provided email and password against the stored credentials in the 'admin_info'
// database table and appropriately redirects the user based on the outcome of the validation. If the login is
// successful, the user will be redirected to 'admin-homepage'; otherwise, they will stay on 'admin-portal'.
//
// ####################################################################################################################
package com.github.cole55512.attendance;

import com.github.cole55512.attendance.entity.admin_info;
import com.github.cole55512.attendance.repository.admin_info_repo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminPortalLoginController {
    // ########## REPOSITORIES ##########
    private final admin_info_repo admin_info_repo;

    // ########## CONTROLLER CONSTRUCTOR ##########
    public AdminPortalLoginController(admin_info_repo admin_info_repo) {
        this.admin_info_repo = admin_info_repo; // Initialize 'admin_info_repo'
    }

    // ########## VALIDATE LOGIN ##########
    // - Function Purpose: This function handles email and password validation for the 'admin-portal' login
    //  - 'admin_email': user inputted email (compared to admin_info.admin_email)
    //  - 'admin_password': user inputted password (compared to admin_info.admin_password)
    //  - 'model': interface for passing data between web pages
    //  - 'redirectedAttributes': interface for passing data through redirects (errors, maintaining user input)
    // - RETURN STRING: IF login fails -> redirect to 'admin-portal' ELSE redirect to 'admin-homepage'
    @PostMapping("/admin-portal")
    public String validateLogin(@RequestParam("admin_email") String admin_email,
                                     @RequestParam("admin_password") String admin_password,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {
        // ########## WEBSITE REDIRECT STRINGS ##########
        String login_page = "redirect:/admin-login";
        String admin_portal = "AdminPortal";

        // ---------- STEP 1: VALIDATE ADMIN_EMAIL ----------
        // 1.1: Check that 'admin_email' is not empty
        if (admin_email == null || admin_email.trim().isEmpty()) {
            // IF 'admin_email' is empty -> display empty email error
            redirectAttributes.addFlashAttribute("error", "Please Enter an Email");
            // Maintain the users inputted email through redirect
            redirectAttributes.addFlashAttribute("admin_email", admin_email);
            return login_page;    // Redirect back to 'admin-portal'
        }
        // 1.2: Check if the 'admin_email' exists in database
        if (admin_info_repo.adminExists(admin_email) == 0) {
            // IF 'admin_email' does not exist in database -> display invalid email error
            redirectAttributes.addFlashAttribute("error", "Please Enter a Valid Email");
            // Maintain the users inputted email through redirect
            redirectAttributes.addFlashAttribute("admin_email", admin_email);
            return login_page;    // Redirect back to 'admin-portal'
        }
        // ---------- STEP 2: VALIDATE ADMIN_PASSWORD ----------
        // 2.1: Check that 'admin_password' is not empty
        if (admin_password == null || admin_password.trim().isEmpty()) {
            // IF 'admin_password' is empty -> display empty password error
            redirectAttributes.addFlashAttribute("error", "Please Enter a Password");
            // Maintain the users inputted email through redirect
            redirectAttributes.addFlashAttribute("admin_email", admin_email);
            return login_page;    // Redirect back to 'admin-portal'
        }
        // 2.2: Check 'admin_password' exists in database
        admin_info admin = admin_info_repo.findAdmin(admin_email);  // Get 'admin' entity from 'admin_email'
        if (!admin.get_admin_password().equals(admin_password)) {
            // IF 'admin_email' does not exist in database -> display incorrect password error
            redirectAttributes.addFlashAttribute("error", "Incorrect Password");
            // Maintain the users inputted email through redirect
            redirectAttributes.addFlashAttribute("admin_email", admin_email);
            return login_page;    // Redirect back to 'admin-portal'
        }
        // ---------- STEP 3: LOGIN SUCCESSFUL ----------
        model.addAttribute("admin", admin); // Add 'admin' entity to model (will be used in 'admin-homepage')
        return admin_portal;  // Redirect to 'admin-portal'
    }
}
