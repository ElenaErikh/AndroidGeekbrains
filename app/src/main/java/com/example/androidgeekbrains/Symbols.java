package com.example.androidgeekbrains;

public enum Symbols {
    sum("+"), sub("-"), mul("*"), div("/");

    private String symbol;

    Symbols(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
