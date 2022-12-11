package edu.uoc.onlinestore.dao;

import edu.uoc.onlinestore.dao.entity.ClientEntity;
import edu.uoc.onlinestore.dao.entities.ClientType;
import edu.uoc.onlinestore.dao.factory.DAOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ClientDAOImplTest {

    ClientDAO<ClientEntity> clientDAO;

    @BeforeEach
    void setUp() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        clientDAO = daoFactory.getClientDAO();
    }

    @Test
    @DisplayName("find_email_not_found")
    void findById() {

        String expectedEmailId = "ppovis@uoc.edu1";

        assertThrows(SQLException.class,()->{
            clientDAO.findById(expectedEmailId);
        });
    }

    @Test
    @DisplayName("find_email_found")
    void findByIdFound() {
        String expectedEmailId = "ppovis@uoc.edu";

        assertDoesNotThrow(()->{
           ClientEntity client = clientDAO.findById(expectedEmailId);
           assertNotNull(client);
           assertEquals(expectedEmailId,client.getEmail());
        });
    }

    @Test
    @DisplayName("create_client_ilegalstate")
    void createClient() {
        ClientEntity client = new ClientEntity("","name","address","99999999R", ClientType.PREMIUM,12,10);
        assertThrows(IllegalStateException.class,()->clientDAO.create(client));

    }

    @Test
    @DisplayName("create_client")
    void createClientInsert() {
        ClientEntity client = new ClientEntity("test@test.com","name","address","99999999R", ClientType.PREMIUM,12,10);
        assertDoesNotThrow(()->clientDAO.create(client));

    }

    @Test
    @DisplayName("update_client_ilegal_state")
    void updateClientErrorValidate() {
        ClientEntity client = new ClientEntity("","name","address","99999999R", ClientType.PREMIUM,12,10);
        assertThrows(IllegalStateException.class,()->clientDAO.update(client));
    }

    @Test
    @DisplayName("update_client")
    void updateClient() {
        ClientEntity client = new ClientEntity("test@test.com","name test","address de test","11111111R", ClientType.PREMIUM,8,4);
        assertDoesNotThrow(()->clientDAO.update(client));
    }

}