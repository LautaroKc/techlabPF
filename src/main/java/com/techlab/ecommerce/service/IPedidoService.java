package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface IPedidoService {
    List<Pedido> listarPedidos();
    Pedido guardarPedido(Pedido pedido);
    Optional<Pedido> buscarPedidoPorId(int id);
    void eliminarPedido(int id);
}
