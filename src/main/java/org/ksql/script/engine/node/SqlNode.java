package org.ksql.script.engine.node;

import org.ksql.script.exception.ErrorException;

import java.util.List;

public interface SqlNode {
    StringBuffer toSqlString();
    List<Object>  toSqlParams() throws ErrorException;
    SqlNode next();
}
