package org.ksql.script.exception;

import org.ksql.script.templete.SqlTemplete;

public class SqlTempleteIsNullException extends BaseException{

    public SqlTempleteIsNullException(Class<? extends SqlTemplete> item){
        this(item.getName()+":the sqlTemplete sqlNode couldn't null");
    }

    public SqlTempleteIsNullException(String desc) {
        super(desc);
    }
}
