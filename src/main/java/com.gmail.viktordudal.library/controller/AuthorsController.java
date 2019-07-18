package com.gmail.viktordudal.library.controller;

import com.gmail.viktordudal.library.exception.ResourceNotFoundException;
import com.gmail.viktordudal.library.model.Author;
import com.gmail.viktordudal.library.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthorService authorService;


    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/all")
    public List<Author> getAll() {
        logger.warn("Test Author controller!");
        return authorService.findAll();
    }

    @PutMapping(value = "/update")
    public Author update(@RequestBody Author author) {
        return authorService.update(author);
    }

    @PostMapping(value = "/create")
    public Author create(@RequestBody Author author) {
        return authorService.create(author);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        authorService.deleteById(id);
    }

    @GetMapping(value = "/byId/{authorId}")
    public Author getAuthorById(@PathVariable("authorId") Long authorId) {
        return authorService.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + authorId));
    }
}
