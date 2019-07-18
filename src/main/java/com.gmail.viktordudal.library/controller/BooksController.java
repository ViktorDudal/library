package com.gmail.viktordudal.library.controller;

import com.gmail.viktordudal.library.exception.ResourceNotFoundException;
import com.gmail.viktordudal.library.model.Book;
import com.gmail.viktordudal.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BookService bookService;


    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/all")
    public List<Book> getAll() {
        logger.warn("Test Books controller!");
        return bookService.findAll();
    }

    @PutMapping(value = "/update")
    public Book update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @PostMapping(value = "/create")
    public Book create(@RequestBody Book book) {
        return bookService.create(book);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        bookService.deleteById(id);
    }

    @GetMapping(value = "/byId/{bookId}")
    public Book getBookById(@PathVariable("bookId") Long bookId) {
        return bookService.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
    }
}
