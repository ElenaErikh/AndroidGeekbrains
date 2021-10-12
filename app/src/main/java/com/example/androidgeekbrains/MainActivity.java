package com.example.androidgeekbrains;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private StringBuilder str;
    private Symbols mathSymbol;
    private TextView outputTextView;
    public static final String DEFAULT_VALUE = "0";
    public static final String PARAM_TEXT_VIEW = "PARAM_TEXT_VIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputTextView = findViewById(R.id.outputTextView);
        str = new StringBuilder();

        findViewById(R.id.clearBtn).setOnClickListener(v -> {
            outputTextView.setText(DEFAULT_VALUE);
            str = new StringBuilder();
        });

        findViewById(R.id.sumBtn).setOnClickListener(v -> {
            mathSymbol = Symbols.sum;
            outputTextView.setText(str.append(String.format(Locale.getDefault(), " %s ",
                    (Symbols.sum).getSymbol())));
        });

        findViewById(R.id.divBtn).setOnClickListener(v -> {
            mathSymbol = Symbols.div;
            outputTextView.setText(str.append(String.format(Locale.getDefault(), " %s ",
                    (Symbols.div).getSymbol())));
        });

        findViewById(R.id.multBtn).setOnClickListener(v -> {
            mathSymbol = Symbols.mul;
            outputTextView.setText(str.append(String.format(Locale.getDefault(), " %s ",
                    (Symbols.mul).getSymbol())));
        });

        findViewById(R.id.subtractBtn).setOnClickListener(v -> {
            mathSymbol = Symbols.sub;
            outputTextView.setText(str.append(String.format(Locale.getDefault(), " %s ",
                    (Symbols.sub).getSymbol())));
        });

        findViewById(R.id.button1).setOnClickListener(v -> outputTextView.setText(str.append("1")));

        findViewById(R.id.button2).setOnClickListener(v -> outputTextView.setText(str.append("2")));

        findViewById(R.id.button3).setOnClickListener(v -> outputTextView.setText(str.append("3")));

        findViewById(R.id.button4).setOnClickListener(v -> outputTextView.setText(str.append("4")));

        findViewById(R.id.button5).setOnClickListener(v -> outputTextView.setText(str.append("5")));

        findViewById(R.id.button6).setOnClickListener(v -> outputTextView.setText(str.append("6")));

        findViewById(R.id.button7).setOnClickListener(v -> outputTextView.setText(str.append("7")));

        findViewById(R.id.button8).setOnClickListener(v -> outputTextView.setText(str.append("8")));

        findViewById(R.id.button9).setOnClickListener(v -> outputTextView.setText(str.append("9")));

        findViewById(R.id.button0).setOnClickListener(v -> outputTextView.setText(str.append("0")));

        findViewById(R.id.equalBtn).setOnClickListener(v -> {
            if (str == null) {
                log("String is null");
            } else {
                String[] parts = str.toString().split("\\s");
                if (parts.length != 3) {
                    log("Invalid data");
                    return;
                }

                int val1 = Integer.parseInt(parts[0]);
                int val2 = Integer.parseInt(parts[2]);

                switch (mathSymbol) {
                    case sum:
                        updateText(String.valueOf(val1 + val2));
                        break;
                    case sub:
                        updateText(String.valueOf(val1 - val2));
                        break;
                    case mul:
                        updateText(String.valueOf(val1 * val2));
                        break;
                    case div:
                        try {
                            if (val2 != 0) {
                                updateText(String.valueOf(val1 / val2));
                                break;
                            }
                        } catch (ArithmeticException e) {
                            updateText(DEFAULT_VALUE);
                            e.printStackTrace();
                        }
                    default:
                        str = new StringBuilder();
                }
            }
        });

        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "Первый запуск!";
        } else {
            instanceState = "Повторный запуск!";
        }
        log(instanceState + " - onCreate()");
    }

    private void updateText(String str){
        this.str = new StringBuilder(str);
        outputTextView.setText(this.str);
    }

    @Override
    protected void onStart() {
        super.onStart();
        log("onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        str.append(saveInstanceState.getString(PARAM_TEXT_VIEW, DEFAULT_VALUE));
        outputTextView.setText(str);
        log("Повторный eState() **************** " + mathSymbol);
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
        outState.putString(PARAM_TEXT_VIEW, str.toString());
        log("saveInstanceState()  ****************" + str.toString());
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
