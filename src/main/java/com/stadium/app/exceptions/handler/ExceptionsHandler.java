package com.stadium.app.exceptions.handler;

import com.stadium.app.exceptions.BadParamsException;
import com.stadium.app.responses.IResponse;
import com.stadium.app.responses.Impl.DefaultErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler({BadParamsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<IResponse> handleAndReturnBadRequest(Exception e) {
        return new ResponseEntity<>(new DefaultErrorResponse(Arrays.toString(e.getStackTrace())),
                HttpStatus.BAD_REQUEST);
    }

}
