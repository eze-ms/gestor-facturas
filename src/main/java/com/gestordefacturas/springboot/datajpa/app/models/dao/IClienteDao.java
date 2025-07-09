package com.gestordefacturas.springboot.datajpa.app.models.dao;

import com.gestordefacturas.springboot.datajpa.app.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IClienteDao extends JpaRepository<Cliente, Long> {

}
