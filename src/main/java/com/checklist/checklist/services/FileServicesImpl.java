package com.checklist.checklist.services;

import com.checklist.checklist.models.Evidencia;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServicesImpl implements FileService {

    private final Path rootFolder = Paths.get("uploads");

    String ruta = "/var/lib/tomcat9/webapps/checklist/uploads";

    @Override
    public String save(MultipartFile file) throws Exception {
        final String name = file.getOriginalFilename();
        Files.copy(file.getInputStream(), this.rootFolder.resolve(name));
        return name;
    }

    @Override
    public String guardarFile(String base64, MultipartFile archi) throws IOException {
        
        byte[] datos = DatatypeConverter.parseBase64Binary(base64);
        String path = ruta + "/" + archi.getOriginalFilename();
        File file = new File(path);
        try {
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(datos);

        } catch (IOException e) {
            System.out.println(e);
        }

        return path;
    }

    @Override
    public Resource load(String name) throws Exception {
        Path file = rootFolder.resolve(name);
        Resource resource = new UrlResource(file.toUri());
        return resource;
    }

    @Override
    public void save(List<MultipartFile> files) throws Exception {
        System.out.println(rootFolder);
        for (MultipartFile multipartFile : files) {
            this.save(multipartFile);
        }
    }

    @Override
    public Stream<Path> loadAll() throws Exception {
        return Files.walk(rootFolder, 1).filter(path -> !path.equals(rootFolder)).map(rootFolder::relativize);
    }

    @Override
    public Evidencia save(Evidencia file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootFolder);
        } catch (IOException e) {
            throw new RuntimeException("No se puede inicializar la carpeta uploads");
        }
    }

}
