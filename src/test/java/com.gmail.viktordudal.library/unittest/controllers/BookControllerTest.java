package com.gmail.viktordudal.library.unittest.controllers;

import com.gmail.viktordudal.library.controller.BookController;
import com.gmail.viktordudal.library.exception.ResourceNotFoundException;
import com.gmail.viktordudal.library.model.Book;
import com.gmail.viktordudal.library.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BookController.class)
@PowerMockIgnore("javax.management.*")
public class BookControllerTest {

    @Mock
    private BookService bookService;

    private BookController controller;

    private static final Long BOOK_ID = 1L;

    @Before
    public void setUp() {
        this.controller = new BookController(bookService);
    }

    @Test
    public void getAll() {
        Book mockBook1 = Book.builder()
                .bookId(1L)
                .author("Tolkien")
                .build();
        Book mockBook2 = Book.builder()
                .bookId(2L)
                .author("King")
                .build();
        List<Book> mockBooks = new LinkedList<>();
        mockBooks.add(mockBook1);
        mockBooks.add(mockBook2);

        when(bookService.findAll()).thenReturn(mockBooks);
        List<Book> allBooks = controller.getAll();

        assertEquals(mockBooks, allBooks);

        verify(bookService, times(1)).findAll();
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void update() {
        Book mockBook = Book.builder()
                .bookId(1L)
                .author("Tolkien")
                .build();

        when(bookService.update(any(Book.class))).thenReturn(mockBook);
        Book updatedBook = controller.update(new Book());

        assertEquals(mockBook, updatedBook);

        verify(bookService, times(1)).update(any(Book.class));
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void create() {
        Book mockBook = Book.builder()
                .bookId(1L)
                .author("Tolkien")
                .build();

        when(bookService.create(any(Book.class))).thenReturn(mockBook);
        Book createdBook = controller.create(new Book());

        assertEquals(mockBook, createdBook);

        verify(bookService, times(1)).create(any(Book.class));
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void delete() {
        controller.delete(BOOK_ID);

        verify(bookService, times(1)).deleteById(eq(BOOK_ID));
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBookById() {
        Book mockBook = Book.builder()
                .bookId(1L)
                .author("Tolkien")
                .build();

        when(bookService.findById(eq(BOOK_ID))).thenReturn(Optional.of(mockBook));
        Book bookById = controller.getBookById(BOOK_ID);

        assertEquals(mockBook, bookById);

        verify(bookService, times(1)).findById(eq(BOOK_ID));
        verifyNoMoreInteractions(bookService);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getBookByIdNotFound() {
        when(bookService.findById(eq(BOOK_ID))).thenThrow(new ResourceNotFoundException("Book not found with id " + BOOK_ID));

        controller.getBookById(BOOK_ID);

        verify(bookService, times(1)).findById(eq(BOOK_ID));
        verifyNoMoreInteractions(bookService);
    }
}
