package edu.uoc.onlinestore.dao;

import edu.uoc.onlinestore.dao.entities.ItemEntity;
import edu.uoc.onlinestore.dao.entity.ClientEntity;
import edu.uoc.onlinestore.dao.factory.DAOFactory;
import edu.uoc.onlinestore.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOImplTest {

    ItemDAO<Item> itemDAO;

    @BeforeEach
    void setUp() {
        itemDAO = DAOFactory.getDAOFactory().getItemDAO();
    }

    @Test
    void findAll() {

        assertDoesNotThrow(()->itemDAO.findAll().forEach(i ->System.out.println(i)));

    }



    @Test
    void testFindById() {

        int expectedCode = 1;
        assertDoesNotThrow(()->{
            Item item = itemDAO.findById(expectedCode);
            assertNotNull(item);
            assertEquals(expectedCode,item.getCode());
        });
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}