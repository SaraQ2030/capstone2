package org.example.capstone2.API;

import lombok.AllArgsConstructor;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }

}
