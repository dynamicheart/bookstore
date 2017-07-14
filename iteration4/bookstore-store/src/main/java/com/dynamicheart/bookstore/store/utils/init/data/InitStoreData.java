package com.dynamicheart.bookstore.store.utils.init.data;

import com.dynamicheart.bookstore.core.utils.exception.ServiceException;
import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.catalog.book.availability.BookAvailability;
import com.dynamicheart.bookstore.core.model.catalog.book.description.BookDescription;
import com.dynamicheart.bookstore.core.model.catalog.book.image.BookImage;
import com.dynamicheart.bookstore.core.model.catalog.category.Category;
import com.dynamicheart.bookstore.core.model.catalog.category.CategoryDescription;
import com.dynamicheart.bookstore.core.model.content.InputContentFile;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.Publisher;
import com.dynamicheart.bookstore.core.model.catalog.book.publisher.PublisherDescription;
import com.dynamicheart.bookstore.core.model.customer.Customer;
import com.dynamicheart.bookstore.core.model.customer.CustomerGender;
import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.core.model.order.orderitem.OrderItem;
import com.dynamicheart.bookstore.core.model.order.orderstatus.OrderStatus;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.model.user.Group;
import com.dynamicheart.bookstore.core.model.user.GroupType;
import com.dynamicheart.bookstore.core.modules.cms.FileManager;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.core.services.catalog.book.image.BookImageService;
import com.dynamicheart.bookstore.core.services.catalog.book.publisher.PublisherService;
import com.dynamicheart.bookstore.core.services.catalog.category.CategoryService;
import com.dynamicheart.bookstore.core.services.customer.CustomerService;
import com.dynamicheart.bookstore.core.services.order.OrderService;
import com.dynamicheart.bookstore.core.services.reference.language.LanguageService;
import com.dynamicheart.bookstore.core.services.user.GroupService;
import com.dynamicheart.bookstore.store.common.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
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
    protected CategoryService categoryService;

    @Inject
    private BookImageService bookImageService;

    @Inject
    private OrderService orderService;

    @Inject
    private PublisherService publisherService;

    @Inject
    private LanguageService languageService;

    @Inject
    private FileManager fileManager;


    @Override
    public void initInitialData() throws ServiceException {
        LOGGER.info("Starting the initialization of test data");
        Language en = languageService.getByCode("en");

        Category book = new Category();
        book.setCode("computerbooks");
        book.setVisible(true);

        CategoryDescription bookEnglishDescription = new CategoryDescription();
        bookEnglishDescription.setName("Computer Books");
        bookEnglishDescription.setCategory(book);
        bookEnglishDescription.setLanguage(en);
        bookEnglishDescription.setSeUrl("computer-books");


        List<CategoryDescription> descriptions = new ArrayList<CategoryDescription>();
        descriptions.add(bookEnglishDescription);

        book.setDescriptions(descriptions);

        categoryService.create(book);

        Category novs = new Category();
        novs.setCode("novels");
        novs.setVisible(false);

        CategoryDescription novsEnglishDescription = new CategoryDescription();
        novsEnglishDescription.setName("Novels");
        novsEnglishDescription.setCategory(novs);
        novsEnglishDescription.setLanguage(en);
        novsEnglishDescription.setSeUrl("novels");


        List<CategoryDescription> descriptions2 = new ArrayList<CategoryDescription>();
        descriptions2.add(novsEnglishDescription);

        novs.setDescriptions(descriptions2);

        categoryService.create(novs);

        Category tech = new Category();
        tech.setCode("tech");

        CategoryDescription techEnglishDescription = new CategoryDescription();
        techEnglishDescription.setName("Technology");
        techEnglishDescription.setCategory(tech);
        techEnglishDescription.setLanguage(en);
        techEnglishDescription.setSeUrl("technology");

        List<CategoryDescription> descriptions4 = new ArrayList<CategoryDescription>();
        descriptions4.add(techEnglishDescription);

        tech.setDescriptions(descriptions4);

        tech.setParent(book);

        categoryService.create(tech);
        categoryService.addChild(book, tech);

        Category web = new Category();
        web.setCode("web");
        web.setVisible(true);

        CategoryDescription webEnglishDescription = new CategoryDescription();
        webEnglishDescription.setName("Web");
        webEnglishDescription.setCategory(web);
        webEnglishDescription.setLanguage(en);
        webEnglishDescription.setSeUrl("the-web");


        List<CategoryDescription> descriptions3 = new ArrayList<CategoryDescription>();
        descriptions3.add(webEnglishDescription);

        web.setDescriptions(descriptions3);

        web.setParent(book);

        categoryService.create(web);
        categoryService.addChild(book, web);



        Category fiction = new Category();
        fiction.setCode("fiction");
        fiction.setVisible(true);

        CategoryDescription fictionEnglishDescription = new CategoryDescription();
        fictionEnglishDescription.setName("Fiction");
        fictionEnglishDescription.setCategory(fiction);
        fictionEnglishDescription.setLanguage(en);
        fictionEnglishDescription.setSeUrl("fiction");

        List<CategoryDescription> fictiondescriptions = new ArrayList<CategoryDescription>();
        fictiondescriptions.add(fictionEnglishDescription);

        fiction.setDescriptions(fictiondescriptions);

        fiction.setParent(novs);

        categoryService.create(fiction);
        categoryService.addChild(novs, fiction);


        Category business = new Category();
        business.setCode("business");
        business.setVisible(true);

        CategoryDescription businessEnglishDescription = new CategoryDescription();
        businessEnglishDescription.setName("Business");
        businessEnglishDescription.setCategory(business);
        businessEnglishDescription.setLanguage(en);
        businessEnglishDescription.setSeUrl("business");

        List<CategoryDescription> businessdescriptions = new ArrayList<CategoryDescription>();
        businessdescriptions.add(businessEnglishDescription);

        business.setDescriptions(businessdescriptions);


        categoryService.create(business);



        Category cloud = new Category();
        cloud.setCode("cloud");
        cloud.setVisible(true);

        CategoryDescription cloudEnglishDescription = new CategoryDescription();
        cloudEnglishDescription.setName("Cloud computing");
        cloudEnglishDescription.setCategory(cloud);
        cloudEnglishDescription.setLanguage(en);
        cloudEnglishDescription.setSeUrl("cloud-computing");

        List<CategoryDescription> clouddescriptions = new ArrayList<CategoryDescription>();
        clouddescriptions.add(cloudEnglishDescription);

        cloud.setDescriptions(clouddescriptions);

        cloud.setParent(tech);

        categoryService.create(cloud);
        categoryService.addChild(tech, cloud);

        Publisher oreilley = new Publisher();
        oreilley.setCode("oreilley");

        PublisherDescription oreilleyd = new PublisherDescription();
        oreilleyd.setName("O\'Reilley");
        oreilleyd.setLanguage(en);
        oreilleyd.setPublisher(oreilley);
        oreilley.getDescriptions().add(oreilleyd);

        publisherService.create(oreilley);


        Publisher sams = new Publisher();
        sams.setCode("sams");

        PublisherDescription samsd = new PublisherDescription();
        samsd.setName("Sams");
        samsd.setLanguage(en);
        samsd.setPublisher(sams);
        sams.getDescriptions().add(samsd);

        publisherService.create(sams);

        Publisher packt = new Publisher();
        packt.setCode("packt");

        PublisherDescription packtd = new PublisherDescription();
        packtd.setName("Packt");
        packtd.setLanguage(en);
        packtd.setPublisher(packt);
        packt.getDescriptions().add(packtd);

        publisherService.create(packt);

        Publisher manning = new Publisher();
        manning.setCode("manning");

        PublisherDescription manningd = new PublisherDescription();
        manningd.setPublisher(manning);
        manningd.setLanguage(en);
        manningd.setName("Manning");
        manning.getDescriptions().add(manningd);

        publisherService.create(manning);

        Publisher novells = new Publisher();
        novells.setCode("novells");

        PublisherDescription novellsd = new PublisherDescription();
        novellsd.setPublisher(novells);
        novellsd.setLanguage(en);
        novellsd.setName("Novells publishing");
        novells.getDescriptions().add(novellsd);

        publisherService.create(novells);

        // BOOK 1

        Book book1 = new Book();
        book1.setIsbn("711541730X");
        book1.setPublisher(manning);

        // Availability
        BookAvailability availability = new BookAvailability();
        availability.setBookQuantity(100);
        availability.setBook(book1);// associate with book

        
        BigDecimal dprice = new BigDecimal(39.99);

        availability.setBookPrice(dprice);
        book1.getAvailabilities().add(availability);

        // BookContainer description
        BookDescription description = new BookDescription();
        description.setName("Spring in Action");
        description.setLanguage(en);
        description.setSeUrl("Spring-in-Action");
        description.setBook(book1);

        book1.getDescriptions().add(description);

        book1.getCategories().add(tech);
        book1.getCategories().add(web);

        bookService.create(book1);
        try {
            ClassPathResource classPathResource = new ClassPathResource("/demo/spring.png");
            InputStream inStream = classPathResource.getInputStream();
            this.saveFile(inStream, "spring.png", book1);
        } catch(Exception e) {
            LOGGER.error("Error while reading demo file spring.png",e);
        }


        // BOOK 2

        Book book2 = new Book();
        book2.setIsbn("7115380333");
        book2.setPublisher(packt);

        // BookContainer description
        description = new BookDescription();
        description.setName("Node Web Development");
        description.setBook(book2);
        description.setLanguage(en);
        description.setSeUrl("Node-Web-Development");

        book2.getDescriptions().add(description);

        book2.getCategories().add(tech);
        book2.getCategories().add(web);

        // Availability
        BookAvailability availability2 = new BookAvailability();
        availability2.setBookQuantity(100);
        availability2.setBook(book2);// associate with book

        BigDecimal dprice2 = new BigDecimal(29.99);

        availability2.setBookPrice(dprice2);
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

        // BookContainer description
        description = new BookDescription();
        description.setName("Programming for PAAS");
        description.setLanguage(en);
        description.setBook(book3);
        description.setSeUrl("programming-for-paas");

        book3.getDescriptions().add(description);

        book3.getCategories().add(cloud);

        // Availability
        BookAvailability availability3 = new BookAvailability();
        availability3.setBookQuantity(100);
        availability3.setBook(book3);// associate with book

        BigDecimal dprice3 = new BigDecimal(19.99);

        availability3.setBookPrice(dprice3);
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

        // BookContainer description
        description = new BookDescription();
        description.setName("Android development");
        description.setBook(book4);
        description.setLanguage(en);
        description.setSeUrl("android-application-development");

        book4.getDescriptions().add(description);

        book4.getCategories().add(tech);


        // Availability
        BookAvailability availability4 = new BookAvailability();
        availability4.setBookQuantity(100);
        availability4.setBook(book4);// associate with book


        BigDecimal dprice4 = new BigDecimal(18.99);

        availability4.setBookPrice(dprice4);
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

        // BookContainer description
        description = new BookDescription();
        description.setName("Android 3.0 Cookbook");
        description.setLanguage(en);
        description.setBook(book5);
        description.setSeUrl("android-3-cookbook");

        book5.getDescriptions().add(description);

        book5.getCategories().add(tech);

        // Availability
        BookAvailability availability5 = new BookAvailability();
        availability5.setBookQuantity(100);
        availability5.setBook(book5);// associate with book

        // bookAvailabilityService.create(availability5);

        BigDecimal dprice5 = new BigDecimal(18.99);

        availability5.setBookPrice(dprice5);
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

        // BookContainer description
        description = new BookDescription();
        description.setName("The Big Switch");
        description.setLanguage(en);
        description.setBook(book6);
        description.setSeUrl("the-big-switch");

        book6.getDescriptions().add(description);

        book6.getCategories().add(business);

        // Availability
        BookAvailability availability6 = new BookAvailability();
        availability6.setBookQuantity(100);
        availability6.setBook(book6);// associate with book

        //bookAvailabilityService.create(availability6);

        BigDecimal dprice6 = new BigDecimal(18.99);

        availability6.setBookPrice(dprice6);
        book6.getAvailabilities().add(availability6);

        bookService.create(book6);

        try {

            ClassPathResource classPathResource = new ClassPathResource("/demo/google.jpg");
            InputStream inStream = classPathResource.getInputStream();
            this.saveFile(inStream, "google.jpg", book6);
        } catch(Exception e) {
            LOGGER.error("Error while reading demo file google.jpg",e);
        }


        Customer customer = new Customer();
        customer.setNick("customer");
        customer.setGender(CustomerGender.M);
        customer.setDefaultLanguage(en);
        customer.setEmailAddress("customer@bookstore.com");
        String password = passwordEncoder.encode("password");
        customer.setPassword(password);

        List<Group> groups = groupService.listGroup(GroupType.CUSTOMER);
        for (Group group : groups) {
            if (group.getGroupName().equals(Constants.GROUP_CUSTOMER)) {
                customer.getGroups().add(group);
            }
        }


        customerService.create(customer);


        //create an order
        Order order = new Order();
        order.setDatePurchased(new Date());
        order.setLastModified(new Date());
        
        order.setCustomerId(customer.getId());
        order.setCustomerEmailAddress("customer@bookstore.com");
        order.setOrderDateFinished(new Date());//committed date


        order.setStatus( OrderStatus.DELIVERED);
        order.setTotal(new BigDecimal(199.99));


        //OrderItem
        OrderItem oitem = new OrderItem();
        oitem.setOneTimeCharge( new BigDecimal(19.99) );
        oitem.setOrder(order);
        oitem.setItemName( "The Big Switch" );
        oitem.setItemQuantity(1);
        oitem.setIsbn("9787508657424" );

        order.getOrderItems().add(oitem);

        orderService.create(order);

        LOGGER.info("Ending the initialization of test data");
    }

    private void saveFile(InputStream fis, String name, Book book) throws Exception {
        if(fis==null) {
            return;
        }

        final FileNameMap fileNameMap = URLConnection.getFileNameMap();

        InputContentFile file = new InputContentFile();
        file.setFile(fis);
        file.setFileName(name);
        file.setMimeType(fileNameMap.getContentTypeFor(name));
        try{
            String resourceId = fileManager.addFile(file);

            BookImage image = new BookImage();
            image.setBook(book);
            image.setDefaultImage(true);
            image.setResourceId(resourceId);
            bookImageService.save(image);
        }catch (Exception ignore){
        }
    }
}
