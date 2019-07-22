package com.gmail.viktordudal.library.controller;

import com.gmail.viktordudal.library.exception.ResourceNotFoundException;
import com.gmail.viktordudal.library.model.Author;
import com.gmail.viktordudal.library.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/all")
    public List<Author> getAll() {
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
