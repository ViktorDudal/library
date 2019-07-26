package com.gmail.viktordudal.library.unittest.controllers;

import com.gmail.viktordudal.library.controller.AuthorController;
import com.gmail.viktordudal.library.exception.ResourceNotFoundException;
import com.gmail.viktordudal.library.model.Author;
import com.gmail.viktordudal.library.service.AuthorService;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AuthorController.class)
@PowerMockIgnore("javax.management.*")
public class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    private AuthorController controller;

    private static final Long AUTHOR_ID = 1L;

    @Before
    public void setUp() {
        this.controller = new AuthorController(authorService);
    }

    @Test
    public void getAll() {
        Author mockAuthor1 = Author.builder()
                .authorId(1L)
                .name("Stiven")
                .build();
        Author mockAuthor2 = Author.builder()
                .authorId(2L)
                .name("Ronald")
                .build();
        List<Author> mockAuthors = new LinkedList<>();
        mockAuthors.add(mockAuthor1);
        mockAuthors.add(mockAuthor2);

        when(authorService.findAll()).thenReturn(mockAuthors);
        List<Author> allAuthors = controller.getAll();

        assertEquals(mockAuthors, allAuthors);

        verify(authorService, times(1)).findAll();
        verifyNoMoreInteractions(authorService);
    }

    @Test
    public void update() {
        Author mockAuthor = Author.builder()
                .authorId(1L)
                .name("Ronald")
                .build();

        when(authorService.update(any(Author.class))).thenReturn(mockAuthor);
        Author updatedAuthor = controller.update(new Author());

        assertEquals(mockAuthor, updatedAuthor);

        verify(authorService, times(1)).update(any(Author.class));
        verifyNoMoreInteractions(authorService);
    }

    @Test
    public void create() {
        Author mockAuthor = Author.builder()
                .authorId(1L)
                .name("Ronald")
                .build();

        when(authorService.create(any(Author.class))).thenReturn(mockAuthor);
        Author createdAuthor = controller.create(new Author());

        assertEquals(mockAuthor, createdAuthor);

        verify(authorService, times(1)).create(any(Author.class));
        verifyNoMoreInteractions(authorService);
    }

    @Test
    public void delete() {
        controller.delete(AUTHOR_ID);

        verify(authorService, times(1)).deleteById(eq(AUTHOR_ID));
        verifyNoMoreInteractions(authorService);
    }

    @Test
    public void getAuthorById() {
        Author mockAuthor = Author.builder()
                .authorId(1L)
                .name("Ronald")
                .build();

        when(authorService.findById(eq(AUTHOR_ID))).thenReturn(Optional.of(mockAuthor));
        Author authorById = controller.getAuthorById(AUTHOR_ID);

        assertEquals(mockAuthor, authorById);

        verify(authorService, times(1)).findById(eq(AUTHOR_ID));
        verifyNoMoreInteractions(authorService);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getAuthorByIdNotFound() {
        when(authorService.findById(eq(AUTHOR_ID))).thenThrow(new ResourceNotFoundException("Author not found with id " + AUTHOR_ID));

        controller.getAuthorById(AUTHOR_ID);

        verify(authorService, times(1)).findById(eq(AUTHOR_ID));
        verifyNoMoreInteractions(authorService);
    }
}
