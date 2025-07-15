package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> listarProductos();
    Producto guardarProducto(Producto producto);
    Optional<Producto> buscarProductoPorId(int id);
    void eliminarProducto(int id);
}
