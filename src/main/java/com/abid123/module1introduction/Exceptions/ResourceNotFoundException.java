package com.abid123.module1introduction.Exceptions;


public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(long Id) {
        super("Resource not found " + Id);
    }
}
