package com.fx.javafx.repository;

import com.fx.javafx.model.BookModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.util.List;

@Component
public class BookSpecifications {

    public Specification<BookModel> titleContains(String title){
        return (root,query,criteriaBuilder) ->
                criteriaBuilder.like(root.get("bookTitle"), "%" + title + "%");
    }

    public Specification<BookModel> authorContains(String author){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("bookAuthor"),"%" + author + "%");
    }

    public Specification<BookModel> publicationYearContains (int year){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("yearOfPublication"), year);
    }

    public Specification<BookModel> genreContains(List<String> genre){
        return (root, query, criteriaBuilder) ->
                root.get("bookGenre").in(genre);
    }

    public Specification<BookModel> bookStateContains(String state){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("bookState"), state);
    }
}
