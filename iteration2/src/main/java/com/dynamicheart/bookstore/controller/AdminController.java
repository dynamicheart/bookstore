package com.dynamicheart.bookstore.controller;

import com.dynamicheart.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by dynamicheart on 4/19/2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = GET)
    public String admin(Model model){
        model.addAttribute("UserList",this.userService.findUsers());
        return "admin/management";
    }
}
