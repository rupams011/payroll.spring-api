package com.payroll.springapi.exceptions;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RestControllerAdvice
public class PayrollExceptionHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    public Map<String, String> NoResourceFoundExceptionHandler(NoResourceFoundException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("type", "NoResourceFoundException");
        errorMap.put("localizedMessage", e.getLocalizedMessage());
//        errorMap.put("body", e.getBody().toString());
        errorMap.put("httpMethod", e.getHttpMethod().toString());
        errorMap.put("statusCode", e.getStatusCode().toString());
        errorMap.put("timeStamp", LocalDateTime.now().toString());
        return errorMap;
    }

    @ExceptionHandler(NullPointerException.class)
    public Map<String, String> NullPointerExceptionHandler(NullPointerException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("type", "NullPointerException");
        errorMap.put("localizedMessage", e.getLocalizedMessage());
        errorMap.put("timeStamp", LocalDateTime.now().toString());
        errorMap.put("probableSolution", "Incorrect identifier provided for the required resource");
        return errorMap;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Map<String, String> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("type", "MethodArgumentTypeMismatchException");
        errorMap.put("timeStamp", LocalDateTime.now().toString());
        errorMap.put("localizedMessage", e.getLocalizedMessage());
        errorMap.put("errorCode", e.getErrorCode());
        errorMap.put("probableSolution", "Please enter " + e.getRequiredType() + " type of value for " + e.getPropertyName() + " instead of " + e.getValue().toString());
        return errorMap;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Map<String, String> HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("type", "MethodArgumentTypeMismatchException");
        errorMap.put("timeStamp", LocalDateTime.now().toString());
        errorMap.put("localizedMessage", e.getLocalizedMessage());
        errorMap.put("httpStatusCode", e.getStatusCode().toString());
        errorMap.put("probableSolution", "Please use http method(s) " + e.getSupportedHttpMethods().toString() + " instead of " + e.getMethod());
        return errorMap;
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public Map<String, String> InternalAuthenticationServiceExceptionHandler(InternalAuthenticationServiceException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("type", "InternalAuthenticationServiceException");
        errorMap.put("timeStamp", LocalDateTime.now().toString());
        errorMap.put("localizedMessage", e.getLocalizedMessage());
        errorMap.put("probableSolution", "Check if the Database server is running.");
        return errorMap;
    }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public Map<String, String> DataAccessResourceFailureExceptionHandler(DataAccessResourceFailureException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("type", "DataAccessResourceFailureException");
        errorMap.put("timeStamp", LocalDateTime.now().toString());
        errorMap.put("localizedMessage", e.getLocalizedMessage());
        return errorMap;
    }

    /*@ExceptionHandler(PSQLException.clss)
    public Map<String, String> PSQLExceptionHandler(PSQLException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("type", "PSQLException");
        errorMap.put("timeStamp", LocalDateTime.now().toString());
        errorMap.put("localizedMessage", e.getLocalizedMessage());
        errorMap.put("httpStatusCode", e.getStatusCode().toString());
        errorMap.put("probableSolution", "Please use http method(s) " + e.getSupportedHttpMethods().toString() + " instead of " + e.getMethod());
        return errorMap;
    }*/


    /*@ExceptionHandler(SignatureException.class)
    public Map<String, String> SignatureExceptionHandler(SignatureException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("type", "MethodArgumentTypeMismatchException");
        errorMap.put("timeStamp", LocalDateTime.now().toString());
        errorMap.put("localizedMessage", e.getLocalizedMessage());
//        errorMap.put("errorCode", e.getErrorCode());
//        errorMap.put("probableSolution", "Please enter " + e.getRequiredType() + " type of value for " + e.getPropertyName() + " instead of " + e.getValue().toString());
        System.out.println("error ===> " + e);
        return errorMap;
    }*/

    /*@ExceptionHandler(ResourceAlreadyExistException.class)
    public Map<String, String> ResourceAlreadyExistExceptionHandler(ResourceAlreadyExistException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("type", "ResourceAlreadyExistException");
        errorMap.put("timeStamp", LocalDateTime.now().toString());
        errorMap.put("localizedMessage", e.getLocalizedMessage());
        return errorMap;
    }*/
}
