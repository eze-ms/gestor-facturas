package com.gestordefacturas.springboot.datajpa.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public Resource load(String filename) {
        try {
            Path file = getPath(filename);
            Resource recurso = new UrlResource(file.toUri());
            if (!recurso.exists() || !recurso.isReadable()) {
                throw new RuntimeException("No se puede cargar la imagen: " + filename);
            }
            return recurso;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al cargar la imagen: " + filename, e);
        }
    }

    @Override
    public String copy(MultipartFile file) {
        String nombreArchivo = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
        Path rutaAbsoluta = getPath(nombreArchivo);

        try {
            Files.copy(file.getInputStream(), rutaAbsoluta, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error al copiar la imagen: " + nombreArchivo, e);
        }

        return nombreArchivo;
    }

    @Override
    public boolean delete(String filename) {
        Path rutaArchivo = getPath(filename);
        File archivo = rutaArchivo.toFile();
        return archivo.exists() && archivo.canRead() && archivo.delete();
    }

    public Path getPath(String filename) {
        return Paths.get(uploadDir).resolve(filename).toAbsolutePath();
    }
}
