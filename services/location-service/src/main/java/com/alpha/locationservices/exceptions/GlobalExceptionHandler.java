package com.alpha.locationservices.exceptions;

import com.alpha.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public

    @ExceptionHandler(CityAlreadyExistsException.class)
    public ApiResponse handleCityAlreadyExists(CityAlreadyExistsException ex){
        return new ApiResponse(ex.getMessage());
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ApiResponse handleCityNotFound(CityNotFoundException ex){
        return new ApiResponse(ex.getMessage());
    }

    @ExceptionHandler(IataCodeAlreadyExistsException.class)
    public ApiResponse handleIataCodeAlreadyExists(IataCodeAlreadyExistsException ex){
        return new ApiResponse(ex.getMessage());
    }
}
