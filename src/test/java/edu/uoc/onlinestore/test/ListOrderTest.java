package edu.uoc.onlinestore.test;

import edu.uoc.onlinestore.model.Order;
import edu.uoc.onlinestore.model.ListOrder;
import org.junit.jupiter.api.Test;

class ListOrderTest {
    @Test
    void add() throws Exception {

        ListOrder listOrder = new ListOrder();
        Order order = new Order();
        order.setCode(2222);

        listOrder.add(order);
    }
}
