package edu.uoc.onlinestore.dao;

import edu.uoc.onlinestore.dao.entity.OrderEntity;

import java.sql.SQLException;

public interface OrderDAO<T> extends GenericDAO<T> {
    public T findById(Integer id) throws SQLException;
}
