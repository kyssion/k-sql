package org.ksql.script.templete.factory;

import org.ksql.script.templete.SqlNodeType;
import org.ksql.script.templete.node.SqlNode;

public interface SqlNodeFactory {
    SqlNode create(String nodeString, Object value, SqlNodeType type);
}
