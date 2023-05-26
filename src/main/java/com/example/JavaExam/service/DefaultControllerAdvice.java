package com.example.JavaExam.service;

import com.example.JavaExam.model.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler({NullPointerException.class})
    public @ResponseBody Response uncheckedError() {
        return new Response("Unknown error, please connect to support...");
    }

}
