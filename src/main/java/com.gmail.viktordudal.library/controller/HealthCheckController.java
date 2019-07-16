package com.gmail.viktordudal.library.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@ResponseStatus(HttpStatus.OK)
public class HealthCheckController {

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "OK";
    }
}
