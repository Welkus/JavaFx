package com.fx.javafx.service;

import com.fx.javafx.model.BookModel;
import com.fx.javafx.model.CheckedOutId;
import com.fx.javafx.model.CheckedOutModel;
import com.fx.javafx.model.UserModel;
import com.fx.javafx.repository.CheckedOutRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    private CheckedOutRepository checkedOutRepository;

    public void checkOutBook(UserModel userModel, BookModel bookModel) {
        CheckedOutId checkedOutId = new CheckedOutId();
        checkedOutId.setBookId(bookModel.getBookId());
        checkedOutId.setUserId(userModel.getUserId());

        checkedOutRepository.save(CheckedOutModel.builder()
                .checkedOutId(checkedOutId)
                .userModel(userModel)
                .bookModel(bookModel)
                .checkOutDate(LocalDateTime.now())
                .returnDate(LocalDateTime.now().plusMonths(1))
                .isReturned(false)
                .build());
    }

    public List<CheckedOutModel> getCheckOuts(UserModel userModel) {
        return checkedOutRepository.findAllByUserModelUserId(userModel.getUserId());
    }
}
