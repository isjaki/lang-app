package com.langapp.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleMethodArgumentNotValidException(HttpServletRequest req, HttpServletResponse res, MethodArgumentNotValidException e) {
        return buildResponse(req, res, e, HttpStatus.BAD_REQUEST.value());
    }

    public Map<String, Object> buildResponse(HttpServletRequest req, HttpServletResponse res, Exception e) {
        return buildResponse(req, res, e, null);
    }

    public Map<String, Object> buildResponse(HttpServletRequest req, HttpServletResponse res, Exception e, Integer status) {
        Map<String, Object> errorMap = new HashMap<>();

        if (status != null) {
            res.setStatus(status);
            errorMap.put("status", status);
        } else {
            int httpStatus = getHttpStatus(e).value();
            errorMap.put("status", httpStatus);
            res.setStatus(httpStatus);
        }

        errorMap.put("error", e.toString());
        errorMap.put("path", req.getRequestURL());
        errorMap.put("message", e.getMessage());
        errorMap.put("timestamp", System.currentTimeMillis());

        return errorMap;
    }

    public HttpStatus getHttpStatus(Exception e) {
        Class<? extends Exception> exceptionClass = e.getClass();
        ResponseStatus responseStatus = exceptionClass.getAnnotation(ResponseStatus.class);

        if (responseStatus != null) {
            return responseStatus.value();
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
