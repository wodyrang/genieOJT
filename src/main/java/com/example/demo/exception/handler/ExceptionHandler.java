package com.example.demo.exception.handler;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.DataValidationException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.data.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wody8674@gmail.com on 2020/02/03.
 *
 * 예외처리.
 */
@Slf4j
@RestController
@ControllerAdvice
public class ExceptionHandler {


    /**
     * Not found 처리
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<ApiResult<Void>> notFound(final HttpServletRequest request, final Exception e) {
        if (log.isErrorEnabled()) {
            log.error(e.getMessage(), e);
        }
        final ApiResult<Void> result = new ApiResult<Void>(e, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }


    /**
     * Bad Request 처리.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ApiResult<Void>> badRequest(final HttpServletRequest request, final Exception e) {
        if (log.isErrorEnabled()) {
            log.error(e.getMessage(), e);
        }
        final ApiResult<Void> result = new ApiResult<Void>(e, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }


    /**
     * Data validation 오류 처리
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {DataValidationException.class})
    public ResponseEntity<ApiResult<Void>> dataValidation(final HttpServletRequest request, final DataValidationException e) {
        if (log.isErrorEnabled()) {
            log.error(e.getMessage(), e);
        }

        final ApiResult<Void> result =
                new ApiResult<>(HttpStatus.BAD_REQUEST, this.toBingingResultString(e.getBindingResult()), null
                        , this.toBindingResultErrorMap(e.getBindingResult()));

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }


    /**
     * Exception 공통 예외 처리.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<Void>> exception(final HttpServletRequest request, final Exception e, final BindingResult errors) {
        if (log.isErrorEnabled()) {
            log.error(e.getMessage(), e);
        }

        ApiResult<Void> result;
        if (errors.hasErrors()) {
            result = new ApiResult<>(HttpStatus.BAD_REQUEST, this.toBingingResultString(errors), null
                    , this.toBindingResultErrorMap(errors));
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            result = new ApiResult<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Validation Default message Convertor.
     * @param bindingResult 오류 내역.
     * @return 메시지.
     */
    private String toBingingResultString(final BindingResult bindingResult) {
        if (bindingResult == null) {
            return "";
        }
        if (!bindingResult.hasErrors()) {
            return "";
        }

        final StringBuilder errorsBuilder = new StringBuilder();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            errorsBuilder.append("[").append(fieldError.getField()).append("] ");
            errorsBuilder.append(fieldError.getDefaultMessage());
            errorsBuilder.append("\n");
        });

        return errorsBuilder.toString();
    }


    /**
     * Validation Json result.
     * @param bindingResult 오류 내역
     * @return json message
     */
    private Map<String, List<String>> toBindingResultErrorMap(final BindingResult bindingResult) {
        final Map<String, List<String>> resultMap = new HashMap<>();

        if (bindingResult == null) {
            return resultMap;
        }
        if (!bindingResult.hasErrors()) {
            return resultMap;
        }

        bindingResult.getFieldErrors().forEach(fieldError -> {
            final String field = fieldError.getField();
            final List<String> messageList = resultMap.computeIfAbsent(field, k -> new ArrayList<>());

            messageList.add(fieldError.getDefaultMessage());
        });

        return resultMap;
    }
}
