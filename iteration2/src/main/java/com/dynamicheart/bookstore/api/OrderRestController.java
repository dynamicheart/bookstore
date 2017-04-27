package com.dynamicheart.bookstore.api;

import com.dynamicheart.bookstore.domain.Order;
import com.dynamicheart.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Timestamp;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by dynamicheart on 4/25/2017.
 */

@RestController
@RequestMapping("/api")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    //Retrieve All Orders
    @RequestMapping(value = "/order", method = GET)
    public ResponseEntity<List<Order>> listAllOrders(){
        List<Order> orders = orderService.findOrders();
        if(orders.isEmpty()){
            return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    //Retrieve Single Order
    @RequestMapping(value = "/order/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
        Order order = orderService.findOne(id);
        if (order == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    //Create a Order
    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {

        if (orderService.isExist(order)) {
            return new ResponseEntity<Order>(HttpStatus.CONFLICT);
        }

        order.setPlaceTime(new Timestamp(System.currentTimeMillis()));
        orderService.save(order);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    //Update a Order
    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody Order order) {

        if (orderService.findOne(id)==null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }

        orderService.update(order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    //Delete a Order
    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") Long id) {
        Order order = orderService.findOne(id);
        if (order == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }

        orderService.delete(id);
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }

    //Delete All Orders
    @RequestMapping(value = "/order", method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteAllOrders() {
        //orderService.deleteAllOrders();
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }
}
