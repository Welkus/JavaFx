package com.fx.javafx.ui;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    USERNAME_EXISTS("User name already exists"),
    INVALID_USERNAME("User name can only contain lowercase cases, uppercase cases, and digits 0-9"),
    INVALID_PASSWORD("Password doesn't match the requirements"),
    INVALID_EMAIL("Email contains illegal symbols"),
    SUCCESS("User successfully created");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
