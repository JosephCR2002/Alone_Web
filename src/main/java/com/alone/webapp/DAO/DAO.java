package com.alone.webapp.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    List<T> findAll() throws SQLException, ClassNotFoundException;
    T findById(int id) throws SQLException, ClassNotFoundException;
    void add(T t) throws SQLException, ClassNotFoundException;
    void delete(int id) throws SQLException, ClassNotFoundException;
}
