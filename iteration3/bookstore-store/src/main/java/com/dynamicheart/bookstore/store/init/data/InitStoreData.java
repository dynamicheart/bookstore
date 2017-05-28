package com.dynamicheart.bookstore.store.init.data;

import com.dynamicheart.bookstore.core.business.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.customer.CustomerGender;
import com.dynamicheart.bookstore.core.model.user.Group;
import com.dynamicheart.bookstore.core.model.user.GroupType;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.user.GroupService;
import com.dynamicheart.bookstore.store.constants.Constants;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by dynamicheart on 5/28/2017.
 */

@Component
public class InitStoreData implements InitData{

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private CustomerService customerService;

    @Inject
    private GroupService groupService;

    @Override
    public void initInitialData() throws ServiceException {

        if(customerService.count() == 0){
            Customer customer = new Customer();
            customer.setNick("test");
            customer.setGender(CustomerGender.M);
            customer.setDateOfBirth(new Date());
            customer.setEmailAddress("test@bookstore.com");
            String password = passwordEncoder.encode("password");
            customer.setPassword(password);

            List<Group> groups = groupService.listGroup(GroupType.CUSTOMER);
            for(Group group : groups) {
                if(group.getGroupName().equals(Constants.GROUP_CUSTOMER)) {
                    customer.getGroups().add(group);
                }
            }

            customerService.create(customer);
        }
    }
}
