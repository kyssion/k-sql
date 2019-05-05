package org.ksql.script.exception;

import org.mirror.reflection.agent.MethodAgent;

public class NoSqlInfoError extends ErrorException {

    public NoSqlInfoError(MethodAgent methodAgent){
        this("the method : "+methodAgent.getMethodName()+
                " don't have sql information");
    }

    private NoSqlInfoError(String desc) {
        super(desc);
    }
}
