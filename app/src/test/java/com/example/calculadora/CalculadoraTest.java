package com.example.calculadora;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculadoraTest {
    @Test
    public void testAdd2Operands() {
        int total = calc.calculate ("5+3");
        assertEquals ("X + Y operations not working correctly", 8, total);
        //The message here is displayed if the test fails
    }

    @Test
    public void testAdd1Operands() {
        int total = calc.calculate ("4+3+1");
        assertEquals ("+X operations not working correctly", 8, total);
        //The message here is displayed if the test fails
    }

    @Test
    public void testMult2Operands() {
        int total = calc.calculate ("4*2");
        assertEquals ("4*X operations not working correctly", 8, total);
        //The message here is displayed if the test fails
    }
}
