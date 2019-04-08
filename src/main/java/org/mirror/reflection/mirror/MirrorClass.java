package org.mirror.reflection.mirror;

import org.mirror.reflection.DefaultReflectorFactory;
import org.mirror.reflection.Reflector;
import org.mirror.reflection.ReflectorFactory;
import org.mirror.reflection.agent.Agent;
import org.mirror.reflection.agent.GetFieldAgent;
import org.mirror.reflection.agent.MethodAgent;
import org.mirror.reflection.property.PropertyTokenizer;
import org.mirror.reflection.property.TypeParameterResolver;
import org.mirror.reflection.util.TypeEnum;
import org.mirror.reflection.exception.ReflectionException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 对reflector的一层封装,更加方便的获取一个Class对象中geter和setter的各种信息
 * 相比较mirrorObject class 是提供了name级别的层级解析功能
 */
public class MirrorClass {

    private final ReflectorFactory reflectorFactory;
    private final Reflector reflector;

    private MirrorClass(Class<?> type, ReflectorFactory reflectorFactory) {
        this.reflectorFactory = reflectorFactory;
        this.reflector = reflectorFactory.findForClass(type);
    }

    public static MirrorClass forClass(Class<?> type, ReflectorFactory reflectorFactory) {
        return new MirrorClass(type, reflectorFactory);
    }
    public static MirrorClass forClass(Class<?> type) {
        return new MirrorClass(type, new DefaultReflectorFactory());
    }



    //通过名称获取本身的一个field的MetaClass
    public MirrorClass metaClassForProperty(String name) {
        Class<?> propType = reflector.getGetterType(name);
        return MirrorClass.forClass(propType, reflectorFactory);
    }

    //支持xxx.xxx.xxx这种格式
    public String findProperty(String name) {
        StringBuilder prop = buildProperty(name, new StringBuilder());
        return prop.length() > 0 ? prop.toString() : null;
    }

    /**
     * 添加驼峰写法的过滤
     *
     * @param name
     * @param useCamelCaseMapping
     * @return
     */
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if (useCamelCaseMapping) {
            name = name.replace("_", "");
        }
        return findProperty(name);
    }

    public String[] getGetterNames() {
        return reflector.getGetablePropertyNames();
    }

    public String[] getSetterNames() {
        return reflector.getSetablePropertyNames();
    }

    public Class<?> getSetterType(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            MirrorClass metaProp = metaClassForProperty(prop.getName());
            return metaProp.getSetterType(prop.getChildren());
        } else {
            return reflector.getSetterType(prop.getName());
        }
    }

    public Class<?> getGetterType(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            MirrorClass metaProp = metaClassForProperty(prop);
            return metaProp.getGetterType(prop.getChildren());
        }
        // issue #506. Resolve the type inside a Collection Object
        return getGetterType(prop);
    }

    private MirrorClass metaClassForProperty(PropertyTokenizer prop) {
        Class<?> propType = getGetterType(prop);
        return MirrorClass.forClass(propType, reflectorFactory);
    }

    private Class<?> getGetterType(PropertyTokenizer prop) {
        Class<?> type = reflector.getGetterType(prop.getName());
        if (prop.getIndex() != null && Collection.class.isAssignableFrom(type)) {
            Type returnType = getGenericGetterType(prop.getName());
            if (returnType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) returnType).getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length == 1) {
                    returnType = actualTypeArguments[0];
                    if (returnType instanceof Class) {
                        type = (Class<?>) returnType;
                    } else if (returnType instanceof ParameterizedType) {
                        type = (Class<?>) ((ParameterizedType) returnType).getRawType();
                    }
                }
            }
        }
        return type;
    }

    private Type getGenericGetterType(String propertyName) {
        try {
            Agent agent = reflector.getGetAgent(propertyName);
            if (agent instanceof MethodAgent) {
                Field _method = MethodAgent.class.getDeclaredField("method");
                _method.setAccessible(true);
                Method method = (Method) _method.get(agent);
                return TypeParameterResolver.resolveReturnType(method, reflector.getType());
            } else if (agent instanceof GetFieldAgent) {
                Field _field = GetFieldAgent.class.getDeclaredField("field");
                _field.setAccessible(true);
                Field field = (Field) _field.get(agent);
                return TypeParameterResolver.resolveFieldType(field, reflector.getType());
            }
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
        return null;
    }

    public boolean hasSetter(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            if (reflector.hasSetter(prop.getName())) {
                MirrorClass metaProp = metaClassForProperty(prop.getName());
                return metaProp.hasSetter(prop.getChildren());
            } else {
                return false;
            }
        } else {
            return reflector.hasSetter(prop.getName());
        }
    }

    public boolean hasGetter(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            if (reflector.hasGetter(prop.getName())) {
                MirrorClass metaProp = metaClassForProperty(prop);
                return metaProp.hasGetter(prop.getChildren());
            } else {
                return false;
            }
        } else {
            return reflector.hasGetter(prop.getName());
        }
    }

    public Agent getGetAgent(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if(prop.hasNext()){
            MirrorClass mirrorProp = metaClassForProperty(prop);
            return mirrorProp.getGetAgent(prop.getChildren());
        }else {
            return reflector.getGetAgent(name);
        }
    }

    public Agent getSetAgent(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if(prop.hasNext()){
            MirrorClass mirrorProp = metaClassForProperty(prop);
            return mirrorProp.getSetAgent(prop.getChildren());
        }else {
            return reflector.getSetAgent(name);
        }
    }

    public Agent getMethod(String name, Class<?>[] paramType) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if(prop.hasNext()){
            MirrorClass mirrorProp = metaClassForProperty(prop);
            return mirrorProp.getMethod(prop.getChildren(),paramType);
        }else {
            List<Agent> allMethod = reflector.getMethod(name);
            for (Agent agent : allMethod) {
                if (isInMethod(agent, paramType)) {
                    return agent;
                }
            }
            throw new ReflectionException("There is no method for method named '" + name + "'");
        }
    }

    private boolean isInMethod(Agent invokers, Class<?>[] paramType) {

        Class<?>[] params = invokers.getParamType();
        if (params.length != paramType.length) {
            return false;
        }
        params = specificationType(params);
        paramType = specificationType(paramType);
        return Arrays.equals(params, paramType);
    }

    private Class<?>[] specificationType(Class<?>[] types) {
        Class<?>[] newTypes = new Class<?>[types.length];
        for (int index = 0; index < types.length; index++) {
            newTypes[index] = TypeEnum.getType(types[index]);
        }
        return newTypes;
    }

    private StringBuilder buildProperty(String name, StringBuilder builder) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            String propertyName = reflector.findPropertyName(prop.getName());
            if (propertyName != null) {
                builder.append(propertyName);
                builder.append(".");
                MirrorClass metaProp = metaClassForProperty(propertyName);
                metaProp.buildProperty(prop.getChildren(), builder);
            }
        } else {
            String propertyName = reflector.findPropertyName(prop.getName());
            if (propertyName != null) {
                builder.append(propertyName);
            }
        }
        return builder;
    }

    public boolean hasDefaultConstructor() {
        return reflector.hasDefaultConstructor();
    }

    public Class<?> getType() {
        return this.reflector.getType();
    }

}
