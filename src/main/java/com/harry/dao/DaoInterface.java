package com.harry.dao;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface DaoInterface<T> {

    void setTableName(String tableName);

    void setRowMapper(RowMapper<T> mapper);

    T findOne(Integer id);

    List<T> findAll();

    T add(T t);

    T update(T t);

    boolean delete(Integer id);

}
