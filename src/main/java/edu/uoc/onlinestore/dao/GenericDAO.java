package edu.uoc.onlinestore.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

    public List<T> findAll() throws SQLException;

    public T findById(T t) throws SQLException;

    public void create(T t) throws SQLException;

    public void delete(T t) throws SQLException;

    public void update(T t) throws SQLException;

}
