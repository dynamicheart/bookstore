package com.dynamicheart.bookstore.core.repositories.system;

import com.dynamicheart.bookstore.core.model.system.SystemConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemConfigurationRepository extends JpaRepository<SystemConfiguration, Long> {


	SystemConfiguration findByKey(String key);

}
