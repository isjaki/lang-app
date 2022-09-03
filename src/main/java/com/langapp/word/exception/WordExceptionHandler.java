package com.langapp.word.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class WordExceptionHandler {

    @ExceptionHandler(WordNotFoundException.class)
    public Map<String, Object> handleWordNotFoundException(HttpServletRequest req, HttpServletResponse res, Exception e) {
        int httpStatus = getHttpStatus(e).value();
        res.setStatus(httpStatus);

        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", e.toString());
        errorMap.put("path", req.getRequestURL());
        errorMap.put("message", e.getMessage());
        errorMap.put("status", httpStatus);
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
