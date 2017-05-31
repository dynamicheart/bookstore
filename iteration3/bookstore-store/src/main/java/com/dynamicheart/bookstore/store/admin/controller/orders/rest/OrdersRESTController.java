package com.dynamicheart.bookstore.store.admin.controller.orders.rest;

import com.dynamicheart.bookstore.core.model.order.Order;
import com.dynamicheart.bookstore.core.services.order.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.dynamicheart.bookstore.store.utils.BriefUtils.getOrderBriefs;

/**
 * Created by dynamicheart on 5/29/2017.
 */

@RestController
public class OrdersRESTController {
    @Inject
    private OrderService orderService;

    @RequestMapping(
            value = "api/admin/orders",
            params = {"draw", "start","length"},
            method = RequestMethod.GET
    )
    public @ResponseBody
    Map<String, Object> pageOrders(
            @RequestParam("draw") int draw,
            @RequestParam("start") int start,
            @RequestParam("length") int length){
        Map<String, Object> result = new HashMap<>();
        Page<Order> pages = orderService.findPaginated(start/length, length);
        result.put("data", getOrderBriefs(pages.getContent()));
        result.put("draw",draw);
        result.put("recordsTotal",pages.getTotalElements());
        result.put("recordsFiltered",pages.getTotalElements());
        return result;
    }

    @RequestMapping(
            value="api/admin/order/{id}",
            method=RequestMethod.DELETE
    )
    public @ResponseBody
    ResponseEntity<String> deleteOrder(
            @PathVariable("id") Long id){
        try {
            Order order = orderService.getById(id);

            if(order == null) {
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            } else {
                orderService.delete(order);
                return new ResponseEntity<String>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
