package com.example;
import java.util.Scanner;

public class ConvTermica {
    private Scanner scanner = new Scanner(System.in);
    private double temp;
    private double conversion;

    public void iniciar() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n================== Bienvenido a Conversión Térmica ==================");
            int opt = validOpt(
                1, 3,
                "1. Celsius a Fahrenheit\n2. Fahrenheit a Celsius\n3. Salir",
                "Ingrese una opción válida (1 - 3)"
            );

            switch (opt) {
                case 1:
                    System.out.println("============ Celsius a Fahrenheit ============\n");
                    double celsius = validOpt(-237, 1000000000, 
                        "¿Qué temperatura en Celsius deseas convertir a Fahrenheit? \n", 
                        "Ingrese un valor válido");
                    setTemp(celsius);
                    celFar();
                    break;

                case 2:
                    System.out.println("============ Fahrenheit a Celsius ============\n");
                    double fahrenheit = validOpt(-459, 1000000000,
                        "¿Qué temperatura en Fahrenheit deseas convertir a Celsius? \n", 
                        "Ingrese un valor válido");
                    setTemp(fahrenheit);
                    farCel();
                    break;

                default:
                    exit = true;
                    break;
            }

            if (!exit) {
                int opt2 = validOpt(1, 2,
                    "1. Volver a convertir\n2. Salir",
                    "Ingrese una opción válida (1 - 2)");
                if (opt2 == 2) {
                    exit = true;
                }
            }
        }
    }

    private void celFar() {
        double resultado = (getTemp() * 9.0 / 5.0) + 32;
        setConversion(resultado);
        if (resultado <= -459.67) {
            System.out.println("¡Qué frío! Alcanzaste el cero absoluto.\n-459.67 °F");
        } else {
            System.out.println(getTemp() + " °C = " + getConversion() + " °F");
        }
    }

    private void farCel() {
        double resultado = (getTemp() - 32) * 5.0 / 9.0;
        setConversion(resultado);
        if (resultado <= -273.15) {
            System.out.println("¡Qué frío! Alcanzaste el cero absoluto.\n-273.15 °C");
        } else {
            System.out.println(getTemp() + " °F = " + getConversion() + " °C");
        }
    }

    public void setTemp(double val) {
        this.temp = val;
    }

    public double getTemp() {
        return this.temp;
    }

    public void setConversion(double val) {
        this.conversion = val;
    }

    public double getConversion() {
        return this.conversion;
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
