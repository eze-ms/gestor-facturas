package com.gestordefacturas.springboot.datajpa.app.models.dao;

import com.gestordefacturas.springboot.datajpa.app.models.entity.Factura;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

}
