package org.ksql.script.conversion;

public interface TypeConversion<T,S> {
    T concersion(S s);
}
