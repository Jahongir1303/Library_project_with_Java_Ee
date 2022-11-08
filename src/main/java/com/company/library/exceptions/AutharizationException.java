package com.company.library.exceptions;

import javax.servlet.ServletException;

public class AutharizationException extends ServletException {

    public AutharizationException(String message) {
        super(message);
    }
}
