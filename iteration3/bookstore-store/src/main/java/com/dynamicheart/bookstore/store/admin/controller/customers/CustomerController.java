package com.dynamicheart.bookstore.store.admin.controller.customers;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
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

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
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
    @Named("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    /**
     * Customer details
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    @RequestMapping(value="/admin/customers/customer", method= RequestMethod.GET)
    public String displayCustomer(Long id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {


        Customer customer = null;

        //if request.attribute contains id then get this customer from customerService
        if(id!=null && id!=0) {//edit mode

            //get from DB
            customer = customerService.getById(id);
            if(customer==null) {
                return "redirect:/admin/customers/list.html";
            }

        } else {
            customer = new Customer();
        }

        return "admin-customer";
    }

    @RequestMapping(value="/admin/customers/save", method=RequestMethod.POST)
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model, HttpServletRequest request) throws Exception{

        String email_regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        Pattern pattern = Pattern.compile(email_regEx);


        if(!StringUtils.isBlank(customer.getEmailAddress() ) ){
            java.util.regex.Matcher matcher = pattern.matcher(customer.getEmailAddress());

            if(!matcher.find()) {
                ObjectError error = new ObjectError("customerEmailAddress",messages.getMessage("Email.customer.EmailAddress"));
                result.addError(error);
            }
        }else{
            ObjectError error = new ObjectError("customerEmailAddress",messages.getMessage("NotEmpty.customer.EmailAddress"));
            result.addError(error);
        }


        Customer newCustomer = new Customer();

        if( customer.getId()!=null && customer.getId().longValue()>0 ) {
            newCustomer = customerService.getById( customer.getId() );

            if(newCustomer==null) {
                return "redirect:/admin/customers/list";
            }
        }

        newCustomer.setEmailAddress(customer.getEmailAddress() );

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
    @RequestMapping(value="/admin/customers/list", method=RequestMethod.GET)
    public String displayCustomers(Model model,HttpServletRequest request) throws Exception {

        model.addAttribute("url","\"/api/admin/customers\"");

        List<String> columns = new LinkedList<>();
        columns.add("id");
        columns.add("gender");
        columns.add("dateOfBirth");
        columns.add("emailAddress");
        columns.add("nick");
        model.addAttribute("columns", columns);

        return "admin-customers";
    }

}
