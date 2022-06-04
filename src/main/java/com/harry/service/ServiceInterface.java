package com.harry.service;


import com.harry.dao.DaoInterface;

import java.util.List;

public interface ServiceInterface<T,R extends DaoInterface<T>> {

    void setDao(R dao);

    R getDao();

    T findOne(Integer id);

    List<T> findAll();

    T add(T t);

    T update(T t);

    boolean delete(Integer id);


}
