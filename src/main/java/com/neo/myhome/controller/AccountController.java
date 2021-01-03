package com.neo.myhome.controller;


import com.neo.myhome.model.User;
import com.neo.myhome.repository.UserRepository;
import com.neo.myhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String idCheck(Model model, String username){
        User checkUser = userRepository.findByUsername(username);

        if(checkUser != null) {
            return "Y";
        }else{
            return "N";
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
    @GetMapping("/findTeresia")
    @ResponseBody
    public String findTeresia(String id){
        User checkUser = userRepository.findByUsername(id);
        if(checkUser != null && checkUser.getName().equals("이소영")){
            return "Y";    
        }
        return "N";
    }
}
