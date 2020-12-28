package com.neo.myhome.controller;


import com.neo.myhome.model.User;
import com.neo.myhome.repository.UserRepository;
import com.neo.myhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }


    @GetMapping("/idCheck")
    public Map idCheck(String username){
        User checkUser = userRepository.findByUsername(username);
        Map result = new HashMap<String,String>();
        if(checkUser != null) {
            result.put("data","Y");
            return result;
        }else{
            result.put("data","N");
            return result;
        }
    }
    @GetMapping("/register")
    public String register(){
        return "account/register";
    }


    @PostMapping("/register")
    public String register(User user){


            userService.save(user);
            return "redirect:/";

    }
}
