package com.dynamicheart.bookstore.store.admin.controller.statistics;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.category.Category;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.category.CategoryService;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.model.statistics.Criteria;
import com.dynamicheart.bookstore.store.admin.model.web.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dynamicheart on 7/16/2017.
 */

@Controller
public class StatisticsController {

    @Inject
    CustomerService customerService;

    @Inject
    CategoryService categoryService;

    @Inject
    BookService bookService;

    @RequestMapping(value = "/admin/statistics", method = RequestMethod.GET)
    public String displayStatistic(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        setMenu(model, request);

        List<Customer> customers = customerService.list();
        List<Category> categories = categoryService.list();
        List<Book> books = bookService.list();


        Criteria criteria = new Criteria();
        model.addAttribute("customers",customers);
        model.addAttribute("categories",categories);
        model.addAttribute("books",books);
        model.addAttribute("criteria",criteria);

        return "admin-statistics";
    }

    private void setMenu(Model model, HttpServletRequest request) throws Exception {

        //display menu
        Map<String,String> activeMenus = new HashMap<String,String>();
        activeMenus.put("Statistics", "StatisticsService");

        @SuppressWarnings("unchecked")
        Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");

        Menu currentMenu = (Menu)menus.get("StatisticsService");
        model.addAttribute("activeMenus",activeMenus);
    }
}
