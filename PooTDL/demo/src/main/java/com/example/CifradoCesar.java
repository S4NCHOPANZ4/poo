package com.example;
import java.util.Scanner;

public class CifradoCesar {
    private Scanner scanner = new Scanner(System.in);
    private String palabra;
    private int option; 
    private int desplazamiento;
    private String resultado;

    private void iniciar() {
        switch (option) {
                case 1:
                    cifrar();
                    break;
                case 2:
                    descifrar();
                    break;
                default:
                    break;
            }
        }
    private void cifrar() {
        String resultado = procesarTexto(getPalabra(), getDesplazamiento(), true);
        setResultado(resultado);
    }
    private void descifrar() {
        String resultado = procesarTexto(getPalabra(), getDesplazamiento(), false);
        setResultado(resultado);
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
   

    public void setResultado(String resultado){
        this.resultado = resultado;
    }
    public String getResultado(){
        return resultado;
    }
    public void setOption(int option){
        this.option = option;
    }
    public int getOption(){
        return option;
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
        iniciar();
        this.desplazamiento = desplazamiento;
    }
}
