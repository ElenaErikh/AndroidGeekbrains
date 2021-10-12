package com.example.androidgeekbrains;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.ReferenceQueue;

public class MainActivity extends AppCompatActivity implements Constants {

    private static final String TAG = "myLogs";

    Calculator calc;
    private TextView outputTextView;

    ThemeChanger themeChanger = new ThemeChanger();

    private final int[] numberBtnsID = new int[]{R.id.button0, R.id.button1, R.id.button2,
            R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(themeChanger.getAppTheme(R.style.MyLightStyle));

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

    private void setBtnListeners() {

        for (int n : numberBtnsID) {
            findViewById(n).setOnClickListener(v -> {
                Button btn = (Button) v;
                outputTextView.setText(calc.setTextStr(btn.getText().toString()));
            });
        }

        findViewById(R.id.clearBtn).setOnClickListener(v -> {
            outputTextView.setText(DEFAULT_VALUE);
            calc = new Calculator();
        });

        initSymbolBtns(findViewById(R.id.sumBtn), Symbols.sum);
        initSymbolBtns(findViewById(R.id.divBtn), Symbols.div);
        initSymbolBtns(findViewById(R.id.multBtn), Symbols.mul);
        initSymbolBtns(findViewById(R.id.subtractBtn), Symbols.sub);

        findViewById(R.id.equalBtn).setOnClickListener(v -> {
            outputTextView.setText(calc.getResult());
        });

        findViewById(R.id.settingsImageButton).setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
//            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    private void initSymbolBtns(View button, Symbols s) {
        button.setOnClickListener(v -> {
            outputTextView.setText(calc.setSymbol(s));
        });
    }

    private void updateText() {
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
        calc = saveInstanceState.getParcelable(PARAM_TEXT_VIEW);
        if (calc == null) {
            calc = new Calculator();
        }
        updateText();
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
        outState.putParcelable(PARAM_TEXT_VIEW, calc);
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode != REQUEST_CODE) {
//            super.onActivityResult(requestCode, resultCode, data);
//            return;
//        }
//        if (resultCode == RESULT_OK && data != null) {
//            int num = data.getExtras().getInt(AppTheme);
//            themeChanger.setAppTheme(num);
//            //setBtnListeners();
//            //setTheme(num);
//        }
//
//    }


}
