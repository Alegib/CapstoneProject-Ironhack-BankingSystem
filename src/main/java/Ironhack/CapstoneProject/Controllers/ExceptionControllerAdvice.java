package Ironhack.CapstoneProject.Controllers;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityException(DataIntegrityViolationException exception) {
        String exceptionMessage = exception.getMostSpecificCause().getMessage();
        String responseMessage = exceptionMessage.substring(exceptionMessage.indexOf("'") + 1);
        responseMessage = responseMessage.substring(0, responseMessage.indexOf("'"));
        HashMap<String, String> errorMap = new HashMap<>();
        errorMap.put("Invalid insertion: ", responseMessage + " already exists");
        System.err.println(exceptionMessage);

        return new ResponseEntity<>(errorMap, HttpStatus.CONFLICT);
    }

}
