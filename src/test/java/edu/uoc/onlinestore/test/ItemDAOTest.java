package edu.uoc.onlinestore.test;

import edu.uoc.onlinestore.dao.ItemDAO;
import edu.uoc.onlinestore.dao.factory.DAOFactory;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ItemDAOTest {


    @Test()
    public void testItemDAOFindAll(){


        DAOFactory factory = DAOFactory.getDAOFactory();

        ItemDAO itemDAO = factory.getItemDAO();
        try {
            itemDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
