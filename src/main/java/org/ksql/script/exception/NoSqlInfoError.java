package org.ksql.script.exception;

public class NoSqlInfoError extends ErrorException {
    public NoSqlInfoError(String desc) {
        super(desc);
    }
}
