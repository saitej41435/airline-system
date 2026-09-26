package com.alpha.locationservices.exceptions;

public class CityAlreadyExistsException extends RuntimeException{
    public CityAlreadyExistsException(String message){
        super(message);
    }
}
