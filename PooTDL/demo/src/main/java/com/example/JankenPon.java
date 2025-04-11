package com.example;

import java.util.Random;
import java.util.Scanner;

public class JankenPon {
    private final Scanner scanner = new Scanner(System.in);
    private final String[] opciones = {"Piedra", "Papel", "Tijera"};
    private final Random rand = new Random();
    private int eleccionUsuario;
    private int eleccionCpu;

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n================== Bienvenido a JankenPon ==================");

            mostrarOpciones();
            eleccionUsuario = validOpt(1, 4, "Elige una opción: ", "Ingrese una opción válida (1 - 4)");

            if (eleccionUsuario == 4) {
                salir = true;
                break;
            }

            eleccionCpu = rand.nextInt(3) + 1;  // Genera 1, 2 o 3
            mostrarResultado();

            int continuar = validOpt(1, 2, "1. Volver a jugar\n2. Salir", "Ingrese una opción válida (1 - 2)");
            if (continuar == 2) {
                salir = true;
            }
        }
    }
    private void mostrarOpciones() {
        System.out.println("1. Piedra");
        System.out.println("2. Papel");
        System.out.println("3. Tijera");
        System.out.println("4. Salir");
    }
    private void mostrarResultado() {
        String jugador = opciones[eleccionUsuario - 1];
        String cpu = opciones[eleccionCpu - 1];

        System.out.println("Tu: " + jugador + " | CPU: " + cpu);

        if (eleccionUsuario == eleccionCpu) {
            System.out.println("Empate!");
        } else if (
            (eleccionUsuario == 1 && eleccionCpu == 3) ||
            (eleccionUsuario == 2 && eleccionCpu == 1) ||
            (eleccionUsuario == 3 && eleccionCpu == 2)
        ) {
            System.out.println("¡Victoria!");
        } else {
            System.out.println("Derrota!");
        }
    }
    public int validOpt(int min, int max, String menu, String errorMessage) {
        while (true) {
            try {
                System.out.println(menu);
                System.out.print("Seleccione una opción: ");
                String input = scanner.nextLine().trim();
                int opt = Integer.parseInt(input);
                
                if (opt >= min && opt <= max) {
                    return opt;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
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

    public void setEleccionCpu(int eleccionCpu) {
        this.eleccionCpu = eleccionCpu;
    }

    
}
