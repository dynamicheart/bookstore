package com.dynamicheart.bookstore.core.services.catalog.book.publisher;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.Publisher;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.PublisherDescription;
import com.dynamicheart.bookstore.core.repositories.catalog.book.publisher.PublisherRepository;
import com.dynamicheart.bookstore.core.services.common.generic.BookstoreEntityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;


@Service("publisherService")
public class PublisherServiceImpl extends
		BookstoreEntityServiceImpl<Long, Publisher> implements PublisherService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PublisherServiceImpl.class);

	private PublisherRepository publisherRepository;
	
	@Inject
	public PublisherServiceImpl(
		PublisherRepository publisherRepository) {
		super(publisherRepository);
		this.publisherRepository = publisherRepository;		
	}
	
	@Override 
	public void delete(Publisher publisher) throws ServiceException {
		publisher =  this.getById(publisher.getId() );
		super.delete( publisher );
	}
	
	@Override
	public Long getCountManufAttachedProducts( Publisher publisher ) throws ServiceException {
		return publisherRepository.countByBook(publisher.getId());
				//.getCountManufAttachedProducts( publisher );
	}


	@Override
	public void addPublisherDescription(Publisher publisher, PublisherDescription description)
			throws ServiceException {
		
		
		if(publisher.getDescriptions()==null) {
			publisher.setDescriptions(new HashSet<PublisherDescription>());
		}
		
		publisher.getDescriptions().add(description);
		description.setPublisher(publisher);
		update(publisher);
	}
	
	@Override	
	public void saveOrUpdate(Publisher publisher) throws ServiceException {

		LOGGER.debug("Creating Publisher");
		
		if(publisher.getId()!=null && publisher.getId()>0) {
		   super.update(publisher);  
			
		} else {						
		   super.create(publisher);

		}
	}

	@Override
	public Publisher getByCode(String code) {
		return publisherRepository.findByCode(code);
	}
}
