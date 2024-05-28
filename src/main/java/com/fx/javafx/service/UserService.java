package com.fx.javafx.service;

import com.fx.javafx.model.BookModel;
import com.fx.javafx.model.CheckedOutModel;
import com.fx.javafx.repository.CheckedOutRepository;
import com.fx.javafx.ui.ErrorMessage;
import com.fx.javafx.model.UserModel;
import com.fx.javafx.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CheckedOutRepository checkedOutRepository;

    public UserService(UserRepository userRepository, CheckedOutRepository checkedOutRepository) {
        this.userRepository = userRepository;
        this.checkedOutRepository = checkedOutRepository;
    }


    public UserModel checkCredentials(UserModel userModel) {

        UserModel lookingForUserModel;
        Optional<UserModel> found = userRepository.findByUsername(userModel.getUsername());
        lookingForUserModel = found.stream().findFirst().orElse(null);
        System.out.println(lookingForUserModel.getFirstName());

        if (lookingForUserModel != null && lookingForUserModel.getUserPassword().equals(userModel.getUserPassword())) {
            return lookingForUserModel;
        }

        return null;
    }


    public ErrorMessage registrationRequest(UserModel userModel) {
        if (userRepository.findByUsername(userModel.getUsername()).isPresent()) {
            return ErrorMessage.USERNAME_EXISTS;
        }
        if (!isValidateUserName(userModel.getUsername())) {
            return ErrorMessage.INVALID_USERNAME;
        }
        if (!isValidPassword(userModel.getUserPassword())) {
            return ErrorMessage.INVALID_PASSWORD;
        }
        if (!isValidEmail(userModel.getEmail())) {
            return ErrorMessage.INVALID_EMAIL;
        }
        userRepository.save(userModel);

        return ErrorMessage.SUCCESS;
    }

    private boolean isValidateUserName(String userName) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(userName);
        return matcher.find();
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9!@#$%^&*]+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9@.]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }






}
