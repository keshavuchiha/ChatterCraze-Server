package com.chattercraze.Server.configs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ControllerException extends Exception{
    private String message = "Unanticipated Error";
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public static ResponseEntity<Object> throwGenericError(){
        return new ResponseEntity<>("Unanticipated Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
