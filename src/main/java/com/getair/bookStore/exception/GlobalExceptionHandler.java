package com.getair.bookStore.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookStoreException.class)
    public final ResponseEntity<BookStoreException> handleCustomerApiException(BookStoreException ex,
                                                                                      WebRequest request) {
        return prepareResponse(ex);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Exception> handleException(
            Exception ex) {
        return ResponseEntity.status(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getHttpStatus()).body(ex);
    }

    private static ResponseEntity<BookStoreException> prepareResponse(BookStoreException exception) {
        return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(exception);
    }
}
