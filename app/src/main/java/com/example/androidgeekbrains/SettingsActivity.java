package com.example.androidgeekbrains;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class SettingsActivity extends AppCompatActivity implements Constants{

    ThemeChanger themeChanger = new ThemeChanger();

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(themeChanger.getAppTheme(R.style.MyLightStyle));

        setContentView(R.layout.activity_settings);

        initView();
    }

    private void initView() {

        initRadioButton(findViewById(R.id.radioBtnCoolStyle), MyCoolDarkStyle);
        initRadioButton(findViewById(R.id.radioBtnLightStyle), LightStyle);

        RadioGroup rg = findViewById(R.id.radioGroup);

        ((MaterialRadioButton) rg.getChildAt(themeChanger.getCodeStyle(MyCoolDarkStyle))).setChecked(true);

        findViewById(R.id.backButton).setOnClickListener(view -> {
//            Intent intentResult = new Intent(this, MainActivity.class);
//            intentResult.putExtra(AppTheme, codeNumber);
//            setResult(RESULT_OK, intentResult);
            finish();
        });
    }

    private void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(v -> {
            themeChanger.setAppTheme(codeStyle);
            recreate();
        });
     }


}
