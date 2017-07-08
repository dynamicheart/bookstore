package com.dynamicheart.bookstore.store.store.controller.customer;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.reference.language.LanguageService;
import com.dynamicheart.bookstore.core.utils.CoreConfiguration;
import com.dynamicheart.bookstore.store.constants.ApplicationConstants;
import com.dynamicheart.bookstore.store.constants.Constants;
import com.dynamicheart.bookstore.store.model.customer.AnonymousCustomer;
import com.dynamicheart.bookstore.store.model.customer.CustomerEntity;
import com.dynamicheart.bookstore.store.model.customer.SecuredStorePersistableCustomer;
import com.dynamicheart.bookstore.store.store.controller.AbstractController;
import com.dynamicheart.bookstore.store.store.controller.customer.facade.CustomerFacade;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by dynamicheart on 6/11/2017.
 */


@Controller
@RequestMapping("/store/customer")
public class CustomerRegistrationController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRegistrationController.class);

    @Inject
    private CoreConfiguration coreConfiguration;

    @Inject
    private LanguageService languageService;

    @Inject
    private CustomerFacade customerFacade;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private LabelUtils messages;


    @RequestMapping(value="/registration", method= RequestMethod.GET)
    public String displayRegistration(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        SecuredStorePersistableCustomer customer = new SecuredStorePersistableCustomer();

        model.addAttribute("customer",customer);

        return "register";
    }


    @RequestMapping(value="/registration", method= RequestMethod.POST)
    public String registerCustomer(@Valid @ModelAttribute("customer") SecuredStorePersistableCustomer customer, BindingResult bindingResult, Model model,
                                   HttpServletRequest request, HttpServletResponse response, final Locale locale ) throws Exception{

        Language language = super.getLanguage(request);

        String userName = null;
        String password = null;

        if ( StringUtils.isNotBlank( customer.getUserName() ) )
        {
            if ( customerFacade.checkIfUserExists( customer.getUserName()) )
            {
                LOGGER.debug( "Customer with username {} already exists for this store ", customer.getUserName() );
                FieldError error = new FieldError("userName","userName",messages.getMessage("registration.username.already.exists", locale));
                bindingResult.addError(error);
            }
            userName = customer.getUserName();
        }

        if ( StringUtils.isNotBlank( customer.getPassword() ) &&  StringUtils.isNotBlank( customer.getCheckPassword() ))
        {
            if (! customer.getPassword().equals(customer.getCheckPassword()) )
            {
                FieldError error = new FieldError("password","password",messages.getMessage("message.password.checkpassword.identical", locale));
                bindingResult.addError(error);

            }
            password = customer.getPassword();
        }

        if ( bindingResult.hasErrors() )
        {
            LOGGER.debug( "found {} validation error while validating in customer registration ",
                    bindingResult.getErrorCount() );

            return "register";
        }

        @SuppressWarnings( "unused" )
        CustomerEntity customerData = null;
        try
        {
            customer.setClearPassword(password);
            customerFacade.registerCustomer( customer,language );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error while registering customer.. ", e);
            ObjectError error = new ObjectError("registration",messages.getMessage("registration.failed", locale));
            bindingResult.addError(error);
            return "register";
        }


        try {
            //refresh customer
            Customer c = customerFacade.getCustomerByUserName(customer.getUserName());
            //authenticate
            customerFacade.authenticate(c, userName, password);
            super.setSessionAttribute(Constants.CUSTOMER, c, request);

            //set username in the cookie
            Cookie cookie = new Cookie(Constants.COOKIE_NAME_USER, c.getNick());
            cookie.setMaxAge(60 * 24 * 3600);
            cookie.setPath(Constants.SLASH);
            response.addCookie(cookie);

            return "redirect:/store/customer/dashboard";


        } catch(Exception e) {
            LOGGER.error("Cannot authenticate user ",e);
            ObjectError error = new ObjectError("registration",messages.getMessage("registration.failed", locale));
            bindingResult.addError(error);
        }

        return "redirect:/";
    }
}
