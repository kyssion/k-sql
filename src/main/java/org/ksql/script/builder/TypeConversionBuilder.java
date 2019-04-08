package org.ksql.script.builder;


import org.ksql.script.conversion.TypeConversion;

public interface TypeConversionBuilder {
    <T, S> TypeConversion<T, S> create(T t, S s);
}
