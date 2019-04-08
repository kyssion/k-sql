package org.ksql.script.fatory;


import org.ksql.script.conversion.TypeConversion;

public interface TypeConversionFactory {
    <T,S> TypeConversion getTypeConversion(T t, S s);
}
