package com.example.calculadora;

public class Calculator{
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

    // Verifica que no haya más de un operador junto en la ecuación
    private boolean esOperacionValida(String operacion) {
        String simbolosConsecutivos = "[+\\-*/]{2,}";
        if (operacion.matches(".*" + simbolosConsecutivos + ".*")) {
            return false;
        }
        return true;  // Devuelve true si la operación es válida
    }

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

    public int resolverSuma(String operacion) {
        String[] bloques = operacion.split("\\+");
        return calculate(bloques[0]) + calculate(bloques[1]);
    }

    public int resolverResta(String operacion) {
        String[] bloques = operacion.split("-");
        return calculate(bloques[0]) - calculate(bloques[1]);
    }
}
