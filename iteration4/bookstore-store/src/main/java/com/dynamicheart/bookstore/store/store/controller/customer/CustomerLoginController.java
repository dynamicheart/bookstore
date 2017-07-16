package com.dynamicheart.bookstore.store.store.controller.customer;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.store.store.model.AjaxResponse;
import com.dynamicheart.bookstore.store.common.constants.StoreConstants;
import com.dynamicheart.bookstore.store.store.model.customer.SecuredCustomer;
import com.dynamicheart.bookstore.store.store.controller.AbstractController;
import com.dynamicheart.bookstore.store.store.controller.customer.facade.CustomerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dynamicheart on 7/6/2017.
 */

@Controller
@RequestMapping("/store/customer")
public class CustomerLoginController extends AbstractController{

    private static final Logger LOG = LoggerFactory.getLogger(CustomerLoginController.class);

    @Inject
    private AuthenticationManager customerAuthenticationManager;

    @Inject
    private CustomerFacade customerFacade;


    @RequestMapping(value="/login", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AjaxResponse> jsonLogon(@ModelAttribute SecuredCustomer securedCustomer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        AjaxResponse jsonObject = this.logon(securedCustomer.getUserName(), securedCustomer.getPassword(), request, response);
        return new ResponseEntity<AjaxResponse>(jsonObject, HttpStatus.OK);

    }

    private AjaxResponse logon(String userName, String password, HttpServletRequest request, HttpServletResponse response) throws Exception {

        AjaxResponse jsonObject = new AjaxResponse();
        try {

            LOG.debug("Authenticating user " + userName);

            Language language = (Language)request.getAttribute("LANGUAGE");
            //check if username is from the appropriate store
            Customer customerModel = customerFacade.getCustomerByUserName(userName);
            if(customerModel==null) {
                jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
                return jsonObject;
            }


            customerFacade.authenticate(customerModel, userName, password);
            //set customer in the http session
            super.setSessionAttribute(StoreConstants.CUSTOMER, customerModel, request);
            jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
            jsonObject.addEntry(StoreConstants.RESPONSE_KEY_USERNAME, customerModel.getNick());


            //set username in the cookie
            Cookie c = new Cookie(StoreConstants.COOKIE_NAME_USER, customerModel.getNick());
            c.setMaxAge(60 * 24 * 3600);
            c.setPath(StoreConstants.SLASH);
            response.addCookie(c);


        } catch (AuthenticationException ex) {
            jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
        } catch(Exception e) {
            jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
        }

        return jsonObject;
    }

    @RequestMapping(value="/authenticate", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AjaxResponse> basicLogon(@RequestParam String userName, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AjaxResponse jsonObject = this.logon(userName, password,request, response);
        return new ResponseEntity<AjaxResponse>(jsonObject, HttpStatus.OK);
    }


    @RequestMapping(value="/logon", method= RequestMethod.GET)
    public String displayLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "store/login";
    }

}
