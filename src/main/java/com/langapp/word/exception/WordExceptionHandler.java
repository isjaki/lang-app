package com.langapp.word.exception;

import com.langapp.common.exception.CommonExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestControllerAdvice
public class WordExceptionHandler extends CommonExceptionHandler {

    @ExceptionHandler(WordNotFoundException.class)
    public Map<String, Object> handleWordNotFoundException(HttpServletRequest req, HttpServletResponse res, Exception e) {
        return buildResponse(req, res, e);
    }
}
