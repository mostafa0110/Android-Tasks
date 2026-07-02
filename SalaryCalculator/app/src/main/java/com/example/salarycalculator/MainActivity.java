package com.example.salarycalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etBasicPay;
    private TextView tvSalaryBreakdown;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBasicPay = findViewById(R.id.etBasicPay);
        tvSalaryBreakdown = findViewById(R.id.tvSalaryBreakdown);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(view -> calculateSalary());
    }

    private void calculateSalary() {
        String basicPayString = etBasicPay.getText().toString();

        if (basicPayString.isEmpty()) {
            tvSalaryBreakdown.setText("Please enter salary amount.");
            return;
        }

        double basicPay = Double.parseDouble(basicPayString);

        double hra = basicPay * 0.20; // 20%
        double da = basicPay * 0.50;  // 50%
        double grossSalary = basicPay + hra + da;

        double tax = grossSalary * 0.10; // 10% tax deduction
        double netSalary = grossSalary - tax;

        String resultMessage =
                "Basic Pay: $" + String.format("%.2f", basicPay) + "\n" +
                        "HRA (20%): $" + String.format("%.2f", hra) + "\n" +
                        "DA (50%): $" + String.format("%.2f", da) + "\n" +
                        "------------------------\n" +
                        "Gross Salary: $" + String.format("%.2f", grossSalary) + "\n" +
                        "Tax (10%): -$" + String.format("%.2f", tax) + "\n" +
                        "========================\n" +
                        "NET SALARY: $" + String.format("%.2f", netSalary);

        tvSalaryBreakdown.setText(resultMessage);
    }
}