package org.ksql.script.fatory;


import org.ksql.script.bo.ReturnParam;
import org.ksql.script.conversion.TypeConversion;

public interface TypeConversionFactory {
    <T,S> TypeConversion createTypeConversion(ReturnParam<T,S> returnParam, S s);
}
