package com.gmail.viktordudal.library.unittest.services;

import com.gmail.viktordudal.library.dao.AuthorDao;
import com.gmail.viktordudal.library.model.Author;
import com.gmail.viktordudal.library.service.AuthorService;
import com.gmail.viktordudal.library.service.impl.AuthorServiceImpl;
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
public class AuthorServiceTest {

    @Mock
    private AuthorDao authorDao;

    private AuthorService service;

    private final static long ID =2;

    @Before
    public void setUp(){
        this.service = new AuthorServiceImpl(authorDao);
    }

    @Test
    public void findById() {
        Author mockAuthor = Author.builder()
                .authorId(ID)
                .build();

        when(authorDao.findById(eq(ID))).thenReturn(Optional.of(mockAuthor));

        Optional<Author> authorById = service.findById(ID);

        assertEquals(Optional.of(mockAuthor), authorById);

        verify(authorDao, times(1)).findById(eq(ID));
        verifyNoMoreInteractions(authorDao);
    }

    @Test
    public void findAll() {
        List<Author> mockAuthors = new ArrayList<>();
        when(authorDao.findAll()).thenReturn(mockAuthors);

        List<Author> allAuthors = service.findAll();

        assertEquals(mockAuthors, allAuthors);

        verify(authorDao, times(1)).findAll();
        verifyNoMoreInteractions(authorDao);
    }

    @Test
    public void create() {
        Author mockAuthor = Author.builder()
                .authorId(4L)
                .build();

        when(authorDao.create(any(Author.class))).thenReturn(mockAuthor);
        Author createdAuthor = service.create(new Author());

        assertEquals(mockAuthor, createdAuthor);

        verify(authorDao, times(1)).create(any(Author.class));
        verifyNoMoreInteractions(authorDao);
    }

    @Test
    public void update() {
        Author mockAuthor = Author.builder()
                .authorId(4L)
                .build();

        when(authorDao.update(any(Author.class))).thenReturn(mockAuthor);
        Author updatedAuthor = service.update(new Author());

        assertEquals(mockAuthor, updatedAuthor);

        verify(authorDao, times(1)).update(any(Author.class));
        verifyNoMoreInteractions(authorDao);
    }

    @Test
    public void deleteById() {
        service.deleteById(ID);

        verify(authorDao, times(1)).deleteById(eq(ID));
        verifyNoMoreInteractions(authorDao);
    }
}
