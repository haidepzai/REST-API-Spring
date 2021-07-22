package de.oette.lecture.A05;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class CustomAccessDeniedException extends AccessDeniedException {

    public CustomAccessDeniedException(String msg) {
        super(msg);
    }
}