package org.ksql.script.exception;

public class ErrorException extends RuntimeException {
    public ErrorException(String desc){
        super(desc);
    }
}
