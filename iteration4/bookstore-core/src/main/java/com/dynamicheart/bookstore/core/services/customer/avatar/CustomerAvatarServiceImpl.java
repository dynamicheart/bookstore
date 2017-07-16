package com.dynamicheart.bookstore.core.services.customer.avatar;

import com.dynamicheart.bookstore.core.model.customer.avatar.CustomerAvatar;
import com.dynamicheart.bookstore.core.repositories.customer.avatar.CustomerAvatarRepository;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by dynamicheart on 7/16/2017.
 */

@Service
@Qualifier("customerAvatar")
public class CustomerAvatarServiceImpl extends BookstoreEntityServiceImpl<Long, CustomerAvatar> implements CustomerAvatarService {

    @Inject
    private CustomerAvatarRepository customerAvatarRepository;

    public CustomerAvatarServiceImpl(CustomerAvatarRepository customerAvatarRepository) {
        super(customerAvatarRepository);
        this.customerAvatarRepository = customerAvatarRepository;
    }
}
