package com.gmail.viktordudal.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorId;

    @Column(nullable = false, length = 25)
    @NotNull(message = "Author name must be not null")
    @NotBlank(message = "Author name must be not blank")
    @Size(min = 3, max = 25, message = "Author name length is incorrect")
    @Pattern(regexp = "^[A-Za-z0-9]{3,25}$")
    private String name;

    @Column(nullable = false, length = 25)
    @NotNull(message = "Author surname must be not null")
    @NotBlank(message = "Author surname must be not blank")
    @Size(min = 2, max = 25, message = "Author surname length is incorrect")
    @Pattern(regexp = "^[A-Za-z0-9]{2,25}$")
    private String surname;

    @OneToMany(mappedBy = "authorNikName", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Book> books;
}
