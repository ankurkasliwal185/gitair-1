package com.getair.bookStore.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookStoreException extends RuntimeException{

    private final ErrorCodeEnum errorCode;

    public BookStoreException(ErrorCodeEnum errorCode) {
        super();
        this.errorCode = errorCode;
    }
}
