package edu.uoc.onlinestore.dao.factory;

import edu.uoc.onlinestore.dao.*;
import edu.uoc.onlinestore.dao.jdbc.ClientDAOImpl;
import edu.uoc.onlinestore.dao.jdbc.ItemDAOImpl;
import edu.uoc.onlinestore.dao.jdbc.OrderDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlDAOFactory extends DAOFactory {

    private static final String URL_CONNECTION="jdbc:mysql://localhost:3307/online_store_db";

    public static Connection getConnection() throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");

        try{
            Connection conn = DriverManager.getConnection(URL_CONNECTION, "root","root");
            return conn;
        }catch (SQLException e) {
            throw e;
        }


    }


    @Override
    public ClientDAO getClientDAO() {
        return new ClientDAOImpl();
    }

    @Override
    public ItemDAO getItemDAO() {
        return new ItemDAOImpl();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new OrderDAOImpl();
    }
}
