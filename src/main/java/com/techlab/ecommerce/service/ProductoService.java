package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<Producto> listarProductos() {
        return repo.findAll();
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return repo.save(producto);
    }

    @Override
    public Optional<Producto> buscarProductoPorId(int id) {
        return repo.findById(id);
    }

    @Override
    public void eliminarProducto(int id) {
        repo.deleteById(id);
    }
}
