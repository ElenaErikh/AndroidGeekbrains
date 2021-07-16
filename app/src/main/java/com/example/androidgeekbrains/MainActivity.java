package com.example.androidgeekbrains;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Calculator calc;
    private TextView outputTextView;
    public static final String DEFAULT_VALUE = "0";
    public static final String PARAM_TEXT_VIEW = "PARAM_TEXT_VIEW";

    private final int[] numberBtnsID = new int[]{R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputTextView = findViewById(R.id.outputTextView);
        calc = new Calculator();

        setBtnListeners();

        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "Первый запуск!";
        } else {
            instanceState = "Повторный запуск!";
        }
        log(instanceState + " - onCreate()");
    }

    private void setBtnListeners(){

        for (int i = 0; i < numberBtnsID.length; i++) {
            findViewById(numberBtnsID[i]).setOnClickListener(v -> {
                Button btn = (Button)v;
                outputTextView.setText(calc.setTextStr(btn.getText().toString()));
            });
        }

        findViewById(R.id.clearBtn).setOnClickListener(v -> {
            outputTextView.setText(DEFAULT_VALUE);
            calc = new Calculator();
        });

        findViewById(R.id.sumBtn).setOnClickListener(v -> {
            outputTextView.setText(calc.setSymbol(Symbols.sum));
        });

        findViewById(R.id.divBtn).setOnClickListener(v -> {
            outputTextView.setText(calc.setSymbol(Symbols.div));
        });

        findViewById(R.id.multBtn).setOnClickListener(v -> {
            outputTextView.setText(calc.setSymbol(Symbols.mul));
        });

        findViewById(R.id.subtractBtn).setOnClickListener(v -> {
            outputTextView.setText(calc.setSymbol(Symbols.sub));
        });

        findViewById(R.id.equalBtn).setOnClickListener(v -> {
            outputTextView.setText(calc.getResult());
        });
    }

    private void updateText(String str){
        outputTextView.setText(calc.getStr());
    }

    @Override
    protected void onStart() {
        super.onStart();
        log("onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        outputTextView.setText(calc.setTextStr(saveInstanceState.getString(PARAM_TEXT_VIEW, DEFAULT_VALUE)));
        log("Повторный eState()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log("onPause()");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PARAM_TEXT_VIEW, calc.getStr().toString());
        log("saveInstanceState()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log("onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log("onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log("onDestroy()");
    }

    private void log(@NonNull String text) {
        Log.d("MAIN_ACTIVITY_1", text);
    }

}
