package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.LineaPedido;
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.PedidoRepository;
import com.techlab.ecommerce.repository.ProductoRepository;
import com.techlab.ecommerce.exception.StockInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        for (LineaPedido linea : pedido.getLineas()) {
            Producto producto = productoRepository.findById(linea.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + linea.getProducto().getId()));

            if (linea.getCantidad() > producto.getStock()) {
                throw new StockInsuficienteException("No hay stock suficiente para el producto: "
                        + producto.getNombre()
                        + ". Stock disponible: " + producto.getStock());
            }
        }

        for (LineaPedido linea : pedido.getLineas()) {
            Producto producto = productoRepository.findById(linea.getProducto().getId()).get();
            producto.setStock(producto.getStock() - linea.getCantidad());
            productoRepository.save(producto);

            linea.setProducto(producto);

            double subtotal = producto.getPrecio() * linea.getCantidad();
            linea.setSubtotal(subtotal);

            linea.setPedido(pedido);
        }

        return pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscarPedidoPorId(int id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public void eliminarPedido(int id) {
        pedidoRepository.deleteById(id);
    }
}

