package org.ksql.script.templete.engine;


import org.ksql.script.exception.SqlNodeListIsNullException;
import org.ksql.script.templete.ResultsCollective;
import org.ksql.script.templete.SqlNodeType;
import org.ksql.script.templete.SqlTemplete;
import org.ksql.script.templete.node.*;
import org.mirror.reflection.mirror.MirrorObject;

import java.util.ArrayList;
import java.util.List;

public class DefaultSqlTempeteEngine implements SqlTempleteEngine {

    public SqlTemplete createByObject(String baseSql) {
        baseSql.replaceAll("\n", "");
        char[] sqlCharArr = baseSql.toCharArray();
        SqlNodeType beforeSqlType = SqlNodeType.Base_Node;
        List<SqlNode> list = new ArrayList<>();
        //: $ #
        int start = 0;
        for (int a = 0; a < sqlCharArr.length; a++) {
            if (sqlCharArr[a] == ':') {
                list.add(new StringSqlNode(baseSql.substring(start, a)));
                int endIndex = findK(sqlCharArr, a);
                list.add(new ObjectSqlNode(baseSql.substring(a + 1, endIndex)));
                start = endIndex;
            } else if (sqlCharArr[a] == '$') {
                list.add(new StringSqlNode(baseSql.substring(start, a)));
                int endIndex = findK(sqlCharArr, a);
                list.add(new ListObjectSqlNode(baseSql.substring(a + 1, endIndex)));
                start = endIndex;
            } else if (sqlCharArr[a] == '#') {
                list.add(new StringSqlNode(baseSql.substring(start, a)));
                int endIndex = findK(sqlCharArr, a);
                list.add(new TemplateListSqlNode(baseSql.substring(a + 1, endIndex)));
                start = endIndex;
            }
        }
        if (start != sqlCharArr.length) {
            list.add(new StringSqlNode(baseSql.substring(start, sqlCharArr.length)));
        }
        DefaultSqlTempete defaultSqlTempete = new DefaultSqlTempete();
        defaultSqlTempete.setSqlNodeList(list);
        return defaultSqlTempete;
    }

    private int findK(char[] arr, int start) {
        while (start < arr.length) {
            if (arr[start] == ' ') {
                return start;
            }
            start++;
        }
        return start;
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
