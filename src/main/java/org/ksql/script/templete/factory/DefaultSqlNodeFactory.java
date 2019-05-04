package org.ksql.script.templete.factory;

import org.ksql.script.templete.SqlNodeType;
import org.ksql.script.templete.node.SqlNodeTemplete;
import org.ksql.script.templete.node.StringSqlNodeTemplete;

public class DefaultSqlNodeFactory implements SqlNodeTempleteFactory {
    @Override
    public SqlNodeTemplete create(String nodeString, Object value, SqlNodeType type) {
        switch (type) {
            case STR_TEMP_NODE:
                return new StringSqlNodeTemplete(nodeString);
            break;
            case NULL_NODE:
                return null;
            break;
        }
    }
}
