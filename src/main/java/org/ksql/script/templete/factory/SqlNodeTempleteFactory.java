package org.ksql.script.templete.factory;

import org.ksql.script.templete.SqlNodeType;
import org.ksql.script.templete.node.SqlNodeTemplete;

public interface SqlNodeTempleteFactory {
    SqlNodeTemplete create(String nodeString, Object value, SqlNodeType type);
}
