package com.challenge.school.exceptions;

import javax.persistence.EntityNotFoundException;

public class CustomNotFoundException extends EntityNotFoundException {
    public CustomNotFoundException(String message) {
        super(message);
    }
}
