package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wody8674@gmail.com on 2020/02/06.
 */
@RestController
public class CommonController {

    @RequestMapping("/")
    public String home() {
        return "Happy Coding!";
    }

}
