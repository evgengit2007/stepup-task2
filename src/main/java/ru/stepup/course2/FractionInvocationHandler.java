package ru.stepup.course2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FractionInvocationHandler implements InvocationHandler {
    private Fraction fraction;

    public FractionInvocationHandler(Fraction fraction) {
        this.fraction = fraction;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before: ");
        return method.invoke(proxy, args);
    }
}
