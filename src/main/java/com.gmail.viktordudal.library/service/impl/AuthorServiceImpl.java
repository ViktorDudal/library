package com.gmail.viktordudal.library.service.impl;

import com.gmail.viktordudal.library.dao.AuthorDao;
import com.gmail.viktordudal.library.model.Author;
import com.gmail.viktordudal.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorDao.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    public Author create(Author author) {
        return authorDao.create(author);
    }

    @Override
    public Author update(Author author) {
        return authorDao.update(author);
    }

    @Override
    public void deleteById(Long id) {
        authorDao.deleteById(id);
    }
}
