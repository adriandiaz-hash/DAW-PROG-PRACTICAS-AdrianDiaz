package com.mycompany.stockdiaz;

/**[T]@author Adrián Díaz García.
Atributos, getters y setters y métodos que conforman la estructura de todos los artículos.*/

public class Articulo implements Comparable<Articulo> {
    private String codigo;
    private String descripcion;
    private int cantidad;

    public Articulo (String codigo, String descripcion, int cantidad) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }
        
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    @Override
    public int compareTo(Articulo otroArticulo) {
        return otroArticulo.getCodigo().compareTo(this.codigo);
    }
    @Override
    public String toString() {
        return "[" + codigo + "] " + descripcion + " - Stock: " + cantidad;
    }
}