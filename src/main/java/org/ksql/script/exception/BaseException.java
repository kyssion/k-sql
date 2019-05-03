package org.ksql.script.exception;

public class BaseException extends Exception {
    public BaseException(String desc){
        super(desc);
    }
}
