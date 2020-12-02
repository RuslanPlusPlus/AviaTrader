package com.example.spring_security_app.web;

import com.example.spring_security_app.model.User;
import com.example.spring_security_app.service.UserService;
import com.example.spring_security_app.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    /*@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }*/

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        String email = registrationDto.getEmail();
        List<User> users = this.userService.findAll();
        if (users != null){
            List<String> emails = new ArrayList<>();

            for (User user: users){
                emails.add(user.getEmail());
            }
            for (String line: emails){
                if (line.equals(email)){
                    return "redirect:/registration?error";
                }
            }
        }
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
