package edu.uoc.onlinestore.dao.jpa;

import edu.uoc.onlinestore.dao.ClientDAO;
import edu.uoc.onlinestore.dao.entities.ClientEntity;
import edu.uoc.onlinestore.dao.entities.ClientType;
import edu.uoc.onlinestore.dao.factory.DAOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClientDAOJpaImplTest {

    ClientDAO<ClientEntity> clientDAO;

    @BeforeEach
    void setUp() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL_JPA);
        clientDAO = daoFactory.getClientDAO();
    }


    @Test
    void findAll() {

        assertDoesNotThrow(()->{
           List<ClientEntity> result = clientDAO.findAll();

           assertNotNull(result,"hay clientes");
            for ( ClientEntity client : result ) {
                System.out.println( client.toString() );
            }

        });

    }

    @Test
    void findById() {

        assertDoesNotThrow(()-> {
            ClientEntity clientEntity = clientDAO.findById("test@test.com");
            assertTrue(clientEntity.getEmail().equals("test@test.com"));
        });
    }


     @Test
    void create() {
        //String email, String name, String address, String nif, ClientType type, float quota, float discount
        String email = UUID.randomUUID().toString().concat("@test.com");
        ClientEntity clientEntity = new ClientEntity(email,"random","ramdom address","12345678R", ClientType.STANDARD,12,4);
        assertDoesNotThrow(()->clientDAO.create(clientEntity));

    }

    @Test
    void delete() {


       assertDoesNotThrow(()->{

           String email = "1c547b0e-036a-4772-a7be-4d38b593021c@test.com";
           assertDoesNotThrow(()->{

               ClientEntity clientEntity = clientDAO.findById(email);
               assertTrue(clientEntity.getEmail().equals(email));
               clientDAO.delete(clientEntity);

           });


       } );

    }

    @Test
    void update() {

        assertDoesNotThrow(()->{

            String email = "test@test.com";
            assertDoesNotThrow(()->{

                ClientEntity clientEntity = clientDAO.findById(email);
                clientEntity.setName("cocote");
                assertTrue(clientEntity.getEmail().equals(email));
                clientDAO.update(clientEntity);

            });


        } );
    }
}