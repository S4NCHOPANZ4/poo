package com.example;

import java.util.Scanner;

public class CheckPalindrom {
    private String fraseOriginal;
    private String resultado; 
    private String fraseLimpia;

    public CheckPalindrom() {
        this.fraseOriginal = "";
        this.resultado = "";
        this.fraseLimpia = "";
    }

    public CheckPalindrom(String fraseOriginal) {
        this.fraseOriginal = fraseOriginal;
        this.fraseLimpia = fraseOriginal.replace(" ", "").toLowerCase();
        this.resultado = mostrarResultado(fraseLimpia);
    }

    private String mostrarResultado(String fraseLimpia) {
        String reversa = new StringBuilder(fraseLimpia).reverse().toString();

        if (reversa.equals(fraseLimpia)) {
            return ("\nDing! Ding! Ding! '" + fraseOriginal + "' es un Palíndromo!");
        } else {
            return ("\nWomp! Womp! '" + fraseOriginal + "' NO es un Palíndromo.");
        }
    }

    public String getFraseOriginal() {
        return fraseOriginal;
    }

    public void setFraseOriginal(String frase) {
        this.fraseOriginal = frase;
    }

    public String getFraseLimpia() {
        return fraseLimpia;
    }

    public void setFraseLimpia(String fraseLimpia) {
        this.fraseLimpia = fraseLimpia.replace(" ", "").toLowerCase();
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String fraseLimpia) {
        this.resultado = mostrarResultado(fraseLimpia);
    }
}
