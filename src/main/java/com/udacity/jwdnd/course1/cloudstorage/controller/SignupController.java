package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.forms.UserForm;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private UsersMapperService usersMapperService;
    public SignupController(UsersMapperService usersMapperService){
        this.usersMapperService = usersMapperService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String getSignup(@ModelAttribute(name = "userForm") UserForm userForm, Model model){
        return "signup";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String postSignup(@ModelAttribute(name = "userForm") UserForm userForm, Model model){
        Users user = new Users();
        user.setFirstname(userForm.getFirstname());
        user.setLastname(userForm.getLastname());
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        if (usersMapperService.isUsernameUnique(userForm.getUsername()) == false){
            model.addAttribute("errmessage", "test error message");
            return "signup";
        }
        System.out.println("usersMapperService: " + usersMapperService);
        Integer userInt = usersMapperService.saveUser(user);
        System.out.println("New User: " + userInt + " username:" + user.getUsername());
        return "login";
    }
}
