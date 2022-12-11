package edu.uoc.onlinestore.test;

import edu.uoc.onlinestore.model.Client;
import edu.uoc.onlinestore.model.ClientStandard;
import edu.uoc.onlinestore.model.ListClient;
import edu.uoc.onlinestore.model.ListItem;
import edu.uoc.onlinestore.model.Item;
import edu.uoc.onlinestore.model.Order;
import edu.uoc.onlinestore.model.ListOrder;

import org.junit.jupiter.api.Test;




public class ExceptionsTests {
    @Test
    void ListClientTest() throws Exception {

        // test para probar si existe el email antes de agregar un cliente.
        ListClient listClient = new ListClient();
        Client client = new ClientStandard();
        client.setEmail("diana@mie.com");

        listClient.add(client);
    }
    @Test
    void ListItemTest() throws Exception {

        //test para comprobar si existe el codidgo del item antes de agregarlo.
        ListItem listItem = new ListItem();
        Item item = new Item();
        item.setCode(2111);

        listItem.add(item);
    }
    @Test
    void ListOrderTest() throws Exception {

        //test para comprobar si existe el codigo de un pedido antes de grabarlo.
        ListOrder listOrder = new ListOrder();
        Order order = new Order();
        order.setCode(2222);

        listOrder.add(order);
    }
}
