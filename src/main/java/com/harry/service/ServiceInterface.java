package com.harry.service;

import com.harry.dao.DaoInterface;

import java.util.List;

public interface ServiceInterface<T> {

    void setDao(DaoInterface<T> dao);

    DaoInterface<T> getDao();

    T findOne(Integer id);

    List<T> findAll();

    T add(T t);

    T update(T t);

    boolean delete(Integer id);


}
