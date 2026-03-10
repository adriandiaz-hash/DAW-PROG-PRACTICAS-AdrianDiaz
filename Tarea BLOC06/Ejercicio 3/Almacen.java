package com.mycompany.stockdiaz;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**[T]@author Adrián Díaz García.
Gestiona una colección de artículos de un almacén utilizando un ArrayList,
y que muestra al final los artículos ordenados alfabéticamente por código de artículo en orden descendente.
(de la Z a la A).*/

public class Almacen {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String codigoIntroducido;
        ArrayList<Articulo> lista = new ArrayList<>();
        
        do{
           System.out.println("Introduce un código de artículo (o escribe '000' para terminar): ");
           codigoIntroducido = sc.nextLine();
           
            if (!codigoIntroducido.equals("000")) {
                boolean encontrado = false;
                for (Articulo art : lista) {
                    if (art.getCodigo().equals(codigoIntroducido)) {
                        encontrado = true;
                        art.setCantidad(art.getCantidad() + 1);
                        break;
                    }
                }
                if (!encontrado) {
                    Articulo nuevoArticulo = new Articulo(codigoIntroducido, codigoIntroducido, 1);
                    lista.add(nuevoArticulo);
                }
            }
        } while (!codigoIntroducido.equals("000"));
        
        Collections.sort(lista);
        
        System.out.println("\n--- INVENTARIO ORDENADO (Z -> A) ---");
        for (Articulo art : lista) {
            System.out.println(art);
        }
       sc.close();
    }
}
