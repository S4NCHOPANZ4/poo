package com.example;
import java.util.Scanner;

public class ConvTermica {
    private double temp;
    private double conversion;
    private int option;
    public double iniciar(int opt) {
            switch (opt) {
                case 1:
                    System.out.println("============ Celsius a Fahrenheit ============\n");
                    return celFar();

                case 2:
                    System.out.println("============ Fahrenheit a Celsius ============\n");
                    return farCel();

                default:
                    return 10;
            }
    }

    private double celFar() {
        double resultado = (getTemp() * 9.0 / 5.0) + 32;
        return resultado;
    }

    private double farCel() {
        double resultado = (getTemp() - 32) * 5.0 / 9.0;
        return resultado;
    }

    public void setTemp(double val) {
        this.temp = val;
    }

    public double getTemp() {
        return this.temp;
    }

    public void setConversion(int opt) {
        this.conversion = iniciar(opt);
    }

    public double getConversion() {
        return this.conversion;
    }

    public void setOption(int opt){
        this.option = opt;
    }

    public int getOption(){
        return option;
    }
}
