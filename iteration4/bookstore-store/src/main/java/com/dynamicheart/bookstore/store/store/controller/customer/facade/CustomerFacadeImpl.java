package com.dynamicheart.bookstore.store.store.controller.customer.facade;

import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.customer.CustomerGender;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.model.shoppingcart.ShoppingCart;
import com.dynamicheart.bookstore.core.model.user.Group;
import com.dynamicheart.bookstore.core.model.user.GroupType;
import com.dynamicheart.bookstore.core.model.user.Permission;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.reference.language.LanguageService;
import com.dynamicheart.bookstore.core.services.shoppingcart.ShoppingCartService;
import com.dynamicheart.bookstore.core.services.user.GroupService;
import com.dynamicheart.bookstore.core.services.user.PermissionService;
import com.dynamicheart.bookstore.store.common.constants.Constants;
import com.dynamicheart.bookstore.store.store.model.customer.CustomerEntity;
import com.dynamicheart.bookstore.store.store.model.customer.ReadableAndPersistableCustomer;
import com.dynamicheart.bookstore.store.store.populator.customer.CustomerEntityPopulator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dynamicheart on 7/6/2017.
 */

@Service( value = "customerFacade" )
public class CustomerFacadeImpl implements CustomerFacade{

    private static final Logger LOG = LoggerFactory.getLogger(CustomerFacadeImpl.class);
    private final static int USERNAME_LENGTH=6;

    public final static String ROLE_PREFIX = "ROLE_";//Spring Security 4


    @Inject
    private CustomerService customerService;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Inject
    private LanguageService languageService;

    @Inject
    private GroupService groupService;

    @Inject
    private PermissionService permissionService;

    @SuppressWarnings( "deprecation" )
    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private AuthenticationManager customerAuthenticationManager;

    @Override
    public CustomerEntity getCustomerDataByUserName(String userName, Language language) throws Exception {
        return null;
    }

    @Override
    public ReadableAndPersistableCustomer getCustomerById(Long id, Language language) throws Exception {
        return null;
    }

    @Override
    public ShoppingCart mergeCart(Customer customer, String sessionShoppingCartId, Language language) throws Exception {
        return null;
    }

    @Override
    public Customer getCustomerByUserName(String userName) throws Exception {
        return customerService.getByNick(userName);
    }

    @Override
    public boolean checkIfUserExists(String userName) throws Exception {
        if ( StringUtils.isNotBlank( userName ) )
        {
            Customer customer = customerService.getByNick(userName);
            if ( customer != null )
            {
                LOG.info( "Customer with userName {} already exists for store {} ", userName);
                return true;
            }

            LOG.info( "No customer found with userName {} for store {} ", userName);
            return false;

        }
        LOG.info( "Either userName is empty or we have not found any value for store" );
        return false;
    }

    @Override
    public CustomerEntity registerCustomer(ReadableAndPersistableCustomer customer, Language language) throws Exception {
        LOG.info( "Starting customer registration process.." );
        Customer customerModel= getCustomerModel(customer, language);
        if(customerModel == null){
            LOG.equals( "Unable to create customer in system" );
            throw new Exception( "Unable to register customer" );
        }

        LOG.info( "About to persist customer to database." );
        customerService.saveOrUpdate( customerModel );

        LOG.info( "Returning customer data to controller.." );
        return customerEntityPoulator(customerModel);
    }

    @Override
    public void setCustomerModelDefaultProperties(Customer customer) throws Exception {
        Validate.notNull(customer, "Customer object cannot be null");

        if(CollectionUtils.isEmpty(customer.getGroups())) {
            List<Group> groups = groupService.listGroup(GroupType.CUSTOMER);
            for(Group group : groups) {
                if(group.getGroupName().equals(Constants.GROUP_CUSTOMER)) {
                    customer.getGroups().add(group);
                }
            }
        }
    }

    @Override
    public void authenticate(Customer customer, String userName, String password) throws Exception {

        Validate.notNull(customer, "Customer cannot be null");

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority role = new SimpleGrantedAuthority(ROLE_PREFIX + Constants.PERMISSION_CUSTOMER_AUTHENTICATED);//required to login
        authorities.add(role);
        List<Integer> groupsId = new ArrayList<Integer>();
        List<Group> groups = customer.getGroups();
        if(groups!=null) {
            for(Group group : groups) {
                groupsId.add(group.getId());

            }
            if(groupsId!=null && groupsId.size()>0) {
                List<Permission> permissions = permissionService.getPermissions(groupsId);
                for(Permission permission : permissions) {
                    GrantedAuthority auth = new SimpleGrantedAuthority(permission.getPermissionName());
                    authorities.add(auth);
                }
            }
        }

        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, password, authorities);

        Authentication authentication = customerAuthenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public Customer getCustomerModel(ReadableAndPersistableCustomer customer, Language language) throws Exception {
        LOG.info( "Starting to populate customer model from customer data" );
        Customer customerModel = new Customer();
        customerModel.setNick(customer.getUserName());
        customerModel.setAnonymous(false);
        customerModel.setPassword(passwordEncoder.encode(customer.getClearPassword()));
        customerModel.setEmailAddress(customer.getEmailAddress());
        customerModel.setGender(CustomerGender.valueOf(customer.getGender()));
        customerModel.setDefaultLanguage(languageService.defaultLanguage());

        setCustomerModelDefaultProperties(customerModel);

        return customerModel;
    }

    @Override
    public Customer populateCustomerModel(Customer customerModel, ReadableAndPersistableCustomer customer, Language language) throws Exception {
        return null;
    }

    private CustomerEntity customerEntityPoulator(final Customer customerModel){
        CustomerEntityPopulator customerPopulator=new CustomerEntityPopulator();
        try
        {
            CustomerEntity customerEntity = null;
            if(customerEntity !=null){
                customerEntity.setId( customerModel.getId() );
                LOG.info( "Retunring populated instance of customer entity" );
                return customerEntity;
            }
            LOG.warn( "Seems some issue with customerEntity populator..retunring null instance of customerEntity " );
            return null;

        }
        catch ( Exception e )
        {
            LOG.error( "Error while converting customer model to customer entity ",e );

        }
        return null;
    }
}
