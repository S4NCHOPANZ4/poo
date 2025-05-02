package com.example;
import java.util.Random;
import java.util.Scanner;

public class GuessNum {
    private int numeroAleatorio;
    private int intentoUsuario;
    private boolean victory;
    private int intentos;
    private final Random rand = new Random();

    // Constructor sin parámetros
    public GuessNum() {
        this.numeroAleatorio = rand.nextInt(100) + 1;
        this.intentoUsuario = 0;
        this.victory = false;
        this.intentos = 0;
    }

    // Constructor con parámetros
    public GuessNum(int min, int max) {
        Random rand = new Random();
        this.numeroAleatorio = rand.nextInt(max - min + 1) + min;
        this.intentoUsuario = 0;
        this.victory = false;
        this.intentos = 0;
    }

    public void setNumeroAleatorio(int max, int min) {
        Random rand = new Random();
        this.numeroAleatorio = rand.nextInt(max - min + 1) + min;
    }

    public int getNumeroAleatorio() {
        return numeroAleatorio;
    }

    public void setNumeroAleatorio(int numeroAleatorio) {
        this.numeroAleatorio = numeroAleatorio;
    }

    public int getIntentoUsuario() {
        return intentoUsuario;
    }

    public void setIntentoUsuario(int intentoUsuario) {
        this.intentoUsuario = intentoUsuario;
    }

    public void setVictory(int cpu, int user) {
        this.victory = (cpu == user);
    }

    public boolean getVictory() {
        return victory;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
}
