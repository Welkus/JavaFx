package com.fx.javafx.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkdout")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckedOutModel {

    @EmbeddedId
    private CheckedOutId checkedOutId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private BookModel bookModel;

    @Column(name = "checkoutdate")
    private LocalDateTime checkOutDate;

    @Column(name = "returndate")
    private LocalDateTime returnDate;

    @Column(name = "isreturned")
    private boolean isReturned;
}
