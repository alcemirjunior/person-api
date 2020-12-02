package jr.alcemir.personAPI.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoungException extends Exception {
    public PersonNotFoungException(Long id) {
        super("Person not found with id " + id);
    }
}
