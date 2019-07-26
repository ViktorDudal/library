package com.gmail.viktordudal.library.unittest.services;

import com.gmail.viktordudal.library.dao.BookDao;
import com.gmail.viktordudal.library.model.Book;
import com.gmail.viktordudal.library.service.BookService;
import com.gmail.viktordudal.library.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
public class BookServiceTest {

    @Mock
    private BookDao bookDao;

    private BookService service;

    private final static long ID =2;

    @Before
    public void setUp(){
        this.service = new BookServiceImpl(bookDao);
    }

    @Test
    public void findById() {
        Book mockBook = Book.builder()
                .bookId(ID)
                .build();

        when(bookDao.findById(eq(ID))).thenReturn(Optional.of(mockBook));

        Optional<Book> bookById = service.findById(ID);

        assertEquals(Optional.of(mockBook), bookById);

        verify(bookDao, times(1)).findById(eq(ID));
        verifyNoMoreInteractions(bookDao);
    }

    @Test
    public void findAll() {
        List<Book> mockBooks = new ArrayList<>();
        when(bookDao.findAll()).thenReturn(mockBooks);

        List<Book> allBooks = service.findAll();

        assertEquals(mockBooks, allBooks);

        verify(bookDao, times(1)).findAll();
        verifyNoMoreInteractions(bookDao);
    }

    @Test
    public void create() {
        Book mockBook = Book.builder()
                .bookId(4L)
                .build();

        when(bookDao.create(any(Book.class))).thenReturn(mockBook);
        Book createdBook = service.create(new Book());

        assertEquals(mockBook, createdBook);

        verify(bookDao, times(1)).create(any(Book.class));
        verifyNoMoreInteractions(bookDao);
    }

    @Test
    public void update() {
        Book mockBook = Book.builder()
                .bookId(4L)
                .build();

        when(bookDao.update(any(Book.class))).thenReturn(mockBook);
        Book updatedBook = service.update(new Book());

        assertEquals(mockBook, updatedBook);

        verify(bookDao, times(1)).update(any(Book.class));
        verifyNoMoreInteractions(bookDao);
    }

    @Test
    public void deleteById() {
        service.deleteById(ID);

        verify(bookDao, times(1)).deleteById(eq(ID));
        verifyNoMoreInteractions(bookDao);
    }
}
