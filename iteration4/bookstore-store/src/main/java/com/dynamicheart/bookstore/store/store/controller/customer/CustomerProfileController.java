package com.dynamicheart.bookstore.store.store.controller.customer;

import com.dynamicheart.bookstore.core.model.content.InputContentFile;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.customer.avatar.CustomerAvatar;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.modules.cms.FileManager;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.customer.avatar.CustomerAvatarService;
import com.dynamicheart.bookstore.store.common.constants.StoreConstants;
import com.dynamicheart.bookstore.store.store.controller.AbstractController;
import com.dynamicheart.bookstore.store.store.controller.customer.facade.CustomerFacade;
import com.dynamicheart.bookstore.store.store.model.customer.CustomerEntity;
import com.dynamicheart.bookstore.store.utils.ImageFilePath;
import com.dynamicheart.bookstore.store.utils.LabelUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Locale;

/**
 * Created by dynamicheart on 7/14/2017.
 */

@Controller
@RequestMapping("/store/customer")
public class CustomerProfileController extends AbstractController{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerProfileController.class);

    @Inject
    private CustomerFacade customerFacade;

    @Inject
    private FileManager fileManager;

    @Inject
    private CustomerService customerService;

    @Inject
    private CustomerAvatarService customerAvatarService;

    @Inject
    private ImageFilePath imageUtils;

    @Inject
    private LabelUtils messages;



    @PreAuthorize("hasRole('AUTH_CUSTOMER')")
    @RequestMapping("/profile")
    public String displayProfile(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{

        Language language = getSessionAttribute(StoreConstants.LANGUAGE, request);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = null;
        if(auth != null && request.isUserInRole("AUTH_CUSTOMER")) {
            customer = customerFacade.getCustomerByUserName(auth.getName());
        }

        if(customer == null){
            return "redirect:/"+StoreConstants.STORE_URI;
        }

        CustomerEntity customerEntity = customerFacade.getCustomerDataByUserName(customer.getNick(), language);
        model.addAttribute("customer",customerEntity);

        return "customer-profile";
    }

    @PreAuthorize("hasRole('AUTH_CUSTOMER')")
    @RequestMapping("/profile/update")
    public String updateProfile(@Valid @ModelAttribute("customer")CustomerEntity customerEntity, Model model, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception{

        Language language = getSessionAttribute(StoreConstants.LANGUAGE, request);

        String userName = null;
        String password = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = null;
        if(auth != null && request.isUserInRole("AUTH_CUSTOMER")) {
            customer = customerFacade.getCustomerByUserName(auth.getName());
        }

        if(customer == null){
            return "redirect:/"+StoreConstants.STORE_URI;
        }

        if ( StringUtils.isNotBlank( customerEntity.getUserName() ) )
        {
            if ( customerFacade.checkIfUserExists( customerEntity.getUserName()) )
            {
                LOGGER.debug( "Customer with username {} already exists for this store ", customerEntity.getUserName() );
                FieldError error = new FieldError("userName","userName",messages.getMessage("registration.username.already.exists", locale));
                bindingResult.addError(error);
            }
            userName = customerEntity.getUserName();
        }

        if ( bindingResult.hasErrors() )
        {
            LOGGER.debug( "found {} validation error while validating in customer registration ",
                    bindingResult.getErrorCount() );

            return "register";
        }

        customer.setNick(customerEntity.getUserName());
        customer.setGender(customerEntity.getGender());
        customer.setEmailAddress(customerEntity.getEmailAddress());

        customerService.saveOrUpdate(customer);

        try {
            //refresh customer
            Customer c = customerFacade.getCustomerByUserName(customerEntity.getUserName());
            //authenticate
            customerFacade.authenticate(c, userName, c.getPassword());
            super.setSessionAttribute(StoreConstants.CUSTOMER, c, request);

            //set username in the cookie
            Cookie cookie = new Cookie(StoreConstants.COOKIE_NAME_USER, c.getNick());
            cookie.setMaxAge(60 * 24 * 3600);
            cookie.setPath(StoreConstants.SLASH);
            response.addCookie(cookie);

            return "customer-profile";


        } catch(Exception e) {
            LOGGER.error("Cannot authenticate user ",e);
            ObjectError error = new ObjectError("registration",messages.getMessage("registration.failed", locale));
            bindingResult.addError(error);
        }


        return "customer-profile";
    }

    @PreAuthorize("hasRole('AUTH_CUSTOMER')")
    @RequestMapping(value = "/profile/avatar/upload")
    public String handleUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws Exception{
        Language language = getSessionAttribute(StoreConstants.LANGUAGE, request);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = null;
        if(auth != null && request.isUserInRole("AUTH_CUSTOMER")) {
            customer = customerFacade.getCustomerByUserName(auth.getName());
        }

        saveAvatar(file.getInputStream(), file.getName(), customer);

        return "redirect:/store/customer/profile";
    }


    private void saveAvatar(InputStream fis, String name, Customer customer) throws Exception {
        if(fis==null) {
            return;
        }

        final FileNameMap fileNameMap = URLConnection.getFileNameMap();

        InputContentFile file = new InputContentFile();
        file.setFile(fis);
        file.setFileName(name);
        file.setMimeType(fileNameMap.getContentTypeFor(name));

        String resourceId = fileManager.addFile(file);


        CustomerAvatar avatar = customer.getDefaultAvatar();
        if(avatar != null){
            avatar.setDefaultAvatar(false);
            customerAvatarService.save(customer.getDefaultAvatar());
        }

        CustomerAvatar customerAvatar = new CustomerAvatar();
        customerAvatar.setCustomer(customer);
        customerAvatar.setDefaultAvatar(true);
        customerAvatar.setResourceId(resourceId);

        customerAvatarService.save(customerAvatar);

    }
}
