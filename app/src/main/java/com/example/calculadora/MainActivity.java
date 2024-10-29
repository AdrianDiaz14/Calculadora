package com.example.calculadora;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView textResultado = findViewById(R.id.textResultado);

        findViewById(R.id.button1).setOnClickListener(v -> textResultado.append("1"));
        findViewById(R.id.button2).setOnClickListener(v -> textResultado.append("2"));
        findViewById(R.id.button3).setOnClickListener(v -> textResultado.append("3"));
        findViewById(R.id.button4).setOnClickListener(v -> textResultado.append("4"));
        findViewById(R.id.button5).setOnClickListener(v -> textResultado.append("5"));
        findViewById(R.id.button6).setOnClickListener(v -> textResultado.append("6"));
        findViewById(R.id.button7).setOnClickListener(v -> textResultado.append("7"));
        findViewById(R.id.button8).setOnClickListener(v -> textResultado.append("8"));
        findViewById(R.id.button9).setOnClickListener(v -> textResultado.append("9"));
        findViewById(R.id.button0).setOnClickListener(v -> textResultado.append("0"));

        // Operadores
        findViewById(R.id.buttonMas).setOnClickListener(v -> textResultado.append("+"));
        findViewById(R.id.buttonMenos).setOnClickListener(v -> textResultado.append("-"));
        findViewById(R.id.buttonMultiplicar).setOnClickListener(v -> textResultado.append("*"));
        findViewById(R.id.buttonDividir).setOnClickListener(v -> textResultado.append("/"));

        // Botón de Clear (C)
        findViewById(R.id.buttonC).setOnClickListener(v -> textResultado.setText(""));

        findViewById(R.id.buttonIgual).setOnClickListener(v -> {
            String valor = textResultado.getText().toString();
            int resultado = calculate(valor);
            textResultado.setText(String.valueOf(resultado));
        });
    }
}
