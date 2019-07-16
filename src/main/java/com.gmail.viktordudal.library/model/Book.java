package com.gmail.viktordudal.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long bookId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 15)
    private String author;

    @Column(nullable = false, length = 15)
    private String genre;

    @Column(name = "publication", nullable = false, length = 4)
    private int publicationDate;
}
