package com.dynamicheart.bookstore.store.admin.controller.statistics;

import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.category.CategoryService;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.model.statistics.Criteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dynamicheart on 7/15/2017.
 */

@RequestMapping("/admin/statistics")
@Controller
public class StatisticsController {

    @Inject
    CustomerService customerService;

    @Inject
    CategoryService categoryService;

    @Inject
    BookService bookService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayStatistic(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{

        return "redirect:/admin/statistics/customer";
    }
}
