package com.gcu.workspacecst339.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.workspacecst339.business.UserService;

@Controller
public class AuthController {

    private final UserService users;

    public AuthController(UserService users) {
        this.users = users;
    }

    @GetMapping("/login")
    public String login() {
        // Spring Security handles POST /login
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String confirmPassword,
                             @RequestParam(required = false) String firstName,
                             @RequestParam(required = false) String lastName,
                             @RequestParam(required = false) String email,
                             Model model) {

        if (username == null || username.isBlank()) {
            model.addAttribute("registerError", "Username is required.");
            return "register";
        }
        if (password == null || password.isBlank()) {
            model.addAttribute("registerError", "Password is required.");
            return "register";
        }
        if (!password.equals(confirmPassword)) {
            model.addAttribute("registerError", "Passwords do not match.");
            return "register";
        }
        if (users.usernameTaken(username)) {
            model.addAttribute("registerError", "That username is taken.");
            return "register";
        }

        users.register(username, password, firstName, lastName, email);
        return "redirect:/login?registered";
    }
}