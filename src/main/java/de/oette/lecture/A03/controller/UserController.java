package de.oette.lecture.A03.controller;

import de.oette.lecture.A03.controller.dto.ErrorDto;
import de.oette.lecture.A03.controller.dto.UserDto;
import de.oette.lecture.A03.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/{id}")
    public UserDto getById(@PathVariable(value = "id") long id) {

        if (id == 1) {
            UserDto userDto = new UserDto();
            userDto.name = "Max";

            return userDto;
        }
        if (id == 7) {
            throw new IllegalArgumentException("Id 7 not allowed");
            //Ruft allgemeinen Exception Handler auf (500)
        }
        throw new UserNotFoundException(String.format("User with id %s not found", id));
        //Ruft ExceptionHandler unten auf (404)
    }

    //@ExceptionHandler fängt die definierten Exceptions innerhalb des Controllers
    @ExceptionHandler(value = UserNotFoundException.class) //reagiert auf UserNotFoundException
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(UserNotFoundException ex) {
        return new ErrorDto(ex.getMessage());
    }
}
