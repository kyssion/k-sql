package org.ksql.script.templete.engine;


import org.ksql.script.exception.SqlNodeListIsNullException;
import org.ksql.script.templete.ResultsCollective;
import org.ksql.script.templete.SqlTemplete;
import org.ksql.script.templete.node.SqlNode;
import org.mirror.reflection.mirror.MirrorObject;

import java.util.List;

public class DefaultSqlTempeteEngine implements SqlTempleteEngine {

    public SqlTemplete createByObject(String baseSql) {
        //解析baseSql形成sqlList数组
        return new DefaultSqlTempete();
    }

    @Override
    public SqlTemplete create(String baseSql) {
        return this.createByObject(baseSql);
    }


    public class DefaultSqlTempete implements SqlTemplete {
        private List<SqlNode> sqlNodeList;

        public void setSqlNodeList(List<SqlNode> sqlNodeList) {
            this.sqlNodeList = sqlNodeList;
        }

        @Override
        public ResultsCollective createSql(Object params) throws SqlNodeListIsNullException {
            if (this.sqlNodeList == null) {
                throw new SqlNodeListIsNullException(this.getClass());
            }
            return ResultsCollective.createResultsCollective(this.sqlNodeList, MirrorObject.forObject(params));
        }
    }
}
