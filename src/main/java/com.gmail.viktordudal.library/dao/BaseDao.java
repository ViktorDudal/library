package com.gmail.viktordudal.library.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T, K extends Serializable> {

    Optional<T> findById(K k);

    T create(T object);

    T update(T object);

    void delete(T object);

    void deleteById(K k);

    List<T> findAll();

}
