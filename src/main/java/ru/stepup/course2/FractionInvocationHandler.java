package ru.stepup.course2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class FractionInvocationHandler implements InvocationHandler {
    private final Object object;
    private final HashMap<String, Double> hashMap;

    public FractionInvocationHandler(Object object) {
        this.object = object;
        this.hashMap = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        double result;
        String key = "keyHashMap";
        Method m = object.getClass().getMethod(method.getName(), method.getParameterTypes());
        // перебираем аннотации в последовательности Mutator, потом Cache
        // теперь если даже разработчик поставит во Fraction на методе doubleValue обе аннотации,
        // или заменит Cache на Mutator, то наша прокси будет считать правильно, правда без кэширования,
        // т.к. первой отработает Mutator
        if (m.isAnnotationPresent(Mutator.class)) {
            if (hashMap.containsKey(key)) {
                hashMap.remove(key);
            }
            return m.invoke(object, args);
        }
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
        return m.invoke(object, args);
    }
}
