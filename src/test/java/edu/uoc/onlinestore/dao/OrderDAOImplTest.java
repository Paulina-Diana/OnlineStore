package edu.uoc.onlinestore.dao;

import edu.uoc.onlinestore.dao.entity.OrderEntity;
import edu.uoc.onlinestore.dao.factory.DAOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDAOImplTest {
    OrderDAO orderDAO;

    @BeforeEach
    void setUp() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        orderDAO = daoFactory.getOrderDAO();
    }



    @Test
    @DisplayName("create_order_ilegalstate")
    void createOrder() {
        OrderEntity order = new OrderEntity(5, 3," ",4, LocalDateTime.now());
        assertThrows(IllegalStateException.class, ()->orderDAO.create(order));
    }

    @Test
    @DisplayName("create_order")
    void createOrderInsert() {
        OrderEntity order = new OrderEntity(12, 55,"test@test.com",1, LocalDateTime.now());
        assertDoesNotThrow(()->orderDAO.create(order));
    }


    @Test
    @DisplayName("update_order_ilegalstate")
    void updateOrderErrorValidate() {
        OrderEntity order = new OrderEntity(0, 3," ",4, LocalDateTime.now());
        assertThrows(IllegalStateException.class, ()->orderDAO.update(order));
    }

    @Test
    @DisplayName("update_order")
    void updateOrder() {
        OrderEntity order = new OrderEntity(12, 3,"test@test.com",4, LocalDateTime.now());
        assertDoesNotThrow(()->orderDAO.update(order));
    }
}