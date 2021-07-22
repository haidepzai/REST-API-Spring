package de.oette.lecture.A03.exception;

import de.oette.lecture.A03.controller.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Allgemeine Exception Handler

@RestControllerAdvice //Zusätzlicher Inhalt, der in jedem Controller hinzugefügt wird
public class CommonExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleExceptions(Throwable ex) {
        return new ErrorDto(ex.getMessage());
    }
}
