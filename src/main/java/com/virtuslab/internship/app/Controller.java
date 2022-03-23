package com.virtuslab.internship.app;

import com.virtuslab.internship.model.BasketRequestModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("/basket")
    public String index(@RequestBody BasketRequestModel basketModel) {
        return "Greetings from Spring Boot!";
    }

}