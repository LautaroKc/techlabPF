package com.techlab.ecommerce.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaPedido> lineas;

    public Pedido() {
        this.lineas = new ArrayList<>();
    }

    public Pedido(List<LineaPedido> lineas) {
        this.lineas = lineas;
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    public int getId() {
        return id;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public double getTotal() {
        return lineas.stream()
                .mapToDouble(LineaPedido::getSubtotal)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido ID: ").append(id).append("\n");
        for (LineaPedido l : lineas) {
            sb.append("  ").append(l.toString()).append("\n");
        }
        sb.append("Total: $").append(getTotal());
        return sb.toString();
    }
}

