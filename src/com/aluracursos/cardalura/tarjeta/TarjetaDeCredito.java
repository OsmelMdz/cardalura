package com.aluracursos.cardalura.tarjeta;

import com.aluracursos.cardalura.compra.Compra;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class TarjetaDeCredito {
    private final double limite;
    private double saldo;
    private final List<Compra> listaDeCompras;

    public TarjetaDeCredito(double limite) {
        if (limite <= 0) {
            throw new IllegalArgumentException("El límite debe ser mayor que cero.");
        }
        this.limite = limite;
        this.saldo = limite;
        this.listaDeCompras = new ArrayList<>();
    }

    public boolean lanzarCompra(Compra compra) {
        if (compra == null) return false;
        if (this.saldo >= compra.getValor()) {
            this.saldo -= compra.getValor();
            this.listaDeCompras.add(compra);
            return true;
        }
        return false;
    }

    public double getLimite() {
        return limite;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Compra> getComprasOrdenadasPorValor() {
        List<Compra> copiaOrdenada = new ArrayList<>(listaDeCompras);
        Collections.sort(copiaOrdenada);
        return copiaOrdenada;
    }
}
