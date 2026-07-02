package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private double firstNumber = 0;
    private String currentOperator = "";
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvDisplay = findViewById(R.id.tvDisplay);
        int[] numberIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(view -> {
                Button button = (Button) view;
                onNumberClick(button.getText().toString());
            });
        }

        int[] operatorIds = {R.id.btn_add, R.id.btn_sub, R.id.btn_mul, R.id.btn_div};

        for (int id : operatorIds) {
            findViewById(id).setOnClickListener(view -> {
                Button button = (Button) view;
                onOperatorClick(button.getText().toString());
            });
        }

        findViewById(R.id.btn_c).setOnClickListener(view -> clearCalculator());
        findViewById(R.id.btn_equal).setOnClickListener(view -> calculateResult());

    }

    private void calculateResult() {
        if (currentOperator.isEmpty()) return;
        try {
            double secondNumber = Double.parseDouble(tvDisplay.getText().toString());
            double result = 0;

            switch (currentOperator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "*": result = firstNumber * secondNumber; break;
                case "/":
                    if (secondNumber == 0) {
                        tvDisplay.setText("Error");
                        isNewOp = true;
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
            }

            tvDisplay.setText(String.valueOf(result));
            currentOperator = "";
            isNewOp = true;

        } catch (NumberFormatException e) {}
    }

    private void clearCalculator() {
        tvDisplay.setText("0");
        firstNumber = 0;
        currentOperator = "";
        isNewOp = true;
    }

    private void onOperatorClick(String operator) {
        try {
            firstNumber = Double.parseDouble(tvDisplay.getText().toString());
            currentOperator = operator;
            isNewOp = true;
        } catch (NumberFormatException e) {}
    }

    private void onNumberClick(String number) {
        if (isNewOp) {
            tvDisplay.setText(number);
            isNewOp = false;
        } else {
            tvDisplay.append(number);
        }
    }
}