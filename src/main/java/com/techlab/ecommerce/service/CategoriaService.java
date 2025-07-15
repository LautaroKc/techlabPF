package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Categoria;
import com.techlab.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Optional<Categoria> buscarCategoriaPorId(int id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public void eliminarCategoria(int id) {
        categoriaRepository.deleteById(id);
    }
}

