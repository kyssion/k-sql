package org.mirror.reflection.wrapper;

import org.mirror.reflection.mirror.MirrorObject;
import org.mirror.reflection.object.ObjectFactory;
import org.mirror.reflection.property.PropertyTokenizer;

import java.util.Collection;
import java.util.List;

public class CollectionWrapper implements ObjectWrapper {

    private final Class<?> type;

    private final Collection<Object> object;

    public CollectionWrapper(MirrorObject mirrorObject, Collection<Object> object) {
        this.object = object;
        this.type=object.getClass();
    }

    @Override
    public Class<?> getType() {
        return this.type;
    }

    @Override
    public Object get(PropertyTokenizer prop) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(PropertyTokenizer prop, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getGetterNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getSetterNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> getSetterType(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> getGetterType(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasSetter(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasGetter(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MirrorObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCollection() {
        return true;
    }

    @Override
    public void add(Object element) {
        object.add(element);
    }

    @Override
    public <E> void addAll(List<E> element) {
        object.addAll(element);
    }

    @Override
    public Object invoke(String name, Object[] params) {
        return null;
    }

}
