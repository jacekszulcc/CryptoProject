package pl.cryptoproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {


    @GetMapping("/hashtags")
    public String hashtags(){
        return "hashtags";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
