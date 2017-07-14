package com.dynamicheart.bookstore.core.services.user;


import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.user.Group;
import com.dynamicheart.bookstore.core.model.user.GroupType;
import com.dynamicheart.bookstore.core.repositories.user.GroupRepository;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;


@Service("groupService")
public class GroupServiceImpl extends
        BookstoreEntityServiceImpl<Integer, Group> implements GroupService {

	GroupRepository groupRepository;


	@Inject
	public GroupServiceImpl(GroupRepository groupRepository) {
		super(groupRepository);
		this.groupRepository = groupRepository;

	}


	@Override
	public List<Group> listGroup(GroupType groupType) throws ServiceException {
		try {
			return groupRepository.findByType(groupType);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Group> listGroupByIds(Set<Integer> ids) throws ServiceException {
		try {
			return groupRepository.findByIds(ids);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


}
