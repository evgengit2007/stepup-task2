package ru.stepup.course2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class FractionInvocationHandler implements InvocationHandler {
    private Object object;
    private HashMap<String, Double> hashMap;

    public FractionInvocationHandler(Object object) {
        this.object = object;
        this.hashMap = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        double result;
        String key = "keyHashMap";
//        System.out.println("Before: ");
        Method m = object.getClass().getMethod(method.getName(), method.getParameterTypes());
//        System.out.println(m.getName());
        if (m.isAnnotationPresent(Cache.class)) {
            if (hashMap.containsKey(key)) {
                result = hashMap.get(key);
                System.out.println(result);
                return result;
            }
            result = (Double) m.invoke(object, args);
            hashMap.put(key, result);
            System.out.println(result);
            return result;
        }
        if (m.isAnnotationPresent(Mutator.class)) {
            if (hashMap.containsKey(key)) {
                hashMap.remove(key);
            }
            System.out.println(hashMap.get(key));
            return m.invoke(object, args);
        }
        return m.invoke(object, args);
    }
}
