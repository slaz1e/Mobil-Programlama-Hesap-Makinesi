package com.example.hesapmakinesi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private String pendingOperation;
    private EditText newNumber;
    private TextView result,operandDisplay;
    private Double operand1=null,operand2=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNumber=(EditText)findViewById(R.id.numbers);
        result=(TextView)findViewById(R.id.resultText);
        operandDisplay=(TextView) findViewById(R.id.operatorText);
        Button button0=(Button)findViewById(R.id.zero);
        Button button1=(Button)findViewById(R.id.one);
        Button button2=(Button)findViewById(R.id.two);
        Button button3=(Button)findViewById(R.id.three);
        Button button4=(Button)findViewById(R.id.four);
        Button button5=(Button)findViewById(R.id.five);
        Button button6=(Button)findViewById(R.id.six);
        Button button7=(Button)findViewById(R.id.seven);
        Button button8=(Button)findViewById(R.id.eight);
        Button button9=(Button)findViewById(R.id.nine);
        Button buttonDot=(Button)findViewById(R.id.dot);
        Button buttonMinus=(Button)findViewById(R.id.minus);
        Button buttonPlus=(Button)findViewById(R.id.plus);
        Button buttonDivide=(Button)findViewById(R.id.divide);
        Button buttonMultiply=(Button)findViewById(R.id.multiplication);
        Button buttonEqual=(Button)findViewById(R.id.equal);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button=(Button)view;
                newNumber.append(button.getText().toString());
            }
        };

        View.OnClickListener operationListener =new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String op = button.getText().toString();
                String value = newNumber.getText().toString();
                try {
                    Double dValue = Double.valueOf(value);
                    performOperation(dValue,op);
                } catch (NumberFormatException E) {
                    newNumber.setText("");
                }
                pendingOperation=op;
                operandDisplay.setText(pendingOperation);
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);
        buttonPlus.setOnClickListener(listener);
        buttonMinus.setOnClickListener(listener);
        buttonMultiply.setOnClickListener(listener);
        buttonEqual.setOnClickListener(listener);
        buttonDot.setOnClickListener(operationListener);
        buttonPlus.setOnClickListener(operationListener);
        buttonMinus.setOnClickListener(operationListener);
        buttonMultiply.setOnClickListener(operationListener);
        buttonEqual.setOnClickListener(operationListener);
        buttonDivide.setOnClickListener(operationListener);
    }
    private void performOperation(Double value,String op){
        if(null==operand1){
            operand1=value;
        }
        else{
            operand2=value;
            if(pendingOperation=="=") {
                pendingOperation = op;
            }
                switch (pendingOperation){
                    case"/":
                        if(operand2==0){
                            operand1=0.0d;
                        }
                        else{
                            operand1/=operand2;
                        }
                        break;
                    case"*":
                        operand1*=operand2;
                        break;
                    case"+":
                        operand1+=operand2;
                        break;
                    case"-":
                        operand1-=operand2;
                        break;
                }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
        operandDisplay.setText(op);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("PENDINGOPERATOR",pendingOperation);
        if(operand1!=null){
            outState.putDouble("OPERAND1",operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation= savedInstanceState.getString("PENDINGOPERATOR");
        operand1=savedInstanceState.getDouble("OPERAND1");
        result.setText(String.valueOf(operand1));
        operandDisplay.setText(pendingOperation);
    }
}