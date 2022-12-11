package edu.uoc.onlinestore.dao.factory;

import edu.uoc.onlinestore.dao.ClientDAO;
import edu.uoc.onlinestore.dao.ItemDAO;
import edu.uoc.onlinestore.dao.OrderDAO;
import edu.uoc.onlinestore.dao.jpa.ClientDAOJpaImpl;
import edu.uoc.onlinestore.dao.jpa.ItemDAOJpaImpl;
import edu.uoc.onlinestore.dao.jpa.OrderDAOJpaImpl;

public class JpaDAOFactory extends DAOFactory {

    @Override
    public ClientDAO getClientDAO() {

        return new ClientDAOJpaImpl();
    }

    @Override
    public ItemDAO getItemDAO() {

        return new ItemDAOJpaImpl();
    }

    @Override
    public OrderDAO getOrderDAO() {

        return new OrderDAOJpaImpl();
    }
}
