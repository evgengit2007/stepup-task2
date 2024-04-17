package ru.stepup.course2;

import java.lang.reflect.Proxy;

public class Utils {
    public static Fractionable cache(Fraction fraction) {
        ClassLoader classLoader = Fraction.class.getClassLoader();
        Class[] interfaces = fraction.getClass().getInterfaces();
        Fractionable proxyFraction = (Fractionable) Proxy.newProxyInstance(classLoader,
                interfaces,
                new FractionInvocationHandler(fraction));
        return proxyFraction;
    }

}
