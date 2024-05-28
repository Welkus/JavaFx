package com.fx.javafx.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "book_author")
    private String bookAuthor;
    @Column(name = "year_of_publication")
    private int yearOfPublication;
    @Column(name = "genre")
    private String bookGenre;
    @Column(name = "book_state")
    private String bookState;

    @OneToMany(mappedBy = "bookModel", fetch = FetchType.EAGER)
    Set<CheckedOutModel> users = new HashSet<>();
}
