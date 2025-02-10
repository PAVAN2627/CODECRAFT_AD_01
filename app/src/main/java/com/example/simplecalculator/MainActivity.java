package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{
private EditText display;
private String input = "";
private String operator = "";
private double num1 = 0;
private double num2 = 0;

@Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Set up button listeners
        setButtonListeners();
    }

    private void setButtonListeners() {
        int[] buttonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
                R.id.btn_add, R.id.btn_subtract, R.id.btn_multiply, R.id.btn_divide,
                R.id.btn_decimal, R.id.btn_equals, R.id.btn_clear, R.id.btn_backspace
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(this::onButtonClick);
        }
    }

    private void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                clear();
                break;
            case "←":
                backspace();
                break;
            case "=":
                calculate();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperator(buttonText);
                break;
            default:
                appendInput(buttonText);
                break;
        }
    }

    private void appendInput(String value) {
        input += value;
        display.setText(input);
    }

    private void clear() {
        input = "";
        operator = "";
        num1 = 0;
        num2 = 0;
        display.setText("0");
    }

    private void backspace() {
        if (input.length() > 0) {
            input = input.substring(0, input.length() - 1);
            display.setText(input.isEmpty() ? "0" : input);
        }
    }

    private void setOperator(String op) {
        if (!input.isEmpty()) {
            num1 = Double.parseDouble(input);
            operator = op;
            input = "";
        }
    }

    private void calculate() {
        if (!input.isEmpty() && !operator.isEmpty()) {
            num2 = Double.parseDouble(input);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            input = String.valueOf(result);
            display.setText(input);
            operator = "";
        }
    }
}