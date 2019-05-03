package org.ksql.script.templete.node;

import org.ksql.script.templete.SqlNodeType;
import org.ksql.script.exception.ErrorException;

import java.util.List;

public interface SqlNode {
    StringBuffer toSqlString();

    List<Object> toSqlParams(Object value) throws ErrorException;

    SqlNode next();

    SqlNodeType getNodeType();
}