package com.gestordefacturas.springboot.datajpa.app.controllers;

import com.gestordefacturas.springboot.datajpa.app.models.entity.Cliente;
import com.gestordefacturas.springboot.datajpa.app.service.IClienteService;
import com.gestordefacturas.springboot.datajpa.app.service.IUploadFileService;
import com.gestordefacturas.springboot.datajpa.app.util.paginator.PageRender;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable("id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Cliente cliente = clienteService.findOne(id);
        if (cliente == null) {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/listar";
        }

        model.put("cliente", cliente);
        model.put("titulo", "Detalle cliente: " + cliente.getNombre());

        return "ver";
    }

    @GetMapping("/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "clienteId", required = false) Long clienteId, Model model) {

        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Cliente> clientes = clienteService.findAll(pageRequest);
        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);


        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);

        Cliente clienteSeleccionado = null;

        if (clienteId != null) {
            clienteSeleccionado = clienteService.findOne(clienteId);
        } else if (!clientes.isEmpty()) {
            clienteSeleccionado = clientes.getContent().get(0);
        }

        model.addAttribute("clienteSeleccionado", clienteSeleccionado);

        return "listar";
    }

    @GetMapping("/form")
    public String crear(Map<String, Object> model) {

        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");
        return "form";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable("id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        if (id <= 0) {
            return "redirect:/listar";
        }

        Cliente cliente = clienteService.findOne(id);
        if (cliente == null) {
            flash.addFlashAttribute("error", "El ID del cliente no existe en la base de datos");
            return "redirect:/listar";
        }

        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "form";
    }

    @PostMapping("/form")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

        Logger logger = LoggerFactory.getLogger(getClass());

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Cliente");
            return "form";
        }

        boolean esEdicion = (cliente.getId() != null);

        try {
            clienteService.save(cliente);

            if (!foto.isEmpty()) {
                logger.info("Archivo recibido: {}", foto.getOriginalFilename());

                // Eliminar imagen anterior si existe
                if (esEdicion && cliente.getFoto() != null) {
                    if (uploadFileService.delete(cliente.getFoto())) {
                        logger.info("Imagen anterior eliminada: {}", cliente.getFoto());
                    }
                }

                // Copiar nueva imagen
                String nombreFoto = uploadFileService.copy(foto);
                cliente.setFoto(nombreFoto);
                clienteService.save(cliente);

                flash.addFlashAttribute("info", "Imagen '" + nombreFoto + "' subida correctamente");
            }

            status.setComplete();
            String mensaje = esEdicion ? "Cliente editado con éxito!" : "Cliente creado con éxito!";
            flash.addFlashAttribute("success", mensaje);

        } catch (DataIntegrityViolationException e) {
            flash.addFlashAttribute("error", "Error: el email ya está registrado");
            logger.error("Violación de integridad al guardar cliente", e);
            return "redirect:/form";
        }

        return "redirect:/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, RedirectAttributes flash) {
        Logger logger = LoggerFactory.getLogger(getClass());

        if (id > 0) {
            Cliente cliente = clienteService.findOne(id);

            if (cliente != null) {
                if (cliente.getFoto() != null) {
                    if (uploadFileService.delete(cliente.getFoto())) {
                        logger.info("Imagen eliminada: {}", cliente.getFoto());
                    }
                }

                clienteService.delete(id);
                flash.addFlashAttribute("success", "Cliente eliminado correctamente");
            } else {
                flash.addFlashAttribute("error", "El cliente no existe en la base de datos.");
            }
        }

        return "redirect:/listar";
    }

}