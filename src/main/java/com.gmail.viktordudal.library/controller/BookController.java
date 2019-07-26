package com.gmail.viktordudal.library.controller;

import com.gmail.viktordudal.library.exception.ResourceNotFoundException;
import com.gmail.viktordudal.library.model.Book;
import com.gmail.viktordudal.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/all")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @PutMapping(value = "/update")
    public Book update(@Valid @RequestBody Book book) {
        return bookService.update(book);
    }

    @PostMapping(value = "/create")
    public Book create(@Valid @RequestBody Book book) {
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
