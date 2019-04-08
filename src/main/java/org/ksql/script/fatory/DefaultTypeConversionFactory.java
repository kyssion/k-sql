package org.ksql.script.fatory;


import org.ksql.script.builder.DefaultTypeConversionBuilder;
import org.ksql.script.builder.TypeConversionBuilder;
import org.ksql.script.conversion.TypeConversion;

public class DefaultTypeConversionFactory implements TypeConversionFactory {

    private static TypeConversionBuilder builder =
            new DefaultTypeConversionBuilder();

    @Override
    public <T, S> TypeConversion getTypeConversion(T t, S s) {
        return builder.create(t,s);
    }
}
