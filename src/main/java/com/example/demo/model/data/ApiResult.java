package com.example.demo.model.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by wody@genieworks.net on 2020/02/03.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {

    @JsonIgnore
    public static final String SUCCESS = "success";

    /** 결과 메타 정보 */
    private Meta meta;

    /** 응답 메시지 */
    private T body;

    /**
     * 메타 정보 Class
     */
    @Getter
    @Setter
    public class Meta {
        private String path;
        private Integer status;
        private String message;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime timestamp;
        private String trace;
        private Map<String, List<String>> errorMap;
    }


    /**
     * API Result Constructor.
     * @param httpStatus
     * @param message
     * @param body
     */
    @JsonIgnore
    public ApiResult(final HttpStatus httpStatus, final String message, final T body) {
        this.meta = new Meta();
        this.meta.setStatus(httpStatus.value());
        this.meta.setMessage(message);
        this.meta.setTimestamp(LocalDateTime.now());
        this.meta.setPath(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI());

        this.body = body;
    }

    /**
     * API Result Constructor.
     * @param httpStatus
     * @param message
     * @param body
     * @param errorMap
     */
    @JsonIgnore
    public ApiResult(final HttpStatus httpStatus, final String message, final T body, final Map<String, List<String>> errorMap) {
        this.meta = new Meta();
        this.meta.setStatus(httpStatus.value());
        this.meta.setMessage(message);
        this.meta.setTimestamp(LocalDateTime.now());
        this.meta.setPath(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI());
        this.meta.setErrorMap(errorMap);

        this.body = body;
    }

    /**
     * API Error Result Constructor.
     * @param e
     */
    @JsonIgnore
    public ApiResult(final Exception e) {
        this.meta = new Meta();
        this.meta.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.meta.setMessage(e.getMessage());
        this.meta.setTimestamp(LocalDateTime.now());
        this.meta.setPath(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI());
    }

    /**
     * API Error Result Constructor.
     * @param e
     */
    @JsonIgnore
    public ApiResult(final Exception e, final HttpStatus httpStatus) {
        this.meta = new Meta();
        this.meta.setStatus(httpStatus.value());
        this.meta.setMessage(e.getMessage());
        this.meta.setTimestamp(LocalDateTime.now());
        this.meta.setPath(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI());
    }
}
