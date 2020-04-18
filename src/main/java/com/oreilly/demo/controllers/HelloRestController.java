package com.oreilly.demo.controllers;

import com.oreilly.demo.entities.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// a RestController is a Controller, does all a Controller can do, plus ResponseBody annotation
// so it can serialize and deserialize JSoN notation
@RestController
public class HelloRestController {

    @GetMapping("/rest")
    public Greeting greet(@RequestParam(required = false, defaultValue = "World") String name) {
        return new Greeting(String.format("Hello, %s!", name));
    }

}
