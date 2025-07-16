package com.gestordefacturas.springboot.datajpa.app.service;

import com.gestordefacturas.springboot.datajpa.app.models.dao.IClienteDao;
import com.gestordefacturas.springboot.datajpa.app.models.dao.IFacturaDao;
import com.gestordefacturas.springboot.datajpa.app.models.dao.IProductoDao;
import com.gestordefacturas.springboot.datajpa.app.models.entity.Cliente;
import com.gestordefacturas.springboot.datajpa.app.models.entity.Factura;
import com.gestordefacturas.springboot.datajpa.app.models.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    private final IClienteDao clienteDao;
    private final IProductoDao productoDao;
    private final IFacturaDao facturaDao;

    public ClienteServiceImpl(IClienteDao clienteDao, IProductoDao productoDao, IFacturaDao facturaDao) {
        this.clienteDao = clienteDao;
        this.productoDao = productoDao;
        this.facturaDao = facturaDao;
    }

    // ---------------------- Cliente ----------------------

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteDao.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteDao.deleteById(id);
    }

    // ---------------------- Producto ----------------------

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNombre(String term) {
        return productoDao.findByNombreContainingIgnoreCase(term);
    }

    // ---------------------- Factura ----------------------
    @Override
    @Transactional
    public void saveFactura(Factura factura) {
        facturaDao.save(factura);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findProductoById(Long id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {
        return facturaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteFactura(Long id) {
        facturaDao.deleteById(id);
    }
}
