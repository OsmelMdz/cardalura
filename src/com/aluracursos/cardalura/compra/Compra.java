package com.aluracursos.cardalura.compra;

public class Compra implements Comparable<Compra> {
    private final double valor;
    private final String descripcion;

    public Compra(double valor, String descripcion) {
        if (valor <= 0) {
            throw new IllegalArgumentException("El valor de la compra debe ser positivo.");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public double getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f", descripcion, valor);
    }

    @Override
    public int compareTo(Compra otraCompra) {
        return Double.compare(this.valor, otraCompra.getValor());
    }
}

