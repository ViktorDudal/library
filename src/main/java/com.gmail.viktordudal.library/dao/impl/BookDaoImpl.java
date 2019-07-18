package com.gmail.viktordudal.library.dao.impl;

import com.gmail.viktordudal.library.dao.BookDao;
import com.gmail.viktordudal.library.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl extends AbstractDao<Book, Long> implements BookDao {
}
