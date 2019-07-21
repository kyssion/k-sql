package org.ksql.script.exception;

import org.ksql.script.templete.SqlTemplete;

public class SqlNodeListIsNullException extends BaseException{

    public SqlNodeListIsNullException(Class<? extends SqlTemplete> item){
        this(item.getName()+":the sqlNodeList couldn't null");
    }

    public SqlNodeListIsNullException(String desc) {
        super(desc);
    }
}
