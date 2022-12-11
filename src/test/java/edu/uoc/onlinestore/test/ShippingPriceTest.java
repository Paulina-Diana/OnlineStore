package edu.uoc.onlinestore.test;

import edu.uoc.onlinestore.dao.entity.ClientEntity;
import edu.uoc.onlinestore.dao.entities.ClientType;
import edu.uoc.onlinestore.model.Item;
import edu.uoc.onlinestore.model.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShippingPriceTest {
    @Test
    public void ShippingPriceTestPremium() {

       // Order order = new Order (001, 3, new ClientPremiun(30F, 0.2F), new Item(" ", " ", 2.5F, 30F, 10), LocalDateTime.parse("2021-10-11T13:25"));
        Order order = new Order (001, 3, new ClientEntity("", "", "","", ClientType.PREMIUM ,30F, 0.2F), new Item(12, " ", 2.5F, 30F, 10), LocalDateTime.parse("2021-10-11T13:25"));
        Item item = new Item();
        float expected = 24F;// precio del coste del envío menos el 20% de dto por ser cliente Premium
        float result = order.shippingPrice();
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void ShippingPriceTestStandard(){

        //Order order = new Order (001, 3, new ClientStandard(), new Item(" ", " ", 2.5F, 30F, 15), LocalDateTime.parse("2021-10-11T13:25"));
        Order order = new Order (001, 3, new ClientEntity(), new Item(12, " ", 2.5F, 30F, 15), LocalDateTime.parse("2021-10-11T13:25"));
        float expected= 30F; //Precio del coste del envío sin ningún descuento asociado ya que no es cliente Premium
        float result = order.shippingPrice();
        assertEquals (expected,result, 0.0);
    }
}
