package ru.stepup.course2;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JunitTest {
    @Test
    @SneakyThrows(NullPointerException.class)
    public void testFraction() {
        // корректно создан экземпляр
        Assertions.assertDoesNotThrow(()->new Fraction(1,2));
        // сеттеры корректно
        Fraction fraction = new Fraction(1,2);
        Assertions.assertDoesNotThrow(()->fraction.setNum(5));
        Assertions.assertDoesNotThrow(()->fraction.setDenum(5));
        // doubleValue корректно
        Assertions.assertDoesNotThrow(()->fraction.doubleValue());
        // doubleValue корректно, вычисление значения
        Assertions.assertEquals(fraction.doubleValue(), 1.00F);
        // doubleValue исключения не будет хоть и деление на ноль
        fraction.setDenum(0);
        Assertions.assertDoesNotThrow(()->fraction.doubleValue());
    }

    @Test
    @SneakyThrows(NullPointerException.class)
    public void testUtils() {
        // Fractionable корректно
        Fraction fr = new Fraction(1,2);
        Assertions.assertDoesNotThrow(()->Utils.cache(fr));
    }

    @Test
    @SneakyThrows(NullPointerException.class)
    public void testFractionInvocationHandler() {
        // FractionInvocationHandler корректно
        Fraction fr = new Fraction(1,2);
        Assertions.assertDoesNotThrow(()->new FractionInvocationHandler(fr));
        // invoke
    }
}
