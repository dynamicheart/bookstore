package com.dynamicheart.bookstore.domain;

import org.junit.Test;

import java.sql.Timestamp;

/**
 * Created by dynamicheart on 4/25/2017.
 */
public class TestOrder {
    @Test
    public void testOrderTime(){
        Order order = new Order();
        order.setPlaceTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(order.getPlaceTime().toString());
    }
}
