
package com.checklist.checklist.services;




import com.checklist.checklist.models.Evidencia;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServicesImpl implements FileService{
    
    private final Path rootFolder = Paths.get("uploads");
    
            
    

    @Override
    public String save(MultipartFile file) throws Exception {
        final String  name = file.getOriginalFilename();
        Files.copy(file.getInputStream(), this.rootFolder.resolve(name));
        return name;
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


   
    
}
