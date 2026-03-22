package com.example.PTUDJ2EE_bai5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        System.out.println("GET /login requested");
        return "login";
    }

    @GetMapping("/")
    public String home() {
        System.out.println("GET / requested - redirecting to /products");
        return "redirect:/products";
    }
}
