package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("hello")
public class HelloViewController {
    @GetMapping("view")
    public String helloView(){
        // 뷰의 이름 반환
        return "hello";
    }
}

