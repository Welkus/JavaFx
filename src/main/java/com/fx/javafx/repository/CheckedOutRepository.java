package com.fx.javafx.repository;

import com.fx.javafx.model.CheckedOutModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckedOutRepository extends JpaRepository<CheckedOutModel,Long> {
    List<CheckedOutModel> findAllByUserModelUserId(long userId);
}
