package ru.stepup.course2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Utils implements Cache, Mutator{
    public static Fractionable cache(Fraction fraction) {
        System.out.println("Utils, cache");
        ClassLoader classLoader = Fraction.class.getClassLoader();
        Class[] interfaces = fraction.getClass().getInterfaces();
        for (Class i: interfaces) {
            System.out.println(i.getDeclaredMethods());
        }
        Fractionable proxyFraction = (Fractionable) Proxy.newProxyInstance(classLoader,
                interfaces,
                new FractionInvocationHandler(fraction));
        return proxyFraction;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
