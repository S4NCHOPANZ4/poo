package com.example;
import java.util.Random;
import java.util.Scanner;

public class GuessNum {
    private int numeroAleatorio;
    private int intentoUsuario;
    private boolean victory;
    private int intentos; 
    public void setNumeroAleatorio(int max, int min){
        Random rand = new Random();
        this.numeroAleatorio = rand.nextInt(max) + min;
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
    public void setVictory(int cpu,int user){
        this.victory = (cpu == user);
    }
    public boolean getVictory(){
        return victory;
    }
    public int getIntentos(){
        return intentos;
    }
    public void setIntentos(int intentos){
        this.intentos = intentos;
    }
}
