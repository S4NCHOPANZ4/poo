package com.example;

import java.util.Scanner;

public class CheckPalindrom {
    private final Scanner scanner = new Scanner(System.in);
    private String fraseOriginal;
    private String fraseLimpia;

    public void iniciar() {
        System.out.println("\n================== Bienvenido a The Palindrome Checker ==================");
        boolean salir = false;

        while (!salir) {
            leerFrase();

            if (fraseLimpia.isEmpty()) {
                System.out.println("Error: No ingresó ningún texto. Intente nuevamente.");
                continue;
            }

            if (!esValida()) {
                System.out.println("\nRecuerda que es una frase, no agregues números ni caracteres especiales, wink! wink!\n");
                continue;
            }

            mostrarResultado();

            int opcion = validOpt(1, 2, "\n¿Qué desea hacer ahora?\n1. Volver a jugar\n2. Salir", 
                                  "Ingrese una opción válida (1-2)\n");
            if (opcion == 2) {
                salir = true;
            }
        }
    }
    private void leerFrase() {
        System.out.print("Ingrese una palabra o frase: ");
        fraseOriginal = scanner.nextLine().trim();
        fraseLimpia = fraseOriginal.replace(" ", "").toLowerCase();
    }

    private boolean esValida() {
        for (char c : fraseLimpia.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private void mostrarResultado() {
        String reversa = new StringBuilder(fraseLimpia).reverse().toString();

        if (reversa.equals(fraseLimpia)) {
            System.out.println("\nDing! Ding! Ding! '" + fraseOriginal + "' es un Palíndromo!");
        } else {
            System.out.println("\nWomp! Womp! '" + fraseOriginal + "' NO es un Palíndromo.");
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
    public String getFraseOriginal() {
        return fraseOriginal;
    }

    public void setFraseOriginal(String fraseOriginal) {
        this.fraseOriginal = fraseOriginal;
    }

    public String getFraseLimpia() {
        return fraseLimpia;
    }

    public void setFraseLimpia(String fraseLimpia) {
        this.fraseLimpia = fraseLimpia;
    }
}
