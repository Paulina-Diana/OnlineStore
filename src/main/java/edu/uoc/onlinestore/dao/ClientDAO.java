package edu.uoc.onlinestore.dao;

import edu.uoc.onlinestore.dao.entity.ClientEntity;

import java.sql.SQLException;

public interface ClientDAO<T> extends GenericDAO<T> {
    T findById(String id) throws SQLException;

}
