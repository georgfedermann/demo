package com.oreilly.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// this is an annotation that will be detected by Spring via component scan in all packages
// below the root package com.oreilly.demo.
@Controller("myHelloController")
public class HelloController {

    // this annotation @GetMapping connects this method to the outside world by providing a URL
    // @RequestParam connects the parameter to the outside world by mapping it to a request parameter
    @GetMapping("/hello")
    public String sayHello(@RequestParam(required = false, defaultValue = "World") String name,
                           Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

}
