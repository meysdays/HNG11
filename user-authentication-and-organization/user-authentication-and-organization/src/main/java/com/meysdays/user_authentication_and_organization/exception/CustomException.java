package com.meysdays.user_authentication_and_organization.exception;

import java.util.List;

public class CustomException extends RuntimeException{

    private List<ErrorResponse.ErrorDetail> errors;

    public CustomException(List<ErrorResponse.ErrorDetail> errors) {
        this.errors = errors;
    }

    public List<ErrorResponse.ErrorDetail> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorResponse.ErrorDetail> errors) {
        this.errors = errors;
    }
}
