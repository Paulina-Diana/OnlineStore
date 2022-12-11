package edu.uoc.onlinestore.test;

import edu.uoc.onlinestore.model.Client;
import edu.uoc.onlinestore.model.ClientStandard;
import edu.uoc.onlinestore.model.ListClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListClientTest {

    @Test
    void add() throws Exception {

        // test para probar si existe el email antes de agregar un cliente.
        ListClient listClient = new ListClient();
        Client client = new ClientStandard();
        client.setEmail("diana@mie.com");

        listClient.add(client);
    }
}