package org.ksql.script.templete.node;

import org.ksql.script.templete.ResultsCollective;
import org.ksql.script.templete.SqlNodeType;
import org.ksql.script.exception.ErrorException;
import org.mirror.reflection.mirror.MirrorObject;

import java.util.List;

public interface SqlNodeTemplete {
    StringBuffer toSqlString(MirrorObject value);

    void setNext(SqlNodeTemplete next);

    List<Object> toSqlParams(MirrorObject value) throws ErrorException;

    SqlNodeTemplete next();

    SqlNodeType getNodeType();
}
