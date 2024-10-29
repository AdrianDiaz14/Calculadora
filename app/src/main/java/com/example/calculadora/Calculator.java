package com.example.calculadora;

/**
 * La clase Calculator proporciona métodos para realizar operaciones matemáticas básicas
 * como suma, resta, multiplicación y división, evaluando expresiones en forma de cadenas.
 */
public class Calculator{

        /**
     * Calcula el resultado de una operación matemática representada como una cadena.
     *
     * @param operacion La operación matemática a calcular, que puede contener números y los operadores
     *                  +, -, *, y /.
     * @return El resultado de la operación, o -1 si la operación es inválida o no se puede calcular.
     */
    public int calculate(String operacion) {

        // Verifica si la operación no contiene operadores
        int resultado = convertirSiEsNumero(operacion);
        if (resultado != -1) {
            return resultado;
        }

        // Comprueba que la operación esté bien formulada
        if (!esOperacionValida(operacion)) {
            TextView textResultado = findViewById(R.id.textResultado);
            textResultado.setText("-1");
            return -1;
        }

        // Resuelve multiplicaciones y divisiones
        operacion = resolverMultiplicaciones(operacion);
        operacion = resolverDivisiones(operacion);

        // Resuelve sumas y restas
        if (operacion.contains("+")) {
            return resolverSuma(operacion);
        } else if (operacion.contains("-")) {
            return resolverResta(operacion);
        }

        // Convierte la operación a un número cuando ya no hay más operadores
        return convertirSiEsNumero(operacion);
    }

     /**
     * Verifica si la operación es válida, asegurándose de que no haya más de un operador
     * consecutivo.
     *
     * @param operacion La operación a validar.
     * @return true si la operación es válida, false en caso contrario.
     */
     private boolean esOperacionValida(String operacion) {
        String simbolosConsecutivos = "[+\\-*/]{2,}";
        if (operacion.matches(".*" + simbolosConsecutivos + ".*")) {
            return false;
        }
        return true;  // Devuelve true si la operación es válida
    }

     /**
     * Intenta convertir la operación en un número entero.
     *
     * @param operacion La operación a convertir.
     * @return El número entero si la operación es un número, o -1 en caso de error.
     */
    public int convertirSiEsNumero(String operacion) {
        if (!operacion.contains("+") && !operacion.contains("-") && !operacion.contains("*") && !operacion.contains("/")) {
            try {
                return Integer.parseInt(operacion);
            } catch (NumberFormatException e) {
                TextView textResultado = findViewById(R.id.textResultado);
                textResultado.setText("-1");
                return -1;  // Devuelve -1 en caso de error
            }
        }
        return -1;
    }

     /**
     * Resuelve las multiplicaciones en la operación.
     *
     * @param operacion La operación a resolver.
     * @return La operación resultante con las multiplicaciones resueltas.
     */
    public String resolverMultiplicaciones(String operacion) {
        while (operacion.contains("*")) {
            int indice = operacion.indexOf("*");
            int parte1 = indice - 1;
            while (parte1 >= 0 && Character.isDigit(operacion.charAt(parte1)))
                parte1--;
            parte1++;

            int parte2 = indice + 1;
            while (parte2 < operacion.length() && Character.isDigit(operacion.charAt(parte2)))
                parte2++;

            // Obtener los dos números
            String parteIzquierda = operacion.substring(parte1, indice);
            String parteDerecha = operacion.substring(indice + 1, parte2);
            int valorIzquierda = calculate(parteIzquierda);
            int valorDerecha = calculate(parteDerecha);
            int resultado = valorIzquierda * valorDerecha;

            // Reemplazar la multiplicación por el resultado
            operacion = operacion.substring(0, parte1) + resultado + operacion.substring(parte2);
        }
        return operacion;
    }

     /**
     * Resuelve las divisiones en la operación.
     *
     * @param operacion La operación a resolver.
     * @return La operación resultante con las divisiones resueltas.
     */
    public String resolverDivisiones(String operacion) {
        while (operacion.contains("/")) {
            int indice = operacion.indexOf("/");
            int parte1 = indice - 1;
            while (parte1 >= 0 && Character.isDigit(operacion.charAt(parte1)))
                parte1--;
            parte1++;

            int parte2 = indice + 1;
            while (parte2 < operacion.length() && Character.isDigit(operacion.charAt(parte2)))
                parte2++;

            // Obtener los dos números
            String parteIzquierda = operacion.substring(parte1, indice);
            String parteDerecha = operacion.substring(indice + 1, parte2);
            int valorIzquierda = calculate(parteIzquierda);
            int valorDerecha = calculate(parteDerecha);
            int resultado = valorIzquierda / valorDerecha;

            // Reemplazar la división por el resultado
            operacion = operacion.substring(0, parte1) + resultado + operacion.substring(parte2);
        }
        return operacion;
    }

     /**
     * Resuelve la suma de los dos operandos en la operación.
     *
     * @param operacion La operación que contiene la suma.
     * @return El resultado de la suma.
     */
    public int resolverSuma(String operacion) {
        String[] bloques = operacion.split("\\+");
        return calculate(bloques[0]) + calculate(bloques[1]);
    }

     /**
     * Resuelve la resta de los dos operandos en la operación.
     *
     * @param operacion La operación que contiene la resta.
     * @return El resultado de la resta.
     */
    public int resolverResta(String operacion) {
        String[] bloques = operacion.split("-");
        return calculate(bloques[0]) - calculate(bloques[1]);
    }
}
