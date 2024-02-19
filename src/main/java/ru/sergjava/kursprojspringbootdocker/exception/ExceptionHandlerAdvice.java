package ru.sergjava.kursprojspringbootdocker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sergjava.kursprojspringbootdocker.util.Logger;

import java.io.IOException;
import java.util.UUID;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    public ExceptionHandlerAdvice(Logger logger) {
        this.logger = logger;
    }

    private Logger logger;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomException> badRequestHandler(HttpMessageNotReadableException e) throws IOException {
        CustomException customException = new CustomException(e.getMessage(), UUID.randomUUID());
        logger.writeLog(e.getMessage() + "\n");
        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomException> internalServerErrorHandler(RuntimeException e) throws IOException {
        CustomException customException = new CustomException(e.getMessage(), UUID.randomUUID());
        logger.writeLog(e.getMessage() + "\n");
        return new ResponseEntity<>(customException, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
