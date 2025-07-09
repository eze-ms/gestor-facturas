package com.gestordefacturas.springboot.datajpa.app.models.dao;

import com.gestordefacturas.springboot.datajpa.app.models.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductoDao extends CrudRepository<Producto, Long> {

    // Opción manual con JPQL
    @Query("select p from Producto p where lower(p.nombre) like lower(concat('%', ?1, '%'))")
    List<Producto> findByNombre(String term);

    // Opción declarativa, más limpia
    List<Producto> findByNombreContainingIgnoreCase(String term);
}

