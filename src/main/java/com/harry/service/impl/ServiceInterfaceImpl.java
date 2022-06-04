package com.harry.service.impl;

import com.harry.dao.DaoInterface;
import com.harry.service.ServiceInterface;

import javax.annotation.Resource;
import java.util.List;

public abstract class ServiceInterfaceImpl<T,R extends DaoInterface<T>> implements ServiceInterface<T,R > {
    private R dao;

    @Resource
    @Override
    public void setDao(R dao) {
        this.dao =  dao;
    }

    @Override
    public R getDao() {
        return  dao;
    }

    @Override
    public T findOne(Integer id) {
        return dao.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

//    @Override
//    public T add(T t) {
//        return null;
//    }
//
//    @Override
//    public T update(T t) {
//        return null;
//    }

    @Override
    public boolean delete(Integer id) {
        return dao.delete(id);
    }
}
