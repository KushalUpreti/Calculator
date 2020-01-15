package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public EditText result;
    public EditText newNumber;
    public TextView displayOperation;

    //variable to hold the operands and type of calculations.
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOpernation = "=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNumber = findViewById(R.id.editText3);
        result = findViewById(R.id.editText);
        displayOperation = findViewById(R.id.textView);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonClear = findViewById(R.id.buttonClear);

        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonDec = findViewById(R.id.buttonDec);

        Button buttonNeg = findViewById(R.id.buttonNeg);

        View.OnClickListener clear = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                result.setText("");
                pendingOpernation = "";
                operand1 = null;
                operand2 = null;
            }
        };

        buttonClear.setOnClickListener(clear);

        View.OnClickListener apple = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNumber.append(b.getText().toString());  //just for display purpose
            }
        };

        button0.setOnClickListener(apple);
        button1.setOnClickListener(apple);
        button2.setOnClickListener(apple);
        button3.setOnClickListener(apple);
        button4.setOnClickListener(apple);
        button5.setOnClickListener(apple);
        button6.setOnClickListener(apple);
        button7.setOnClickListener(apple);
        button8.setOnClickListener(apple);
        button9.setOnClickListener(apple);
        buttonDec.setOnClickListener(apple);

        View.OnClickListener operation = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                if (value.length() != 0) {
                    performOperation(value, op);
                }
                pendingOpernation = op;
                displayOperation.setText(pendingOpernation);
            }
        };

        buttonEquals.setOnClickListener(operation);
        buttonDivide.setOnClickListener(operation);
        buttonMultiply.setOnClickListener(operation);
        buttonPlus.setOnClickListener(operation);
        buttonMinus.setOnClickListener(operation);

        buttonNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = newNumber.getText().toString();
                if (value.length() == 0) {
                    newNumber.setText("-");
                } else {
                    try {
                        Double doubluValue = Double.valueOf(value);
                        doubluValue = doubluValue * -1;
                        newNumber.setText(doubluValue.toString());
                    } catch (NumberFormatException e) {
                        newNumber.setText("");
                    }
                }
            }
        });


    }

    private void performOperation(String value, String op) {
        if (null == operand1) {
            operand1 = Double.valueOf(value);
        } else {
            operand2 = Double.valueOf(value);

            if (pendingOpernation.equals("=")) {
                pendingOpernation = op;
            }
            switch (pendingOpernation) {
                case "=":
                    operand1 = operand2;
                    break;

                case "/":
                    if (operand2 == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= operand2;
                    }
                    break;

                case "*":
                    operand1 *= operand2;
                    break;

                case "-":
                    operand1 -= operand2;
                    break;

                case "+":
                    operand1 += operand2;
                    break;
            }
        }

        result.setText(operand1.toString());
        newNumber.setText(" ");
    }
}
