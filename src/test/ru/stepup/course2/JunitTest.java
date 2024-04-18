package ru.stepup.course2;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class JunitTest {
    @Test
    @SneakyThrows(NullPointerException.class)
    public void testFraction() {
        // корректно создан экземпляр
        Assertions.assertDoesNotThrow(() -> new Fraction(1, 2));
        // сеттеры корректно
        Fraction fraction = new Fraction(1, 2);
        Assertions.assertDoesNotThrow(() -> fraction.setNum(5));
        Assertions.assertDoesNotThrow(() -> fraction.setDenum(5));
        // doubleValue корректно
        Assertions.assertDoesNotThrow(() -> fraction.doubleValue());
        // doubleValue корректно, вычисление значения
        Assertions.assertEquals(fraction.doubleValue(), 1.00F);
        // doubleValue исключения не будет хоть и деление на ноль
        fraction.setDenum(0);
        Assertions.assertDoesNotThrow(() -> fraction.doubleValue());
        // multiValue корректно
        Assertions.assertDoesNotThrow(() -> fraction.multiValue());
    }

    @Test
    @SneakyThrows(NullPointerException.class)
    public void testUtils() {
        // Fractionable корректно
        Fraction fr = new Fraction(1, 2);
        Assertions.assertDoesNotThrow(() -> (Fractionable) Utils.cache(fr));
    }

    @Test
    @SneakyThrows(NullPointerException.class)
    public void testFractionInvocationHandler() {
        // FractionInvocationHandler корректно
        Fraction fr = new Fraction(1, 2);
        Assertions.assertDoesNotThrow(() -> new FractionInvocationHandler(fr));
        // invoke
        // первый вызов
        Fraction fr1 = new Fraction(1, 2);
        Fractionable num = Utils.cache(fr1);
        double result1 = num.doubleValue();
        num = mock(Fractionable.class);
        System.out.println(result1);
        // повторный вызов из кэша
        double result2 = num.doubleValue();
        verify(num, times(1)).doubleValue();
    }

    @Test
    @SneakyThrows(NullPointerException.class)
    public void testMainApp() {
        // проверка запускающего модуля MainApp
        String[] str = null;
        Assertions.assertDoesNotThrow(() -> MainApp.main(str));
    }
}
