package com.harry.service.impl;

import com.harry.dao.DaoInterface;
import com.harry.service.ServiceInterface;

import javax.annotation.Resource;
import java.util.List;

public abstract class ServiceInterfaceImpl<T> implements ServiceInterface<T> {
    private DaoInterface<T> dao;

    @Resource
    @Override
    public void setDao(DaoInterface<T> dao) {
        this.dao = dao;
    }

    @Override
    public DaoInterface<T> getDao() {
        return dao;
    }

    @Override
    public T findOne(Integer id) {
        T result = null;
        result = dao.findOne(id);
        return result;
    }

    @Override
    public List<T> findAll() {
        List<T> result = null;
        result = dao.findAll();
        return result;
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
        boolean result = false;
        T target = dao.findOne(id);
        if (target != null) {
            if(dao.delete(id)==1){
                result = true;
            }
        }
        return result;
    }
}
