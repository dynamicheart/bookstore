package com.dynamicheart.bookstore.store.store.controller.shoppingcart;

import com.dynamicheart.bookstore.core.model.catalog.book.Book;
import com.dynamicheart.bookstore.core.model.reference.language.Language;
import com.dynamicheart.bookstore.core.services.catalog.book.BookService;
import com.dynamicheart.bookstore.store.common.constants.StoreConstants;
import com.dynamicheart.bookstore.store.store.controller.AbstractController;
import com.dynamicheart.bookstore.store.store.model.AjaxResponse;
import com.dynamicheart.bookstore.store.store.model.catalog.book.ReadableBook;
import com.dynamicheart.bookstore.store.store.model.shoppingcart.ShoppingCart;
import com.dynamicheart.bookstore.store.store.model.shoppingcart.ShoppingCartItem;
import com.dynamicheart.bookstore.store.store.populator.catalog.ReadableBookPopulator;
import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.dynamicheart.bookstore.store.store.model.AjaxResponse.RESPONSE_STATUS_SUCCESS;

/**
 * Created by dynamicheart on 7/6/2017.
 */

@Controller
@RequestMapping("/store/cart")
public class ShoppingCartController extends AbstractController{

    @Inject
    private BookService bookService;

    @Inject
    private ReadableBookPopulator populator;

    @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.GET)
    public String displayShpping(final Model model, final HttpServletRequest request, final HttpServletResponse response, final Locale locale ) throws Exception{
        /** there must be a cart in the session **/
        ShoppingCart shoppingCart = (ShoppingCart)request.getSession().getAttribute(StoreConstants.SHOPPING_CART);

        if(shoppingCart == null) {
            return "cart";
        }

        model.addAttribute("cart", shoppingCart);

        return "cart";
    }


    @RequestMapping(value={"/addShoppingCartItem"}, method={RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<AjaxResponse> addShoppingCartItem(@RequestParam final String bookIsbn, @RequestParam final int quantity, final Model model, final HttpServletRequest request, final HttpServletResponse response, final Locale locale ) throws Exception{
        ShoppingCart shoppingCart = (ShoppingCart)request.getSession().getAttribute(StoreConstants.SHOPPING_CART);
        Language language = (Language)request.getAttribute(StoreConstants.LANGUAGE);

        if(shoppingCart == null){
            shoppingCart = new ShoppingCart();
        }

        ShoppingCartItem shoppingCartItem = shoppingCart.getShoppingCartItems().get(bookIsbn);
        if(shoppingCartItem == null){
            shoppingCartItem = new ShoppingCartItem();
            Book book = bookService.getByCode(bookIsbn);
            if(book == null){
                return new ResponseEntity<AjaxResponse>(HttpStatus.NO_CONTENT);
            }
            ReadableBook readableBook = new ReadableBook();
            populator.populate(book, readableBook, language);
            shoppingCartItem.setBookId(readableBook.getId());
            shoppingCartItem.setQuantity(quantity);
            shoppingCartItem.setBookPrice(readableBook.getPrice());
            shoppingCartItem.setName(readableBook.getDescription().getName());
            shoppingCartItem.setImage(readableBook.getDefaultImage());
        }else {
            shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + quantity);
        }

        shoppingCart.getShoppingCartItems().put(bookIsbn, shoppingCartItem);

        setSessionAttribute(StoreConstants.SHOPPING_CART, shoppingCart, request);

        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(RESPONSE_STATUS_SUCCESS);

        return new ResponseEntity<AjaxResponse>(ajaxResponse, HttpStatus.OK);
    }

    @RequestMapping(value={"/removeShoppingCartItem"},method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<AjaxResponse> removeShoppingCartItem(@RequestParam final String bookIsbn, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        ShoppingCart shoppingCart = (ShoppingCart)request.getSession().getAttribute(StoreConstants.SHOPPING_CART);

        Validate.notNull(shoppingCart);

        shoppingCart.getShoppingCartItems().remove(bookIsbn);

        if(shoppingCart.getShoppingCartItems().size() == 0){
            removeAttribute(StoreConstants.SHOPPING_CART, request);
        }else {
            setSessionAttribute(StoreConstants.SHOPPING_CART, shoppingCart, request);
        }

        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(RESPONSE_STATUS_SUCCESS);

        return new ResponseEntity<AjaxResponse>(ajaxResponse, HttpStatus.OK);
    }


    @RequestMapping(value={"/updateShoppingCartItem"},method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<AjaxResponse> updateShoppingCartItem(@RequestParam final String bookIsbn, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        ShoppingCart shoppingCart = (ShoppingCart)request.getSession().getAttribute(StoreConstants.SHOPPING_CART);

        Validate.notNull(shoppingCart);

        ShoppingCartItem shoppingCartItem = shoppingCart.getShoppingCartItems().get(bookIsbn);

        if(shoppingCart.getShoppingCartItems().size() == 0){
            removeAttribute(StoreConstants.SHOPPING_CART, request);
        }else {
            setSessionAttribute(StoreConstants.SHOPPING_CART, shoppingCart, request);
        }

        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(RESPONSE_STATUS_SUCCESS);

        return new ResponseEntity<AjaxResponse>(ajaxResponse, HttpStatus.OK);
    }

}
