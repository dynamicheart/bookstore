package com.dynamicheart.bookstore.store.init.data;

import com.dynamicheart.bookstore.core.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.availability.BookAvailability;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.model.catalog.book.price.BookPrice;
import com.dynamicheart.bookstore.core.model.catalog.book.price.BookPriceDescription;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.Publisher;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.PublisherDescription;
import com.dynamicheart.bookstore.core.model.common.Billing;
import com.dynamicheart.bookstore.core.model.common.Delivery;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.customer.CustomerGender;
import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.core.model.order.orderitem.OrderItem;
import com.dynamicheart.bookstore.core.model.order.orderitem.OrderItemPrice;
import com.dynamicheart.bookstore.core.model.order.orderstatus.OrderStatus;
import com.dynamicheart.bookstore.core.model.user.Group;
import com.dynamicheart.bookstore.core.model.user.GroupType;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.book.image.BookImageService;
import com.dynamicheart.bookstore.core.services.catalog.book.publisher.PublisherService;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.order.OrderService;
import com.dynamicheart.bookstore.core.services.user.GroupService;
import com.dynamicheart.bookstore.store.constants.Constants;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by dynamicheart on 5/28/2017.
 */

@Component
public class InitStoreData implements InitData{

    private static final Logger LOGGER = LoggerFactory.getLogger(InitStoreData.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private CustomerService customerService;

    @Inject
    private GroupService groupService;

    @Inject
    private BookService bookService;

    @Inject
    private BookImageService bookImageService;

    @Inject
    private OrderService orderService;

    @Inject
    private PublisherService publisherService;


    @Override
    public void initInitialData() throws ServiceException {
        LOGGER.info("Starting the initialization of test data");
        Date date = new Date(System.currentTimeMillis());

        Publisher oreilley = new Publisher();
        oreilley.setCode("oreilley");

        PublisherDescription oreilleyd = new PublisherDescription();
        oreilleyd.setName("O\'Reilley");
        oreilleyd.setPublisher(oreilley);
        oreilley.getDescriptions().add(oreilleyd);

        publisherService.create(oreilley);


        Publisher sams = new Publisher();
        sams.setCode("sams");

        PublisherDescription samsd = new PublisherDescription();
        samsd.setName("Sams");
        samsd.setPublisher(sams);
        sams.getDescriptions().add(samsd);

        publisherService.create(sams);

        Publisher packt = new Publisher();
        packt.setCode("packt");

        PublisherDescription packtd = new PublisherDescription();
        packtd.setName("Packt");
        packtd.setPublisher(packt);
        packt.getDescriptions().add(packtd);

        publisherService.create(packt);

        Publisher manning = new Publisher();
        manning.setCode("manning");

        PublisherDescription manningd = new PublisherDescription();
        manningd.setPublisher(manning);
        manningd.setName("Manning");
        manning.getDescriptions().add(manningd);

        publisherService.create(manning);

        Publisher novells = new Publisher();
        novells.setCode("novells");

        PublisherDescription novellsd = new PublisherDescription();
        novellsd.setPublisher(novells);
        novellsd.setName("Novells publishing");
        novells.getDescriptions().add(novellsd);

        publisherService.create(novells);

        // BOOK 1

        Book book = new Book();
        book.setIsbn("711541730X");
        book.setPublisher(manning);

        // Availability
        BookAvailability availability = new BookAvailability();
        availability.setBookDateAvailable(date);
        availability.setBookQuantity(100);
        availability.setBook(book);// associate with book



        BookPrice dprice = new BookPrice();
        dprice.setDefaultPrice(true);
        dprice.setBookPriceAmount(new BigDecimal(39.99));
        dprice.setBookAvailability(availability);

        BookPriceDescription dpd = new BookPriceDescription();
        dpd.setName("Base price");
        dpd.setBookPrice(dprice);

        dprice.getDescriptions().add(dpd);

        availability.getPrices().add(dprice);
        book.getAvailabilities().add(availability);

        // Book description
        BookDescription description = new BookDescription();
        description.setName("Spring in Action");
        description.setSeUrl("Spring-in-Action");
        description.setBook(book);

        book.getDescriptions().add(description);

        bookService.create(book);
        try {
            ClassPathResource classPathResource = new ClassPathResource("/demo/spring.png");
            InputStream inStream = classPathResource.getInputStream();
            this.saveFile(inStream, "spring.png", book);
        } catch(Exception e) {
            LOGGER.error("Error while reading demo file spring.png",e);
        }


        // BOOK 2

        Book book2 = new Book();
        book2.setIsbn("7115380333");
        book2.setPublisher(packt);

        // Book description
        description = new BookDescription();
        description.setName("Node Web Development");
        description.setBook(book2);
        description.setSeUrl("Node-Web-Development");

        book2.getDescriptions().add(description);


        // Availability
        BookAvailability availability2 = new BookAvailability();
        availability2.setBookDateAvailable(date);
        availability2.setBookQuantity(100);
        availability2.setBook(book2);// associate with book

        BookPrice dprice2 = new BookPrice();
        dprice2.setDefaultPrice(true);
        dprice2.setBookPriceAmount(new BigDecimal(29.99));
        dprice2.setBookAvailability(availability2);

        dpd = new BookPriceDescription();
        dpd.setName("Base price");
        dpd.setBookPrice(dprice2);

        dprice2.getDescriptions().add(dpd);

        availability2.getPrices().add(dprice2);
        book2.getAvailabilities().add(availability2);

        bookService.create(book2);

        try {
            ClassPathResource classPathResource = new ClassPathResource("/demo/node.jpg");
            InputStream inStream = classPathResource.getInputStream();
            this.saveFile(inStream, "node.jpg", book2);
        } catch(Exception e) {
            LOGGER.error("Error while reading demo file node.jpg",e);
        }

        // BOOK 3

        Book book3 = new Book();
        book3.setIsbn("9787111482451");
        book3.setPublisher(oreilley);

        // Book description
        description = new BookDescription();
        description.setName("Programming for PAAS");
        description.setBook(book3);
        description.setSeUrl("programming-for-paas");

        book3.getDescriptions().add(description);

        // Availability
        BookAvailability availability3 = new BookAvailability();
        availability3.setBookDateAvailable(date);
        availability3.setBookQuantity(100);
        availability3.setBook(book3);// associate with book

        BookPrice dprice3 = new BookPrice();
        dprice3.setDefaultPrice(true);
        dprice3.setBookPriceAmount(new BigDecimal(19.99));
        dprice3.setBookAvailability(availability3);

        dpd = new BookPriceDescription();
        dpd.setName("Base price");
        dpd.setBookPrice(dprice3);

        dprice3.getDescriptions().add(dpd);

        availability3.getPrices().add(dprice3);
        book3.getAvailabilities().add(availability3);


        bookService.create(book3);

        try {
            ClassPathResource classPathResource = new ClassPathResource("/demo/paas.JPG");
            InputStream inStream = classPathResource.getInputStream();
            this.saveFile(inStream, "paas.JPG", book3);
        } catch(Exception e) {
            LOGGER.error("Error while reading demo file paas.jpg",e);
        }


        // BOOK 4
        Book book4 = new Book();
        book4.setIsbn("9788126531066");
        book4.setPublisher(sams);

        // Book description
        description = new BookDescription();
        description.setName("Android development");
        description.setBook(book4);
        description.setSeUrl("android-application-development");

        book4.getDescriptions().add(description);


        // Availability
        BookAvailability availability4 = new BookAvailability();
        availability4.setBookDateAvailable(date);
        availability4.setBookQuantity(100);
        availability4.setBook(book4);// associate with book


        BookPrice dprice4 = new BookPrice();
        dprice4.setDefaultPrice(true);
        dprice4.setBookPriceAmount(new BigDecimal(18.99));
        dprice4.setBookAvailability(availability4);

        dpd = new BookPriceDescription();
        dpd.setName("Base price");
        dpd.setBookPrice(dprice4);

        dprice4.getDescriptions().add(dpd);

        availability4.getPrices().add(dprice4);
        book4.getAvailabilities().add(availability4);

        bookService.create(book4);
        try {
            ClassPathResource classPathResource = new ClassPathResource("/demo/android.jpg");
            InputStream inStream = classPathResource.getInputStream();
            this.saveFile(inStream, "android.jpg", book4);
        } catch(Exception e) {
            LOGGER.error("Error while reading demo file android.jpg",e);
        }


        // BOOK 5
        Book book5 = new Book();
        book5.setIsbn("9781849612954");
        book5.setPublisher(packt);

        // Book description
        description = new BookDescription();
        description.setName("Android 3.0 Cookbook");
        description.setBook(book5);
        description.setSeUrl("android-3-cookbook");

        book5.getDescriptions().add(description);



        // Availability
        BookAvailability availability5 = new BookAvailability();
        availability5.setBookDateAvailable(date);
        availability5.setBookQuantity(100);
        availability5.setBook(book5);// associate with book

        // bookAvailabilityService.create(availability5);

        BookPrice dprice5 = new BookPrice();
        dprice5.setDefaultPrice(true);
        dprice5.setBookPriceAmount(new BigDecimal(18.99));
        dprice5.setBookAvailability(availability5);

        dpd = new BookPriceDescription();
        dpd.setName("Base price");
        dpd.setBookPrice(dprice5);

        dprice5.getDescriptions().add(dpd);

        availability5.getPrices().add(dprice5);
        book5.getAvailabilities().add(availability5);

        bookService.create(book5);

        try {
            ClassPathResource classPathResource = new ClassPathResource("/demo/android2.jpg");
            InputStream inStream = classPathResource.getInputStream();
            this.saveFile(inStream, "android2.jpg", book5);
        } catch(Exception e) {
            LOGGER.error("Error while reading demo file android2.jpg",e);
        }



        // BOOK 6
        Book book6 = new Book();
        book6.setIsbn("9787508657424");
        book6.setPublisher(novells);

        // Book description
        description = new BookDescription();
        description.setName("The Big Switch");
        description.setBook(book6);
        description.setSeUrl("the-big-switch");

        book6.getDescriptions().add(description);
        try {

            ClassPathResource classPathResource = new ClassPathResource("/demo/google.jpg");
            InputStream inStream = classPathResource.getInputStream();
            this.saveFile(inStream, "google.jpg", book6);
        } catch(Exception e) {
            LOGGER.error("Error while reading demo file google.jpg",e);
        }

        // Availability
        BookAvailability availability6 = new BookAvailability();
        availability6.setBookDateAvailable(date);
        availability6.setBookQuantity(100);
        availability6.setBook(book6);// associate with book

        //bookAvailabilityService.create(availability6);

        BookPrice dprice6 = new BookPrice();
        dprice6.setDefaultPrice(true);
        dprice6.setBookPriceAmount(new BigDecimal(18.99));
        dprice6.setBookAvailability(availability6);

        dpd = new BookPriceDescription();
        dpd.setName("Base price");
        dpd.setBookPrice(dprice6);

        dprice6.getDescriptions().add(dpd);

        availability6.getPrices().add(dprice6);
        book6.getAvailabilities().add(availability6);

        bookService.create(book6);


        Customer customer = new Customer();
        customer.setNick("customer");
        customer.setGender(CustomerGender.M);
        customer.setDateOfBirth(new Date());
        customer.setEmailAddress("customer@bookstore.com");
        String password = passwordEncoder.encode("password");
        customer.setPassword(password);

        List<Group> groups = groupService.listGroup(GroupType.CUSTOMER);
        for (Group group : groups) {
            if (group.getGroupName().equals(Constants.GROUP_CUSTOMER)) {
                customer.getGroups().add(group);
            }
        }

        Delivery delivery = new Delivery();
        delivery.setAddress("358 Du Languadoc");
        delivery.setCity( "Boucherville" );
        delivery.setFirstName("Leonardo" );
        delivery.setLastName("DiCaprio" );
        delivery.setPostalCode("J4B-8J9" );

        Billing billing = new Billing();
        billing.setAddress("358 Du Languadoc");
        billing.setCity("Boucherville");
        billing.setCompany("CSTI Consulting");
        billing.setFirstName("Leonardo" );
        billing.setLastName("DiCaprio" );
        billing.setPostalCode("J4B-8J9");

        customer.setDelivery(delivery);
        customer.setBilling(billing);
        customerService.create(customer);


        //create an order
        Order order = new Order();
        order.setDatePurchased(new Date());
        order.setLastModified(new Date());
        order.setBilling(billing);

        order.setCustomerId(customer.getId());
        order.setCustomerEmailAddress("customer@bookstore.com");
        order.setOrderDateFinished(new Date());//committed date


        order.setStatus( OrderStatus.DELIVERED);
        order.setTotal(new BigDecimal(23.99));


        //OrderItemPrice
        OrderItemPrice oitemprice = new OrderItemPrice();
        oitemprice.setDefaultPrice(true);
        oitemprice.setItemPrice(new BigDecimal(19.99) );
        oitemprice.setItemPriceCode("baseprice" );
        oitemprice.setItemPriceName("Base Price" );
        //oitemprice.setBookPriceSpecialAmount(new BigDecimal(13.99) );


        //OrderItem
        OrderItem oitem = new OrderItem();
        oitem.setOneTimeCharge( new BigDecimal(19.99) );
        oitem.setOrder(order);
        oitem.setItemName( "The Big Switch" );
        oitem.setItemQuantity(1);
        oitem.setIsbn("9787508657424" );
        oitem.getPrices().add(oitemprice ) ;

        oitemprice.setOrderItem(oitem);
        order.getOrderItems().add(oitem);


        orderService.create(order);

        LOGGER.info("Ending the initialization of test data");
    }

    private void saveFile(InputStream fis, String name, Book book) throws Exception {
        if(fis==null) {
            return;
        }

        final byte[] is = IOUtils.toByteArray( fis );


        BookImage bookImage = new BookImage();
        bookImage.setBookImage(name);
        bookImage.setBookId(book.getId());
        bookImage.setImageContent(is);


        bookImageService.create(bookImage);
    }
}
