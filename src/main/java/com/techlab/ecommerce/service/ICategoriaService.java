package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    List<Categoria> listarCategorias();
    Categoria guardarCategoria(Categoria categoria);
    Optional<Categoria> buscarCategoriaPorId(int id);
    void eliminarCategoria(int id);
}

