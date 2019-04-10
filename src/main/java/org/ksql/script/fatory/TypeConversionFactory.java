package org.ksql.script.fatory;


import org.ksql.script.bo.ReturnParam;
import org.ksql.script.conversion.TypeConversion;

public interface TypeConversionFactory {
    <S> TypeConversion createTypeConversion(ReturnParam returnParam, S s);
}
