package com.example;

import com.example.test.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JUnit5ExceptionTester {

    private Calculator calculator = new Calculator();

    @Test
    public void expectIllegalArgumentException() {
        Throwable throwable = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.sqrt(-1));
        assertEquals("Cannot extract the square root of a negative value", throwable.getMessage());
    }

    @Test
    public void expectArithmeticException() {
        Throwable throwable = assertThrows(ArithmeticException.class,
                () -> calculator.divide(1, 0));
        assertEquals("Cannot divide by zero", throwable.getMessage());
    }
}
