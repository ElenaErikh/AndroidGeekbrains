package com.example.androidgeekbrains;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Calculator implements Parcelable {

    private StringBuilder str;
    private Symbols mathSymbol;
    public static final String DEFAULT_VALUE = "0";

    public Calculator() {
        str = new StringBuilder();
    }

    protected Calculator(Parcel in) {
        str.append(in.readString());
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    public StringBuilder getStr() {
        return str;
    }

    public StringBuilder setSymbol(Symbols s) {
        mathSymbol = s;
        return str.append(String.format(Locale.getDefault(), " %s ", mathSymbol.getSymbol()));
    }

    public String setTextStr(String s) {
        return String.valueOf(str.append(String.format(Locale.getDefault(), "%s", s)));
    }

    public String getResult() {
        if (str == null) {
            log("String is null");
        }
        return calcResult();
    }

    private String calcResult(){
        String[] parts = str.toString().split("\\s");
        if (parts.length != 3) {
            log("Invalid data" + str);
            return str.toString();
        }

        int val1 = Integer.parseInt(parts[0]);
        int val2 = Integer.parseInt(parts[2]);

        switch (mathSymbol) {
            case sum:
                return updateText(String.valueOf(val1 + val2));
            case sub:
                return updateText(String.valueOf(val1 - val2));
            case mul:
                return updateText(String.valueOf(val1 * val2));
            case div:
                try {
                    if (val2 != 0) {
                        return updateText(String.valueOf(val1 / val2));
                    }
                } catch (ArithmeticException e) {
                    e.printStackTrace();
                    return updateText(DEFAULT_VALUE);
                }
            default:
                str = new StringBuilder();
                return str.toString();
        }
    }

    private String updateText(String s){
        str = new StringBuilder(s);
        return str.toString();
    }

    private void log(@NonNull String text) {
        Log.d("CALCULATOR", text);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(str.toString());

    }
}
