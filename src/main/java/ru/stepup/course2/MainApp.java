package ru.stepup.course2;

public class MainApp {
    public static void main(String[] args) {
        Fraction fr = new Fraction(1,2);
        Fractionable num = Utils.cache(fr);
        num.doubleValue(); // sout сработал
        num.doubleValue(); // sout молчит
        num.doubleValue(); // sout молчит
        num.setNum(5);
        num.doubleValue(); // sout сработал
        num.doubleValue(); // sout молчит

    }
}
