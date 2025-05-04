package com.example;

public class CifradoCesar {
    private String palabra;
    private int option; 
    private int desplazamiento;
    private String resultado;

    public CifradoCesar() {
        this.palabra = "";
        this.option = 0;
        this.desplazamiento = 0;
        this.resultado = "";
    }

    public CifradoCesar(String palabra, int option, int desplazamiento) {
        this.palabra = palabra;
        this.option = option;
        this.desplazamiento = desplazamiento;
        this.resultado = "";
    }

    private void cifrar() {
        String resc = procesarTexto(getPalabra(), getDesplazamiento(), true);
        setResultado(resc);
    }

    private void descifrar() {
        String resd = procesarTexto(getPalabra(), getDesplazamiento(), false);
        setResultado(resd);
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

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        if (this.option == 1) {
            cifrar(); 
        } else if (this.option == 2) {
            descifrar(); 
        }
        return resultado;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getOption() {
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
        this.desplazamiento = desplazamiento;

    }
}
