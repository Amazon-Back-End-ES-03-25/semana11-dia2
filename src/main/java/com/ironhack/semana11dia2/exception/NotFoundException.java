package com.ironhack.semana11dia2.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String item) {
        super(item + " not found");
    }
}
