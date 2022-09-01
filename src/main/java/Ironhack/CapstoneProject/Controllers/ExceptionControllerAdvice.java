package Ironhack.CapstoneProject.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.logging.Logger;

@RestControllerAdvice
public class ExceptionController {

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
