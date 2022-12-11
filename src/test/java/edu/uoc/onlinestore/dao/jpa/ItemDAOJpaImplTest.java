package edu.uoc.onlinestore.dao.jpa;

import edu.uoc.onlinestore.dao.ClientDAO;
import edu.uoc.onlinestore.dao.ItemDAO;
import edu.uoc.onlinestore.dao.entities.ClientEntity;
import edu.uoc.onlinestore.dao.entities.ItemEntity;
import edu.uoc.onlinestore.dao.factory.DAOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOJpaImplTest {

    ItemDAO<ItemEntity> itemDAO;

    @BeforeEach
    void setUp() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL_JPA);
        itemDAO = daoFactory.getItemDAO();
    }

    @Test
    void findById() {
        assertDoesNotThrow(()->{
            ItemEntity itemEntity= itemDAO.findById(001);
            assertTrue(itemEntity.getCode().equals(001));
        });
    }

    @Test
    void findAll() {
        assertDoesNotThrow(()->{
            List<ItemEntity> result = itemDAO.findAll();

            assertNotNull(result,"hay items");
            for ( ItemEntity item : result ) {
                System.out.println( item.toString() );
            }
        });
    }

    @Test
    void create() {
        //int code, String description,float salePrice, float shippingCosts, int preparationTime
        ItemEntity itemEntity = new ItemEntity(001,"random",2, 10, 1);
        assertDoesNotThrow(()->itemDAO.create(itemEntity));
    }

    @Test
    void delete() {
        assertDoesNotThrow(()->{
            int code = 001;
            assertDoesNotThrow(()->{
                ItemEntity itemEntity = itemDAO.findById(code);
                assertTrue(itemEntity.getCode().equals(code));
                itemDAO.delete(itemEntity);
            });
        });
    }

    @Test
    void update() {
        assertDoesNotThrow(()->{
            int code= 001;
            assertDoesNotThrow(()->{
                ItemEntity itemEntity = itemDAO.findById(code);
                assertTrue(itemEntity.getCode().equals(code));
                itemDAO.update(itemEntity);
            });
        });
    }
}