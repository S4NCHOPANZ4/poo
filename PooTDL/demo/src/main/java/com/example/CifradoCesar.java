package com.example;
import java.util.Scanner;

public class CifradoCesar {
    private Scanner scanner = new Scanner(System.in);
    private String palabra;
    private int desplazamiento;

    public void iniciar() {
        boolean exit = false;
        while (!exit) {
            System.out.println("============ Bienvenido al sistema de cifrado cesar ============\n");
            int opt = validOpt(1, 3, "1. Cifrar\n2. Descifrar\n3. Salir\n", "Ingresa un valor válido (1 - 3)");

            switch (opt) {
                case 1:
                    cifrar();
                    break;
                case 2:
                    descifrar();
                    break;
                default:
                    exit = true;
            }

            int opt2 = validOpt(1, 2, "1. Volver a elegir Cifrar o Descifrar\n2. Salir", "Ingrese una opción válida (1 - 2)");
            if (opt2 == 2) {
                exit = true;
            }
        }
    }
    private void cifrar() {
        System.out.print("¿Qué oración te gustaría cifrar?\n> ");
        setPalabra(scanner.nextLine().trim());

        setDesplazamiento(validOpt(1, 99, "¿Cuántas posiciones te gustaría moverte?\n> ",
                "Ingresa una opción válida (1 - 99)\n¿Cuántas posiciones te gustaría moverte?\n> "));

        String resultado = procesarTexto(getPalabra(), getDesplazamiento(), true);
        System.out.println("\nTu palabra cifrada es:\n" + resultado + "\n¡Guarda el secreto!\n");
    }
    private void descifrar() {
        System.out.print("¿Qué oración te gustaría descifrar?\n> ");
        setPalabra(scanner.nextLine().trim());

        setDesplazamiento(validOpt(1, 99, "¿Cuántas posiciones te gustaría moverte?\n> ",
                "Ingresa una opción válida (1 - 99)\n¿Cuántas posiciones te gustaría moverte?\n> "));

        String resultado = procesarTexto(getPalabra(), getDesplazamiento(), false);
        System.out.println("\nTu palabra descifrada es:\n" + resultado + "\n¡Guarda el secreto!\n");
    }

    private String procesarTexto(String texto, int desplazamiento, boolean cifrar) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (Character.isLetter(c)) {
                int base = Character.isUpperCase(c) ? 'A' : 'a';
                int offset = (cifrar ? desplazamiento : -desplazamiento);
                char newChar = (char) (((c - base + offset + 26) % 26) + base);
                resultado.append(newChar);
            } else if (Character.isDigit(c)) {
                int offset = (cifrar ? desplazamiento : -desplazamiento);
                char newChar = (char) (((c - '0' + offset + 10) % 10) + '0');
                resultado.append(newChar);
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
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

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getDesplazamiento() {
        return desplazamiento;
    }

    public void setDesplazamiento(int desplazamiento) {
        this.desplazamiento = desplazamiento;
    }
}
