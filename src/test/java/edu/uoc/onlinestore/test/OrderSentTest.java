package edu.uoc.onlinestore.test;

import edu.uoc.onlinestore.dao.entity.ClientEntity;
import edu.uoc.onlinestore.model.Item;
import edu.uoc.onlinestore.model.Order;
import edu.uoc.onlinestore.model.ClientPremiun;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderSentTest {

    @Test
    public void OrderSentTest() {
        //Order order = new Order(001, 3, new ClientPremiun(), new Item(" ", " ", 2.5F, 30F, 10), LocalDateTime.parse("2021-10-11T13:25"));
        Order order = new Order(001, 3, new ClientEntity(), new Item(12, " ", 2.5F, 30F, 10), LocalDateTime.parse("2021-10-11T13:25"));
        boolean expected = true;
        boolean result = order.orderSent();
        assertEquals(expected, result);
    }
    @Test
    public void OrderNotSentTest(){
        //Order order = new Order (001, 3, new ClientPremiun(), new Item(" ", " ", 2.5F, 30F, 0), LocalDateTime.parse("2021-10-11T13:25"));
        Order order = new Order (001, 3, new ClientEntity(), new Item(12, " ", 2.5F, 30F, 0), LocalDateTime.parse("2021-10-11T13:25"));
        boolean expected = false;
        boolean result = order.orderSent();
        assertEquals(expected, result);
    }
}

