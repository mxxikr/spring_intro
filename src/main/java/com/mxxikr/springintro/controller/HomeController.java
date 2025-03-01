package com.mxxikr.springintro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // localhost:8080/ 요청이 오면 이 메서드 실행
    public String home() {
        return "home";
    }
}
