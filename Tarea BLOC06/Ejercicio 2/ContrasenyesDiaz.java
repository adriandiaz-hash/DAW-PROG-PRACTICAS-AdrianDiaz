package com.mycompany.contrasenyesdiaz;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;

/**[T]@author Adrián Díaz García.
Solicita al usuario, al inicio de la ejecución, el número de contraseñas a generar, así como la longitud de las contraseñas.
Genera de forma aleatoria un número determinado de contraseñas y las almacene en un HashSet.
Y finaliza cuando el HashSet contenga el número de contraseñas indicado por el usuario, o sea imposible generarlas.*/
 
public class ContrasenyesDiaz {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<String> llistaContrasenyes = new HashSet<>();
        int nombreContrasenyes = 0;
        int longitudContrasenyes = 0;
        int intentosPorContrasenya = 0;
        int colissions = 0;
        Random random = new Random();
        
        System.out.println("Introdueix el nombre de contrasenyes a generar: ");
        nombreContrasenyes = sc.nextInt();
        sc.nextLine();
        System.out.println("Introdueix la longitud de les contrasenyes a generar: ");
        longitudContrasenyes = sc.nextInt();
        sc.nextLine();
        
        while (llistaContrasenyes.size() < nombreContrasenyes) {
            char[] password = new char[longitudContrasenyes];
            for (int j = 0; j < longitudContrasenyes; j++) {    
                char c = (char) (random.nextInt(26) + 'a');    
                if (random.nextBoolean()) {
                    c = Character.toUpperCase(c);
                }   
                if (random.nextBoolean()) {
                    password[j] = c;    
                } else {        
                    password[j] = (char) ('0' + random.nextInt(10));    
                }
            }
            String word = new String(password);
            if (llistaContrasenyes.add(word)) {
                System.out.println("Contrasenya amagatzemada correctament.");
                intentosPorContrasenya = 0;
            } else {
                System.out.println("¡Error! Contrasenya ja amagatzemada.");
                intentosPorContrasenya++;
                colissions++;
            }
            if (intentosPorContrasenya == 10) {
                break;
            }
        }
        
        if (llistaContrasenyes.size() == nombreContrasenyes) {
            System.out.println("\n--- INFORME DE LA OPERACIÓN ---");
            System.out.println("Totes les contrasenyes sol·licitades s'han generat amb èxit.");
            System.out.println("Llista de contrasenyes: " + llistaContrasenyes);
            
            int totalGeneradas = nombreContrasenyes + colissions;
            double porcentajeColisiones = ((double) colissions / totalGeneradas) * 100;
            
            System.out.println("Percentatge de col·lisions: " + porcentajeColisiones + "%");   
        } else {
            System.out.println("\n--- INFORME DE LA OPERACIÓN ---");
            System.out.println("Operació avortada. No s'han pogut generar totes les contrasenyes sol·licitades a causa de l'alta taxa de col·lisions.");
        }
    }
}