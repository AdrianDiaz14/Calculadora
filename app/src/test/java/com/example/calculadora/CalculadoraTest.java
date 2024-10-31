package com.example.calculadora;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * La clase CalculatorTest contiene pruebas unitarias para la clase
 * Calculator, asegurando que las operaciones aritméticas se
 * realicen correctamente.
 *
 * @author Adrian Diaz
 * @version 2.0
 * @since 2024-10-29
 */
public class CalculadoraTest {

    private Calculator calculator = new Calculator();

    /**
     * Configura el entorno de prueba antes de cada prueba.
     * Se crea una nueva instancia de Calculator.
     */
    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    /**
     * Limpia el entorno de prueba después de cada prueba.
     * Se establece la referencia de Calculator a null.
     */
    @After
    public void tearDown() {
        calculator = null;
    }

    /**
     * Prueba la suma de dos operandos.
     */
    @Test
    public void testAdd2Operands() {
        int total = calculator.calculate("5+3");
        assertEquals("X + Y operations not working correctly", 8, total);
    }

    /**
     * Prueba la suma de múltiples operandos.
     */
    @Test
    public void testAdd1Operands() {
        int total = calculator.calculate("4+3+1");
        assertEquals("+X operations not working correctly", 8, total);
    }

    /**
     * Prueba la multiplicación de dos operandos.
     */
    @Test
    public void testMult2Operands() {
        int total = calculator.calculate("4*2");
        assertEquals("4*X operations not working correctly", 8, total);
    }

    /**
     * Prueba combinaciones de suma y multiplicación.
     */
    @Test
    public void testAddAndMultiplyMixed() {
        assertEquals("2*2+3 not working correctly", 7, calculator.calculate("2*2+3"));
        assertEquals("3+2*2 not working correctly", 7, calculator.calculate("3+2*2"));
        assertEquals("3+2*2+4 not working correctly", 11, calculator.calculate("3+2*2+4"));
    }

    /**
     * Prueba la multiplicación de múltiples operandos.
     */
    @Test
    public void testMultOperands() {
        assertEquals("2*3 not working correctly", 6, calculator.calculate("2*3"));
        assertEquals("1*2*8 not working correctly", 16, calculator.calculate("1*2*8"));
    }
}
