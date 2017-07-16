package com.dynamicheart.bookstore.store.admin.controller.customers;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.reference.language.LanguageService;
import com.dynamicheart.bookstore.store.admin.model.web.Menu;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by dynamicheart on 5/24/2017.
 */

@Controller
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Inject
    private CustomerService customerService;

    @Inject
    private LabelUtils messages;

    @Inject
    private LanguageService languageService;

    @Inject
    @Named("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value="/admin/customer/detail", method= RequestMethod.GET)
    public String displayCustomerEdit(@RequestParam("id") Long id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return displayCustomer(id, model, request, response);
    }

    @RequestMapping(value="/admin/customer/create", method= RequestMethod.GET)
    public String displayCustomerCreate(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return displayCustomer(null, model, request, response);
    }


    public String displayCustomer(Long id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //display menu
        this.setMenu(model, request);

        List<Language> languages = languageService.getLanguages();
        Customer customer = null;

        //if request.attribute contains id then get this customer from customerService
        if(id!=null && id!=0) {//edit mode

            //get from DB
            customer = customerService.getById(id);
            if(customer==null) {
                return "redirect:/admin/customers";
            }

        } else {
            customer = new Customer();
        }

        model.addAttribute("customer",customer);
        model.addAttribute("languages",languages);
        return "admin-customer";
    }

    @RequestMapping(value="/admin/customer/save", method=RequestMethod.POST)
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception{

        String email_regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        Pattern pattern = Pattern.compile(email_regEx);


        if(!StringUtils.isBlank(customer.getEmailAddress() ) ){
            java.util.regex.Matcher matcher = pattern.matcher(customer.getEmailAddress());

            if(!matcher.find()) {
                ObjectError error = new ObjectError("customerEmailAddress",messages.getMessage("Email.customer.EmailAddress", locale));
                result.addError(error);
            }
        }else{
            ObjectError error = new ObjectError("customerEmailAddress",messages.getMessage("NotEmpty.customer.EmailAddress", locale));
            result.addError(error);
        }


        Customer newCustomer = new Customer();

        if( customer.getId()!=null && customer.getId().longValue()>0 ) {
            newCustomer = customerService.getById( customer.getId() );

            if(newCustomer==null) {
                return "redirect:/admin/customers";
            }
        }

        Language language = languageService.getById(customer.getDefaultLanguage().getId());

        newCustomer.setEmailAddress(customer.getEmailAddress() );
        newCustomer.setGender(customer.getGender());
        newCustomer.setNick(customer.getNick());
        newCustomer.setPassword(customer.getPassword());
        newCustomer.setDefaultLanguage(language);

        customerService.saveOrUpdate(newCustomer);

        model.addAttribute("customer", newCustomer);
        model.addAttribute("success","success");

        return "admin-customer";
    }

    /**
     * List of customers
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/customers", method=RequestMethod.GET)
    public String displayCustomers(Model model, HttpServletRequest request) throws Exception {

        this.setMenu(model, request);

        return "admin-customers";
    }


    private void setMenu(Model model, HttpServletRequest request) throws Exception {

        //display menu
        Map<String,String> activeMenus = new HashMap<String,String>();
        activeMenus.put("Customer", "Customer");

        @SuppressWarnings("unchecked")
        Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");

        Menu currentMenu = (Menu)menus.get("Customer");
        model.addAttribute("activeMenus",activeMenus);
    }

}
