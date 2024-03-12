package com.mukesh.grocery.exceptions;

public class ProductNotAvailable extends RuntimeException {
    public ProductNotAvailable(String format) {
        super(format);
    }
}
