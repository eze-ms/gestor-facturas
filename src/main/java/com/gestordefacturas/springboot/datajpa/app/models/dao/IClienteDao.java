package com.gestordefacturas.springboot.datajpa.app.models.dao;

import com.gestordefacturas.springboot.datajpa.app.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

    @Query("select c from Cliente c " +
            "left join fetch c.facturas " +
            "where c.id = ?1")
    Cliente fetchByIdWithFacturas(Long id);
}
