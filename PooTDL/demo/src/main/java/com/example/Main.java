package com.example;
import java.util.Random;
import java.util.Scanner; 

public class Main {
    public static void main(String[] args) {
        Taller taller = new Main().new Taller();
        taller.Menu();
    }

    public class Taller{
        Scanner scanner = new Scanner(System.in);
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
        public void Menu(){
            boolean exit = false; 
            while (!exit){
                System.out.println("====== Welcome Back ========");            
                int opt = validOpt(1,6, "1. Adivinar numero por bianry search\n2. Comprobar Palindromia \n3. Jugar Piedra Papel  o Tijera \n4. Cifrado Cesar\n5. Conversion Termica \n6. Salir\n", 
                "Ingrese una opcion valida\n");
                switch (opt) {
                    case 1:
                        guessNum();
                        break;
                    case 2:
                        checkPalindromo();
                        break;
                    case 3:
                        jankenpon();
                        break;
                    case 4:
                        cifradoCesar();
                        break;
                    case 5:
                        convTermica();
                        break;    
                    case 6:
                        exit = true;
                        break;   
                    default:
                        return;
                }     
            }
        } 
        public void guessNum(){
            boolean exit = false; 
            while(!exit){
            Random rand = new Random();
            int ramdomN = rand.nextInt(100);
            int opt = validOpt(1,100, "Ingresa un numero del 1 al 100\n", 
            "Ingrese una opcion valida numero (1 - 100)\n");
            while (opt != ramdomN){
                if(opt < ramdomN){
                    opt = validOpt(1,100, opt + " es menor al numero a adivinar\n", 
                    "Ingrese una opcion valida numero (1 - 100)\n");
                }
                else if((opt > ramdomN)){
                    opt = validOpt(1,100, opt + " es mayor al numero a adivinar\n", 
                    "Ingrese una opcion valida numero (1 - 100)\n");
                }
            }
            System.out.println("Ding! Ding! Ding! "+opt+" es correcto \n" );
            int opt2 = validOpt(1,2,"1. Volver a jugar\n2. Salir", "Digite una opcion valida (1 - 2)\n");
            if (opt2 == 2){
                exit = true;
            }
            }
            System.out.println("hola");
        }
        public void checkPalindromo() {
            boolean exit = false; 
            while (!exit) {
                System.out.println("\n================== Bienvenido a The Palindrome Checker ==================");
                System.out.print("Ingrese una palabra o frase: ");
                String word = scanner.nextLine().trim();
                String word2 = word.replace(" ", "");
                if (word2.isEmpty()) {
                    System.out.println("Error: No ingresó ningún texto. Intente nuevamente.");
                    continue;
                }
                boolean digitsOrSpecials = false;
                for (int i = 0; i < word2.length(); i++){
                    char c = word.charAt(i);
                    if (Character.isDigit(c)) {
                        digitsOrSpecials = true;
                    } else if (!Character.isLetter(c) && c != ' ') {
                        digitsOrSpecials = true;
                        break;
                    }
                }
                if (digitsOrSpecials){
                    System.out.println("\nRecuerda que es una frase, no agregues numeros ni caracteres especiales, wink! wink!\n");
                    continue;
                }
                String revWord = new StringBuilder(word2).reverse().toString();
                if (revWord.equalsIgnoreCase(word2)) {
                    System.out.println("\nDing! Ding! Ding! '" + word + "' es un Palíndromo!");
                } else {
                    System.out.println("\nWomp! Womp! '" + word + "' NO es un Palíndromo.");
                }
                System.out.println("\n¿Qué desea hacer ahora?");
                int opt = validOpt(1, 2, "1. Volver a jugar\n2. Salir", "Ingrese una opción válida (1-2)\n");
                if (opt == 2) {
                    exit = true;
                }
            }
        }
        public void jankenpon(){
            boolean exit = false;
            String[] options = {"Piedra" , "Papel" , "Tijera"};
            while (!exit){
                System.out.println("\n================== Bienvenido a JankenPon ==================");
                int opt = validOpt(1,4, "1. Piedra\n2. Papel\n3. Tijera\n4. Salir\n","Ingrese una opcion valida (1 - 4)");
                Random rand = new Random();
                int ramdomN = rand.nextInt(2)+1;
                if(opt == 4){
                    exit = true;
                    break;
                }
                else if(opt == ramdomN){
                    System.out.println("Tu: "+options[opt-1]+ " Cpu: "+options[ramdomN-1]+"\n Empate!");
                }
                else if (opt == 1 && ramdomN == 3){
                    System.out.println("Tu: "+options[opt-1]+ " Cpu: "+options[ramdomN-1]+"\n Victoria!");
                }
                else if (opt == 2 && ramdomN == 1){
                    System.out.println("Tu: "+options[opt-1]+ " Cpu: "+options[ramdomN-1]+"\n Victoria!");
                }
                else if (opt == 3 && ramdomN == 2){
                    System.out.println("Tu: "+options[opt-1]+ " Cpu: "+options[ramdomN-1]+"\n Victoria!");
                }
                else{
                    System.out.println("Tu: "+options[opt-1]+ " Cpu: "+options[ramdomN-1]+"\n Derrota!");
                }
                int opt2 = validOpt(1,2, "1. Volver a jugar\n2. Salir","Ingrese una opcion valida (1 - 2)");
                if (opt2 == 2){
                    exit = true;
                }
            }

        }
        public void convTermica(){
            boolean exit = false;
            while(!exit){
                System.out.println("\n================== Bienvenido a Conversion Termica ==================");
                int opt = validOpt(1,3, "1. Celsius a Fahrenheit\n2. Fahrenheit a Celsius\n3.Salir","Ingrese una opcion valida (1 - 3)");
                switch (opt) {
                    case 1:
                        System.out.println("============ Celsius a Fahrenheit ============\n");
                        int temp = validOpt(-237,  1000000000, "Que temperatura en Celsius deseas convertir a Fahrenheit: \n", "Ingrese un valor valido");
                        double res = (((double) temp * (double) 9/5) + (double) 32);
                        if (res <= -237){
                            System.out.println("Que frio, alcanzaste el cero absoluto\n"+-237);
                            break;
                        }
                        System.out.println(temp+" °C = "+res+" °F");
                        break;
                    case 2: 
                        System.out.println("============ Fahrenheit a Celsius ============\n");
                        int temp2 = validOpt(-459,  1000000000, "Que temperatura en Fahrenheit deseas convertir a Celsius: \n", "Ingrese un valor valido");
                        double res2 = (((double) temp2 - (double) 32) * (double) 5/9);
                        if (res2 <= -459){
                            System.out.println("Que frio, alcanzaste el cero absoluto\n"+-459);
                            break;
                        }
                        System.out.println(temp2+" °F = "+res2+" °C");
                        break;
                    default:
                        exit = true;
                        break;
                }
                int opt2 = validOpt(1,2, "1. Volver a convertir\n2. Salir","Ingrese una opcion valida (1 - 2)");
                if (opt2 == 2){
                    exit = true;
                }               
            }
        }
        public void cifradoCesar(){
            boolean exit = false;
            while(!exit){
                System.out.println("============ Bienvenido al sistema de cifrado cesar ============\n");
                int opt = validOpt(1, 3, "1. Cifrar\n2. Decifrar\n3. Salir\n", "Ingresa un valor valido (1 - 2)");
                switch (opt) {
                    case 1:
                        System.out.print("Que oracion te gustaria cifrar\n ");
                        String word = scanner.nextLine().trim();
                        int cifrado = validOpt(1, 99, "Cuantas pocisiones te gustaria moverte?\n", "Ingresa una opcion valida (1 - 99)\nCuantas pocisiones te gustaria moverte?\n");
                        int base = 65;
                        String res = "";
                        for (int i = 0; i < word.length(); i++) {
                            char c = word.charAt(i);
                            if (Character.isLetter(c)) {
                                base = Character.isUpperCase(c) ? 65 : 97; // A = 65 -  a = 97
                                char newChar = (char) ((c - base + cifrado) % 26 + base);
                                res += newChar;
                            } 
                            else if (Character.isDigit(c)) {
                                char newChar = (char) (((c - 48 + cifrado) % 10) + 48);
                                res += newChar;
                            } 
                            else {
                                res += c; 
                            }
                        }
                        System.out.println("Tu palabra es\n"+res+"\nGuarda el secreto");
                        break;
                    case 2: 
                        System.out.print("Que oracion te gustaria cifrar\n ");
                        String word2 = scanner.nextLine().trim();
                        int cifrado2 = validOpt(1, 99, "Cuantas pocisiones te gustaria moverte?\n", "Ingresa una opcion valida (1 - 99)\nCuantas pocisiones te gustaria moverte?\n");
                        int base2 = 65;
                        String res2 = "";
                        for (int i = 0; i < word2.length(); i++) {
                            char c = word2.charAt(i);
                            if (Character.isLetter(c)) {
                                base2 = Character.isUpperCase(c) ? 65 : 97; // A = 65 -  a = 97
                                char newChar = (char) ((c - base2 - cifrado2) % 26 + base2);
                                res2 += newChar;
                            } 
                            else if (Character.isDigit(c)) {
                                char newChar = (char) (((c - 48 - cifrado2) % 10) + 48);
                                res2 += newChar;
                            } 
                            else {
                                res2 += c; 
                            }
                        }
                        System.out.println("Ru palabra es\n"+res2+"\nGuarda el secreto");
                            break;
                    default:
                        exit = true;
                }
                int opt2 = validOpt(1,2, "1. Volver a elegir Cifrar o Decifrar\n2. Salir","Ingrese una opcion valida (1 - 2)");
                if (opt2 == 2){
                    exit = true;
                }     
            }

        }
    }
}