package com.example;

import java.util.Random;

public class JankenPon {
    private final String[] opciones = {"Piedra", "Papel", "Tijera"};
    private final Random rand = new Random();
    private int eleccionUsuario;
    private int eleccionCpu;
    private String resultado;

    public JankenPon() {
        this.eleccionUsuario = 0;
        this.eleccionCpu =  rand.nextInt(3) + 1;
        this.resultado = "";
    }

    public JankenPon(int eleccionUsuario) {
        this.eleccionUsuario = eleccionUsuario;
        this.eleccionCpu =  rand.nextInt(3) + 1;
        this.resultado = "";
    }

    private String mostrarResultado(int eleccionUsuario, int eleccionCpu) {
        String jugador = opciones[eleccionUsuario - 1];
        String cpu = opciones[eleccionCpu - 1];

        if (eleccionUsuario == eleccionCpu) {
            String result = ("Tu: " + jugador + " | CPU: " + cpu + "\n ¡Empate!");
            return result;
        } else if (
            (eleccionUsuario == 1 && eleccionCpu == 3) ||
            (eleccionUsuario == 2 && eleccionCpu == 1) ||
            (eleccionUsuario == 3 && eleccionCpu == 2)
        ) {
            String result = ("Tu: " + jugador + " | CPU: " + cpu + "\n ¡Victoria!");
            return result;
        } else {
            String result = ("Tu: " + jugador + " | CPU: " + cpu + "\n ¡Derrota!");
            return result;
        }
    }

    public int getEleccionUsuario() {
        return eleccionUsuario;
    }

    public void setEleccionUsuario(int eleccionUsuario) {
        this.eleccionUsuario = eleccionUsuario;
    }

    public int getEleccionCpu() {
        return eleccionCpu;
    }

    public void setEleccionCpu(int max, int min) {
        this.eleccionCpu = rand.nextInt(max) + min;
    }

    public void setResultado(int user, int cpu) {
        this.resultado = mostrarResultado(user, cpu);
    }

    public String getResultado() {
        return resultado;
    }
}
