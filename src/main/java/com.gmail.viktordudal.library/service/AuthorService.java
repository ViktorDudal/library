package com.gmail.viktordudal.library.service;

import com.gmail.viktordudal.library.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);

    List<Author> findAll();

    Author create(Author author);

    Author update(Author author);

    void deleteById(Long id);
}
