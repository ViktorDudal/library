package com.gmail.viktordudal.library.dao.impl;

import com.gmail.viktordudal.library.dao.BaseDao;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class AbstractDao<T, K extends Serializable> implements BaseDao<T, K> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Class<T> clazz;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public Optional<T> findById(K k) {
        if (k == null) return Optional.empty();
        T entity = sessionFactory.getCurrentSession().get(clazz, k);
        return Optional.ofNullable(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T create(T object) {
        sessionFactory.getCurrentSession().save(object);
        return object;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T update(T object) {
        sessionFactory.getCurrentSession().merge(object);
        return object;
    }

    @Override
    public void deleteById(K k) {
        T entity = sessionFactory.getCurrentSession().get(clazz, k);
        if (entity != null) {
            delete(entity);
        }
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return (List<T>) sessionFactory.getCurrentSession().createQuery("from " + clazz.getName()).list();
    }
}
