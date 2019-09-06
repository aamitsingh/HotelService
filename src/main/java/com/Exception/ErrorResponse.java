package com.Exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

/**
 * Created by amitsingh on 27/06/19.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private HttpStatus status;
    private String message;
    private String url;

    ErrorResponse(HttpStatus status, String message, String url) {
        this.status = status;
        this.message = message;
        this.url = url;
    }
}
