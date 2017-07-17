package com.dynamicheart.bookstore.store.admin.controller.statistics;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.statistics.Criteria;
import com.dynamicheart.bookstore.core.model.statistics.Statistics;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.statistics.StatisticsService;
import com.dynamicheart.bookstore.store.admin.model.web.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dynamicheart on 7/15/2017.
 */

@RequestMapping("/admin/statistics")
@Controller
public class BookStatisticsController {

    @Inject
    private StatisticsService statisticsService;
    
    @Inject
    private BookService bookService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String displayCriteria(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        setMenu(model, request);
        List<Book> books = bookService.list();

        Criteria criteria = new Criteria();
        model.addAttribute("books",books);
        model.addAttribute("criteria",criteria);

        return "statistics-book";
    }


    @RequestMapping(value = "/book", method = RequestMethod.POST)
    private String displayResult(@ModelAttribute("criteria")Criteria criteria, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        setMenu(model, request);

        List<Book> books = bookService.list();
        model.addAttribute("books",books);

        String bookIsbn = criteria.getBookIsbn();

        Statistics statistics = statisticsService.getBookStat(bookIsbn);

        model.addAttribute("criteria",criteria);
        model.addAttribute("statistics",statistics);
        return "statistics-book";
    }

    private void setMenu(Model model, HttpServletRequest request) throws Exception {

        //display menu
        Map<String,String> activeMenus = new HashMap<String,String>();
        activeMenus.put("Statistics", "Statistics");

        @SuppressWarnings("unchecked")
        Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");

        Menu currentMenu = (Menu)menus.get("Statistics");
        model.addAttribute("activeMenus",activeMenus);
    }
}
