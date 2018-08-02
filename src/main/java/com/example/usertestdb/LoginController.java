package com.example.usertestdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String username,
                               @RequestParam String password) {

        loginRepository.addUser(name, email, username, password);


        return "register";
    }

    @GetMapping("/allUsers")
    public ModelAndView getUsers() {
        List <Users> allUsers = new ArrayList<>();

        allUsers = loginRepository.getUsers();


        return new ModelAndView("listusers").addObject("allUsers", allUsers);
    }

}
