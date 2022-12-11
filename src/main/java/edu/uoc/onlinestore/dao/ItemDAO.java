package edu.uoc.onlinestore.dao;

import edu.uoc.onlinestore.model.Item;

import java.sql.SQLException;

public interface ItemDAO<T> extends GenericDAO<T> {
    T findById(int id) throws SQLException;
}
