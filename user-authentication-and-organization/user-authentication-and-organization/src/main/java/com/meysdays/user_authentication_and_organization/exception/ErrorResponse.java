package com.meysdays.user_authentication_and_organization.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ErrorResponse {
    private List<ErrorDetail> errors;

    public ErrorResponse(List<ErrorDetail> errors){
        this.errors = errors;
    }

    public List<ErrorDetail> getErrors(){
        return errors;
    }

    public void setErrors(List<ErrorDetail> errors){
        this.errors = errors;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class ErrorDetail{
        private String field;
        private String message;
    }
}
