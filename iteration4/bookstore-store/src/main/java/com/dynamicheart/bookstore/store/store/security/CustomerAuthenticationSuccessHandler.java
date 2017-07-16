package com.dynamicheart.bookstore.store.store.security;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.store.admin.controller.customers.CustomerController;
import com.dynamicheart.bookstore.store.common.constants.StoreConstants;
import com.dynamicheart.bookstore.store.store.controller.customer.facade.CustomerFacade;
import com.dynamicheart.bookstore.store.store.model.AjaxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dynamicheart on 7/16/2017.
 */
public class CustomerAuthenticationSuccessHandler extends
        SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAuthenticationSuccessHandler.class);

    @Inject
    CustomerFacade customerFacade;


    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        try {

            String userName = authentication.getName();

            Language language = (Language)request.getAttribute("LANGUAGE");
            //check if username is from the appropriate store
            Customer customerModel = customerFacade.getCustomerByUserName(userName);

            customerFacade.authenticate(customerModel, userName, customerModel.getPassword());
            //set customer in the http session
            request.getSession().setAttribute(StoreConstants.CUSTOMER, customerModel);

            //set username in the cookie
            Cookie c = new Cookie(StoreConstants.COOKIE_NAME_USER, customerModel.getNick());
            c.setMaxAge(60 * 24 * 3600);
            c.setPath(StoreConstants.SLASH);
            response.addCookie(c);
        } catch(Exception ignore) {

        }
        redirectStrategy.sendRedirect(request, response, request.getContextPath() + "/store");
    }


    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
