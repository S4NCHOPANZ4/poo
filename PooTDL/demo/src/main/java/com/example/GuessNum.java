package com.example;
import java.util.Random;
import java.util.Scanner;

public class GuessNum {
    private Scanner scanner = new Scanner(System.in);
    private int numeroAleatorio;
    private int intentoUsuario;

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            generarNumeroAleatorio();
            System.out.println("============ Bienvenido a Adivina el Número ============\n");
            pedirIntento();

            while (intentoUsuario != numeroAleatorio) {
                if (intentoUsuario < numeroAleatorio) {
                    System.out.println(intentoUsuario + " es menor al número a adivinar.");
                } else {
                    System.out.println(intentoUsuario + " es mayor al número a adivinar.");
                }
                pedirIntento();
            }

            System.out.println("¡Ding! ¡Ding! ¡Ding! " + intentoUsuario + " es correcto.\n");

            int opcion = validOpt(
                1, 2,
                "1. Volver a jugar\n2. Salir",
                "Digite una opción válida (1 - 2)"
            );

            if (opcion == 2) {
                salir = true;
            }
        }
        System.out.println("¡Gracias por jugar!");
    }

    private void generarNumeroAleatorio() {
        Random rand = new Random();
        numeroAleatorio = rand.nextInt(100) + 1; 
    }

    private void pedirIntento() {
        intentoUsuario = validOpt(
            1, 100,
            "Ingresa un número del 1 al 100:",
            "Ingrese una opción válida (1 - 100)\n"
        );
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
}
