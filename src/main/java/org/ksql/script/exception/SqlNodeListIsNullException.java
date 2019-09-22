package org.ksql.script.exception;

import org.ksql.script.templete.SqlTemplete;

/**
 *Sql link node is a null exception
 */
public class SqlNodeListIsNullException extends BaseException{

    public SqlNodeListIsNullException(Class<? extends SqlTemplete> item){
        this(item.getName()+":the sqlNodeList couldn't null");
    }

    public SqlNodeListIsNullException(String desc) {
        super(desc);
    }
}
