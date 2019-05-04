package org.ksql.script.exception;

public class TypeErrorException extends ErrorException {
    public TypeErrorException(String desc) {
        super(desc);
    }
}
