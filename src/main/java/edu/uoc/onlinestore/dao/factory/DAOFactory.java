package edu.uoc.onlinestore.dao.factory;

import edu.uoc.onlinestore.dao.ClientDAO;
import edu.uoc.onlinestore.dao.ItemDAO;
import edu.uoc.onlinestore.dao.OrderDAO;

public abstract class DAOFactory {

    public enum Factory{ MYSQL_JDBC,MYSQL_JPA}

    public abstract ClientDAO getClientDAO();

    public abstract ItemDAO getItemDAO();

    public abstract OrderDAO getOrderDAO();

    public static DAOFactory getDAOFactory(){

        return getDAOFactory(Factory.MYSQL_JDBC);
    }

    public static DAOFactory getDAOFactory(Factory factory) {

        switch (factory) {
            case MYSQL_JPA:
                return new JpaDAOFactory();
            case MYSQL_JDBC:
                return new MysqlDAOFactory();

            default:
                return null;
        }
    }


    }



