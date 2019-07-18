package com.gmail.viktordudal.library.dao.impl;

import com.gmail.viktordudal.library.dao.AuthorDao;
import com.gmail.viktordudal.library.model.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDaoImpl extends AbstractDao<Author, Long> implements AuthorDao {
}
