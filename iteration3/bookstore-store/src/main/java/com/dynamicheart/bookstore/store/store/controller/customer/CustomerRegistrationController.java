package com.dynamicheart.bookstore.store.store.controller.customer;

import com.dynamicheart.bookstore.core.services.reference.language.LanguageService;
import com.dynamicheart.bookstore.core.utils.CoreConfiguration;
import com.dynamicheart.bookstore.store.constants.ApplicationConstants;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dynamicheart on 6/11/2017.
 */


@Controller
@RequestMapping("/store/customer")
public class CustomerRegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRegistrationController.class);


    @Inject
    private CoreConfiguration coreConfiguration;

    @Inject
    private LanguageService languageService;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private LabelUtils messages;

    @Inject
    private AuthenticationManager customerAuthenticationManager;


    @RequestMapping(value="/registration.html", method= RequestMethod.GET)
    public String displayRegistration(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return null;
    }
}
