package org.ksql.script.exception;

import org.mirror.reflection.mirror.MirrorClass;

public class NotInterfaceError extends ErrorException {

    public NotInterfaceError(MirrorClass mirrorClass) {
        this("the class :" + mirrorClass.getType().getName() + "not a interface!");
    }

    private NotInterfaceError(String desc) {
        super(desc);
    }
}
