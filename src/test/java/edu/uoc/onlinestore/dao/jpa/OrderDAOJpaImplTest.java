package edu.uoc.onlinestore.dao.jpa;

import edu.uoc.onlinestore.dao.ClientDAO;
import edu.uoc.onlinestore.dao.ItemDAO;
import edu.uoc.onlinestore.dao.OrderDAO;
import edu.uoc.onlinestore.dao.entities.ClientEntity;
import edu.uoc.onlinestore.dao.entities.ItemEntity;
import edu.uoc.onlinestore.dao.entities.OrderEntity;
import edu.uoc.onlinestore.dao.factory.DAOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOJpaImplTest {

    OrderDAO<OrderEntity> orderDAO;

    ClientDAO<ClientEntity> clientDAO;

    ItemDAO<ItemEntity> itemDAO;

    @BeforeEach
    void setUp() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL_JPA);
        orderDAO = daoFactory.getOrderDAO();
        clientDAO = daoFactory.getClientDAO();
        itemDAO = daoFactory.getItemDAO();
    }

    @Test
    void findAll() {

        assertDoesNotThrow(()->{
            List<OrderEntity> result = orderDAO.findAll();

            assertNotNull(result,"exists orders");
            for ( OrderEntity order : result ) {
                System.out.println( order.toString() );
            }

        });
    }

    @Test
    void findById() {

        assertDoesNotThrow(()-> {
            OrderEntity orderEntity = orderDAO.findById(1);
            assertTrue(orderEntity.getCode().equals(1));
        });
    }

    @Test
    void create() {
        //int codeOrder, int amount, LocalDataTime orderDataTime

        assertDoesNotThrow(() -> {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderDateTime(LocalDateTime.now());
            orderEntity.setItemEntity(itemDAO.findById(1));
            orderEntity.setClientEntity(clientDAO.findById("test@test.com"));
            orderEntity.setAmount(12);
            orderDAO.create(orderEntity);
        });
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> {
            int codeOrder = 1;
            assertDoesNotThrow(() -> {
                OrderEntity orderEntity = orderDAO.findById(codeOrder);
                assertTrue(orderEntity.getCode().equals(codeOrder));
                orderDAO.delete(orderEntity);
            });
        });
    }

    @Test
    void update() {
        assertDoesNotThrow(() -> {
            int codeOrder = 1;
            assertDoesNotThrow(() -> {
                OrderEntity orderEntity = orderDAO.findById(codeOrder);
                assertTrue(orderEntity.getCode().equals(codeOrder));
                orderDAO.update(orderEntity);
            });
        });
    }
}