package com.alpha.locationservices.exceptions;

public class IataCodeAlreadyExistsException extends RuntimeException{
    public IataCodeAlreadyExistsException(String message){
        super(message);
    }
}
