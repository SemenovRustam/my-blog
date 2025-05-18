package com.semenov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // Вариант 1: Возврат строки напрямую
    @GetMapping("/hello")
    @ResponseBody
    public String helloWorld() {
        return "Hello World!";
    }

    // Вариант 2: Возврат JSP-страницы
    @GetMapping("/hello-jsp")
    public String helloJsp() {
        return "hello"; // Будет искать /WEB-INF/views/hello.jsp
    }
}