package com.dynamicheart.bookstore.store.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dynamicheart on 5/27/2017.
 */

@Controller
public class AdminController {

    @RequestMapping(value = {"/admin/","/admin"}, method = RequestMethod.GET)
    public String displayDashboard(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        return "admin-dashboard";
    }
}
