package org.ksql.script.builder;

import org.ksql.script.bo.SqlData;
import org.ksql.script.bo.SqlDataMap;

import java.util.Map;

public interface SqlDataMapBuilder {
    Map<String, SqlData> build(SqlDataMap sqlDataMap);
}
