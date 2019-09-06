package com.Exception;

/**
 * Created by amitsingh on 27/06/19.
 */
public class InvalidArgumentException extends Exception {

    private String cause;

    public InvalidArgumentException(String cause) {
        this.cause = cause;
    }

}
