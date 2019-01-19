package ru.flametaichou.quest.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.flametaichou.quest.core.dto.ErrorInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private ApplicationContext applicationContext;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public ErrorInfo handle(IllegalArgumentException e) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(e.getMessage());

        return errorInfo;
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseBody
    public ErrorInfo handle(AccessDeniedException e, Locale locale) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(applicationContext.getMessage("error.403", null, locale));
        return errorInfo;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public ErrorInfo handle(Throwable e, HttpServletRequest request) {
        logger.error(request.getQueryString(), e);

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(e.getMessage());

        return errorInfo;
    }
}
