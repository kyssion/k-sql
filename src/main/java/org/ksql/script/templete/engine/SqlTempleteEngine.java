package org.ksql.script.templete.engine;

import org.ksql.script.templete.SqlTemplete;

public interface SqlTempleteEngine {
    SqlTemplete create(String baseSql);
}
