package com.meysdays.user_authentication_and_organization.exception;

import com.meysdays.user_authentication_and_organization.reponse.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex){
//        public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex){

            List<ErrorResponse.ErrorDetail> errors = new ArrayList<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            errors.add(new ErrorResponse.ErrorDetail(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        ErrorResponse errorResponse = new ErrorResponse(errors);
//        return errorResponse;
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
//        public ErrorResponse handleCustomException(CustomException ex) {

            ErrorResponse errorResponse = new ErrorResponse(ex.getErrors());
//            return errorResponse;
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Response> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        Response errorResponse = new Response("error", e.getMessage(), "400");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
