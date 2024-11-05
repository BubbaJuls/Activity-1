package com.act1.controllers;

import com.act1.models.User;
import com.act1.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "RegisterForm";
    }

    @PostMapping("/registerProcess")
    public String registerUser(@ModelAttribute User user, Model model) {
        String message = userService.register(user);
        model.addAttribute("message", message);
        return "RegisterForm";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "LoginForm";
    }

    @PostMapping("/loginProcess")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            return "Menu";
        } else {
            model.addAttribute("message", "Invalid username or password");
            return "LoginForm";
        }
    }

    @GetMapping("/menu")
    public String showMenu(HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", session.getAttribute("username"));
        return "Menu";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
