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
                case 1 -> usarGuessNum();
                case 2 -> usarCp();
                case 3 -> usarJanken();
                case 4 -> usarCifradoCesar();
                case 5 -> usarConvTermica();
                case 6 -> exit = true;
            }
        }
    }
    public static void usarJanken(){
        JankenPon janken = new JankenPon();
        while (true) {
            System.out.println("\n================== Bienvenido a JankenPon ==================");
            janken.setEleccionUsuario(validOpt(1, 4, "Elige una opción: \n1. Piefra\n2. Papel\n3. Tijera", "Ingrese una opción válida (1 - 4)"));
            janken.setEleccionCpu(3,1);
            janken.setResultado(janken.getEleccionUsuario(), janken.getEleccionCpu());
            System.out.println(janken.getResultado());
            int opt= validOpt(1, 2, "1. Volver a elegir jugar\n2. Salir", "Ingrese una opción válida (1 - 2)");
            if (opt == 2) {
                break;
            }
        }
    }
    public static void usarCp(){
        CheckPalindrom cp = new CheckPalindrom();
        while(true){
            System.out.println("\n================== Bienvenido a The Palindrome Checker ==================");
            System.out.println("Ingresa tu posible palindromo \n");
            cp.setFraseOriginal(scanner.nextLine().trim());
            cp.setFraseLimpia(cp.getFraseOriginal());
            for (char c : cp.getFraseLimpia().toCharArray()) {
                if (!Character.isLetter(c)) {
                    System.out.println("\nRecuerda que es una frase, no agregues números ni caracteres especiales, wink! wink!\n");
                    continue;
                }
            }
            if(cp.getFraseLimpia().isEmpty()){
                System.out.println("Error: No ingresó ningún texto. Intente nuevamente.");
                continue;
            }
            cp.setResultado(cp.getFraseLimpia());
            System.out.println(cp.getResultado());

            int opt= validOpt(1, 2, "1. Volver a elegir Frase\n2. Salir", "Ingrese una opción válida (1 - 2)");
            if (opt == 2) {
                break;
            }
        }
    }
    public static void usarCifradoCesar() {
        CifradoCesar cc = new CifradoCesar();
        while (true) {
            System.out.println("============ Bienvenido al sistema de cifrado cesar ============\n");
            cc.setOption(validOpt(1, 3, "1. Cifrar\n2. Descifrar\n3. Salir\n", "Ingresa un valor válido (1 - 3)"));
            System.out.print("¿Con qué operación te gustaría trabajar?\n> ");
            cc.setPalabra(scanner.nextLine().trim());
            cc.setDesplazamiento(validOpt(1, 99, "¿Cuántas posiciones te gustaría moverte?\n> ",
                    "Ingresa una opción válida (1 - 99)\n¿Cuántas posiciones te gustaría moverte?\n> "));
            System.out.println("\nTu palabra es:\n" + cc.getResultado() + "\n¡Guarda el secreto!\n");
            int opt = validOpt(1, 2, "1. Volver a elegir Cifrar o Descifrar\n2. Salir", "Ingrese una opción válida (1 - 2)");
            if (opt == 2) {
                break;
            }
        }
    }
    public static void usarConvTermica(){
        ConvTermica convTermica = new ConvTermica();
        while (true) {
            System.out.println("\n================== Bienvenido a Conversión Térmica ==================");
            convTermica.setOption(validOpt(1, 2,
            "1. Celsius a Fahrenheit\n2. Fahrenheit a Celsius\n",
            "Ingrese una opción válida (1 - 2)"));
            convTermica.setTemp(validOpt(-237, 1000000000, 
            "¿Qué temperatura en Celsius deseas convertir? \n", 
            "Ingrese un valor válido"));
            convTermica.setConversion(convTermica.getOption());
            System.out.println(convTermica.getConversion());
            int opt= validOpt(1, 2, "1. Volver a convertir\n2. Salir", "Ingrese una opción válida (1 - 2)");
            if (opt == 2) {
                break;
            }
        }
    }
    public static void usarGuessNum(){
        GuessNum guessNum = new GuessNum();
        while(true){
            System.out.println("============ Bienvenido a Adivina el Número ============\n");
            guessNum.setNumeroAleatorio(100,1);
            guessNum.setIntentoUsuario(validOpt(
                1, 100,
                "Ingresa un número del 1 al 100:",
                "Ingrese una opción válida (1 - 100)\n"
            ));
            guessNum.setIntentos(1);
            do{
                if ( guessNum.getIntentoUsuario() < guessNum.getNumeroAleatorio()) {
                    System.out.println( guessNum.getIntentoUsuario() + " es menor al número a adivinar.");
                } else {
                    System.out.println( guessNum.getIntentoUsuario() + " es mayor al número a adivinar.");
                }
                guessNum.setIntentoUsuario(validOpt(
                    1, 100,
                    "Ingresa un número del 1 al 100:",
                    "Ingrese una opción válida (1 - 100)\n"
                ));
            guessNum.setIntentos(guessNum.getIntentos()+1);

            }while(guessNum.getNumeroAleatorio() != guessNum.getIntentoUsuario());
            System.out.println("¡Ding! ¡Ding! ¡Ding! " + guessNum.getIntentoUsuario() + " es correcto.\nEn "+guessNum.getIntentos()+" Intentos");
            System.out.println("¡Gracias por jugar!");

            int opt= validOpt(1, 2, "1. Volver a adivinar\n2. Salir", "Ingrese una opción válida (1 - 2)");
            if (opt == 2) {
                break;
            } 
        }
    }
}
