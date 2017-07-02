package com.dynamicheart.bookstore.store.init.data;

import com.dynamicheart.bookstore.core.constants.SystemConstants;
import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.system.SystemConfiguration;
import com.dynamicheart.bookstore.core.model.user.Group;
import com.dynamicheart.bookstore.core.model.user.GroupType;
import com.dynamicheart.bookstore.core.model.user.Permission;
import com.dynamicheart.bookstore.core.services.reference.init.InitializationDatabase;
import com.dynamicheart.bookstore.core.services.system.SystemConfigurationService;
import com.dynamicheart.bookstore.core.services.user.GroupService;
import com.dynamicheart.bookstore.core.services.user.PermissionService;
import com.dynamicheart.bookstore.core.utils.CoreConfiguration;
import com.dynamicheart.bookstore.store.admin.model.permission.Permissions;
import com.dynamicheart.bookstore.store.admin.model.permission.StorePermission;
import com.dynamicheart.bookstore.store.admin.security.WebUserServices;
import com.dynamicheart.bookstore.store.constants.ApplicationConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dynamicheart on 5/28/2017.
 */

@Component
public class InitializationLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializationLoader.class);

    @Inject
    private InitData initData;

    @Inject
    private InitializationDatabase initializationDatabase;

    @Inject
    private WebUserServices userDetailsService;

    @Inject
    protected PermissionService permissionService;

    @Inject
    protected GroupService groupService;

    @Inject
    private SystemConfigurationService systemConfigurationService;

    @Inject
    private CoreConfiguration configuration;

    @Inject
    private ObjectMapper jacksonObjectMapper;

    @Inject
    private WebApplicationContext resourceLoader;

    @PostConstruct
    public void init(){
        try {
            if(initializationDatabase.isEmpty()){
                File permissionXML=resourceLoader.getResource("classpath:/permission/permission.json").getFile();
                StreamSource xmlSource = new StreamSource(permissionXML);

                Permissions permissions= jacksonObjectMapper.readValue(permissionXML,Permissions.class);

                LOGGER.info("Bookstore database is empty, populate it....");

                initializationDatabase.populate("bs-store");

                Map<String, Group> groupMap = new HashMap<String,Group>();
                if(CollectionUtils.isNotEmpty(permissions.getStorePermission())){
                    for(StorePermission shopPermission : permissions.getStorePermission()){

                        Permission permission = new Permission(shopPermission.getType());

                        for(String groupName: shopPermission.getStoreGroup().getName()){
                            if(groupMap.get(groupName) == null){
                                Group group = new Group(groupName);
                                group.setGroupType(GroupType.ADMIN);
                                groupService.create(group);
                                groupMap.put(groupName,group);
                                permission.getGroups().add(group);
                            }
                            else{
                                permission.getGroups().add(groupMap.get(groupName)) ;
                            }
                            permissionService.create( permission);
                        }


                    }
                }
                userDetailsService.createDefaultAdmin();
                loadData();
            }
        }catch (Exception e){
            LOGGER.error("Error in the init method",e);
        }
    }

    private void loadData() throws ServiceException{
        String loadTestData = configuration.getProperty(ApplicationConstants.POPULATE_TEST_DATA);
        boolean loadData =  !StringUtils.isBlank(loadTestData) && loadTestData.equals(SystemConstants.CONFIG_VALUE_TRUE);


        if(loadData) {

            SystemConfiguration configuration = systemConfigurationService.getByKey(ApplicationConstants.TEST_DATA_LOADED);

            if(configuration!=null) {
                if(configuration.getKey().equals(ApplicationConstants.TEST_DATA_LOADED)) {
                    if(configuration.getValue().equals(SystemConstants.CONFIG_VALUE_TRUE)) {
                        return;
                    }
                }
            }

            initData.initInitialData();
            configuration = new SystemConfiguration();
            configuration.getAuditSection().setModifiedBy(SystemConstants.SYSTEM_USER);
            configuration.setKey(ApplicationConstants.TEST_DATA_LOADED);
            configuration.setValue(SystemConstants.CONFIG_VALUE_TRUE);
            systemConfigurationService.create(configuration);
        }
    }
}
