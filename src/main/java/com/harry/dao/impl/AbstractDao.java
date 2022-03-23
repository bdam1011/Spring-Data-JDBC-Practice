package com.harry.dao.impl;

import com.harry.dao.DaoInterface;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.util.List;

public abstract class AbstractDao<T> implements DaoInterface<T> {
    private String tableName;
    private RowMapper<T> rowMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;


    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void setRowMapper(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Override
    public T findOne(Integer id) {
        try {
            return jdbcTemplate.queryForObject("select *from " + tableName + " where id_ = ?", rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        try {
            return jdbcTemplate.query("select * from " + tableName, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public boolean delete(Integer id) {
        boolean result = false;
        if (this.findOne(id) != null) {
            int target = jdbcTemplate.update("delete from " + tableName + " where id_=?", id);
            if (target == 1) {
                result = true;
            }
        }
        return result;
    }
}
