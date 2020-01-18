package pers.lyning.autodatajpa.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lyning
 */
public class GetMethod {
    private final Object instance;

    private final String methodName;

    public GetMethod(Object instance, String fieldName) {
        this.instance = instance;
        this.methodName = asGetMethod(fieldName);
    }

    public Object invoke() {
        try {
            Method method = instance.getClass().getDeclaredMethod(this.methodName);
            return method.invoke(instance);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(String.format("%s.%s method not found", this.instance.getClass().getName(), this.methodName));
        }
    }

    private String asGetMethod(String alias) {
        return "get" + alias.substring(0, 1).toUpperCase() + alias.substring(1);
    }
}
