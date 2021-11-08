package com.example.androidgeekbrains;

import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class ThemeChanger extends AppCompatActivity implements Constants {

    private static final String TAG = "myLogs";

    protected int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    protected int getCodeStyle(int codeStyle){
        return getSharedPreferences(NameSharedPreference, MODE_PRIVATE).getInt(AppTheme, codeStyle);
    }

    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        sharedPref.edit()
                .putInt(AppTheme, codeStyle)
                .apply();
    }

    private int codeStyleToStyleId(int codeStyle){
        if (codeStyle == LightStyle) {
            return R.style.MyLightStyle;
        }
        return R.style.MyCoolStyle;
    }



}
