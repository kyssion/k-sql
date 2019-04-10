package org.ksql.script.fatory;


import org.ksql.script.bo.ReturnParam;
import org.ksql.script.conversion.TypeConversion;

public class DefaultTypeConversionFactory implements TypeConversionFactory {

    @Override
    public <S> TypeConversion createTypeConversion(ReturnParam returnParam, S s) {
        return null;
    }
}
