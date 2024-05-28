package com.fx.javafx.service;

import com.fx.javafx.model.BookDto;
import com.fx.javafx.model.BookModel;
import com.fx.javafx.repository.BookRepository;
import com.fx.javafx.repository.BookSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookSpecifications bookSpec;

    public void saveBook(BookModel bookModel){
        bookRepository.save(bookModel);
    }

    public List<BookModel> getAllBooks(){
        return bookRepository.findAll();

    }

    public List<BookModel> searchForBook(BookDto bookDto){
        Specification<BookModel> spec = Specification.where(null);

        if(!bookDto.getBookTitle().isBlank()) spec = spec.and(bookSpec.titleContains(bookDto.getBookTitle()));
        if(!bookDto.getBookAuthor().isBlank()) spec = spec.and(bookSpec.authorContains(bookDto.getBookAuthor()));
        if(bookDto.getYearOfPublication() != 0) spec = spec.and(bookSpec.publicationYearContains(bookDto.getYearOfPublication()));
        if(!bookDto.getBookGenre().isEmpty()) spec = spec.and(bookSpec.genreContains(bookDto.getBookGenre()));
        if(!bookDto.getBookState().isEmpty()) spec = spec.and(bookSpec.bookStateContains(bookDto.getBookState()));
       return bookRepository.findAll(spec);
    }


}
