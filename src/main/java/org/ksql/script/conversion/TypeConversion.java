package org.ksql.script.conversion;

import org.ksql.script.bo.ReturnParam;

public interface TypeConversion<S> {
     void concersion(ReturnParam returnParam,S s);
}
