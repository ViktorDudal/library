package com.gmail.viktordudal.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Column(name = "book_id")
    private Long bookId;

    @Column(nullable = false, length = 150)
    @NotNull(message = "Title must be not null")
    @NotBlank(message = "Title must be not blank")
    @Size(min = 2, max = 150, message = "Title length is incorrect")
    @Pattern(regexp = "^[A-Za-z0-9? A-Za-z0-9]{2,150}$")
    private String title;

    @Column(nullable = false, length = 25)
    @NotNull(message = "Author must be not null")
    @NotBlank(message = "Author must be not blank")
    @Size(min = 5, max = 25, message = "Author length is incorrect")
    @Pattern(regexp = "^[A-Za-z0-9.? A-Za-z0-9]{5,25}$")
    private String author;

    @Column(nullable = false, length = 25)
    @NotNull(message = "Genre must be not null")
    @NotBlank(message = "Genre must be not blank")
    @Size(min = 2, max = 25, message = "Genre length is incorrect")
    @Pattern(regexp = "^[A-Za-z0-9? A-Za-z0-9]{2,25}$")
    private String genre;

    @Column(name = "publication", nullable = false, length = 4)
    @NotNull(message = "Publication date must be not null")
    private int publicationDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author authorNikName;
}
