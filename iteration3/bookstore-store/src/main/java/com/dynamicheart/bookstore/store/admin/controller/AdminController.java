package com.dynamicheart.bookstore.store.admin.controller;

import com.dynamicheart.bookstore.store.admin.model.web.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dynamicheart on 5/27/2017.
 */

@Controller
public class AdminController {

    @RequestMapping(value = {"/admin/home","/admin/","/admin"}, method = RequestMethod.GET)
    public String displayDashboard(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //display menu
        this.setMenu(model, request);

        return "admin-dashboard";
    }

    private void setMenu(Model model, HttpServletRequest request) throws Exception {

        //display menu
        Map<String,String> activeMenus = new HashMap<String,String>();
        activeMenus.put("Home", "Home");

        @SuppressWarnings("unchecked")
        Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");

        Menu currentMenu = (Menu)menus.get("Home");
        model.addAttribute("activeMenus",activeMenus);
    }
}
