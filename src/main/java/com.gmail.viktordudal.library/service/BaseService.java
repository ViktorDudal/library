package com.gmail.viktordudal.library.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, K> {

    Optional<T> findById(K k);

    List<T> findAll();

    T create(T t);

    T update(T t);

    void deleteById(K k);
}
