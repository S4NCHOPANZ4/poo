package com.example;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static int validOpt(int min, int max, String menu, String errorMessage) {
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

    public static void main(String[] args) {
        
        JankenPon janken = new JankenPon();
        GuessNum guessNum = new GuessNum();
        ConvTermica convTermica = new ConvTermica();
        CifradoCesar cc = new CifradoCesar();
        CheckPalindrom cp = new CheckPalindrom();
        boolean exit = false;
        while (!exit) {
            System.out.println("====== Welcome Back ========");
            int opt = validOpt(1, 6,
                    "1. Adivinar número por búsqueda binaria\n" +
                    "2. Comprobar Palindromía\n" +
                    "3. Jugar Piedra, Papel o Tijera\n" +
                    "4. Cifrado César\n" +
                    "5. Conversión térmica\n" +
                    "6. Salir\n",
                    "Ingrese una opción válida\n");

            switch (opt) {
                case 1 -> guessNum.iniciar();
                case 2 -> cp.iniciar();
                case 3 -> janken.iniciar();
                case 4 -> cc.iniciar();
                case 5 -> convTermica.iniciar();
                case 6 -> exit = true;
            }
        }
    }
}
