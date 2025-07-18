package com.gestordefacturas.springboot.datajpa.app.models.dao;

import com.gestordefacturas.springboot.datajpa.app.models.entity.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

    @Query("select f from Factura f " +
            "join fetch f.cliente " +
            "join fetch f.items l " +
            "join fetch l.producto " +
            "where f.id = ?1")
    Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);

}
