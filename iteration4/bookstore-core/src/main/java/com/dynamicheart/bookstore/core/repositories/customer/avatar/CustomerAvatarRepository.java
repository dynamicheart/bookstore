package com.dynamicheart.bookstore.core.repositories.customer.avatar;

import com.dynamicheart.bookstore.core.model.customer.avatar.CustomerAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dynamicheart on 7/16/2017.
 */
public interface CustomerAvatarRepository extends JpaRepository<CustomerAvatar, Long> {
}
