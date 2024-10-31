package com.example.calculadora;

/**
 * La clase Calculator proporciona métodos para realizar operaciones
 * aritméticas básicas, incluyendo suma, resta, multiplicación, división,
 * y el manejo de operadores unarios '+' y '-'.
 *
 * @version 2.2
 */
public class Calculator {

    /**
     * Calcula el resultado de una operación matemática representada como una cadena.
     * Este método soporta suma, resta, multiplicación, división y operadores unarios.
     *
     * @param operacion la cadena de operación a calcular
     * @return el resultado de la operación o -1 si la operación es inválida
     */
    public int calculate(String operacion) {
        // Manejar los operadores unarios
        operacion = resolverOperadoresUnarios(operacion);

        // Procesar multiplicaciones y divisiones
        while (operacion.contains("*") || operacion.contains("/")) {
            operacion = resolverMultiplicacionesYDivisiones(operacion);
        }

        // Procesar sumas y restas
        return resolverSumaYResta(operacion);
    }

    /**
     * Maneja los operadores unarios '+' y '-' al principio o después de otros operadores.
     *
     * @param operacion la cadena de operación que puede incluir operadores unarios
     * @return la cadena de operación con los operadores unarios manejados
     */
    private String resolverOperadoresUnarios(String operacion) {
        // En caso de encontrar dos signos "+" y/o "-" seguidos los convierte en su equivalente
        operacion = operacion.replaceAll("\\+\\-", "-");
        operacion = operacion.replaceAll("\\-\\-", "+");

        return operacion;
    }

    /**
     * Evalúa todas las operaciones de multiplicación y división en la cadena dada.
     *
     * @param operacion la cadena de operación que contiene multiplicaciones y divisiones
     * @return la cadena de operación con las multiplicaciones y divisiones evaluadas
     */
    private String resolverMultiplicacionesYDivisiones(String operacion) {
        while (operacion.contains("*") || operacion.contains("/")) {
            int indice = -1;
            char operador = ' ';
            if (operacion.contains("*")) {
                indice = operacion.indexOf("*");
                operador = '*';
            }
            if (operacion.contains("/") && (indice == -1 || operacion.indexOf("/") < indice)) {
                indice = operacion.indexOf("/");
                operador = '/';
            }

            int parte1 = indice - 1;
            while (parte1 >= 0 && (Character.isDigit(operacion.charAt(parte1)) || operacion.charAt(parte1) == '-'))
                parte1--;
            parte1++;

            int parte2 = indice + 1;
            while (parte2 < operacion.length() && (Character.isDigit(operacion.charAt(parte2)) || operacion.charAt(parte2) == '-'))
                parte2++;

            String parteIzquierda = operacion.substring(parte1, indice);
            String parteDerecha = operacion.substring(indice + 1, parte2);
            int valorIzquierda = Integer.parseInt(parteIzquierda);
            int valorDerecha = Integer.parseInt(parteDerecha);
            int resultado = (operador == '*') ? valorIzquierda * valorDerecha : valorIzquierda / valorDerecha;

            operacion = operacion.substring(0, parte1) + resultado + operacion.substring(parte2);
        }
        return operacion;
    }

    /**
     * Resuelve las sumas y restas en la cadena dada.
     *
     * @param operacion la cadena de operación que contiene sumas y restas
     * @return el resultado de la operación
     */
    private int resolverSumaYResta(String operacion) {
        int resultado = 0;
        int numeroActual = 0;
        char operacionActual = '+'; // Inicializamos como '+' para que el primer número se sume

        for (int i = 0; i < operacion.length(); i++) {
            char c = operacion.charAt(i);

            // Si el carácter es un dígito, lo convertimos y agregamos al número actual
            if (Character.isDigit(c)) {
                numeroActual = numeroActual * 10 + (c - '0');
            }

            // Si encontramos un operador o llegamos al final, aplicamos la operación previa
            if (c == '+' || c == '-' || i == operacion.length() - 1) {
                if (operacionActual == '+') {
                    resultado += numeroActual;
                } else if (operacionActual == '-') {
                    resultado -= numeroActual;
                }

                // Actualizamos el operador y reiniciamos el número actual
                operacionActual = c;
                numeroActual = 0;
            }
        }

        return resultado;
    }
}
