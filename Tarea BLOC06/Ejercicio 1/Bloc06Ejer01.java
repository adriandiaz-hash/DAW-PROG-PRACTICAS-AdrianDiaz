package com.mycompany.bloc06ejer01;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**[T] @author Adrián Díaz García.
Lee palabras introducidas por teclado y las analiza contabilizándolas por su misma inicial*/


public class Bloc06Ejer01{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        ArrayList<String> lista = new ArrayList<>();
        HashMap<Character, Integer> contadorIniciales = new HashMap<>();
        String palabra;
        
       do{
           System.out.println("Introduce una palabra (o escribe 'FINALIZAR' para terminar): ");
           
           palabra = leerPalabraValida(sc);
           
            if (!palabra.equalsIgnoreCase("FINALIZAR")) {
                lista.add(palabra);
                char inicial = Character.toUpperCase(palabra.charAt(0));
                contadorIniciales.put(inicial, contadorIniciales.getOrDefault(inicial, 0) + 1);
            }
       } while (!palabra.equalsIgnoreCase("FINALIZAR"));
       
       sc.close();
       
       System.out.println("Lista completa de palabras: " + lista);
       
       System.out.println("Recuento de iniciales:");
       for (Character inicial : contadorIniciales.keySet()) {
            int cantidad = contadorIniciales.get(inicial);
    
            if (cantidad == 1) {
                System.out.println(" - Letra " + inicial + ": " + cantidad + " aparición");
            } else {
                System.out.println(" - Letra " + inicial + ": " + cantidad + " apariciones");
            }
        }
    }
    public static String leerPalabraValida(Scanner sc) {
        String entrada = sc.nextLine().trim();
        while (!entrada.matches("\\p{L}+")) {
            System.out.println("¡Error! Debes introducir una palabra válida, sin espacios ni cifras.");
            entrada = sc.nextLine().trim();
        }
        return entrada;
    }
}