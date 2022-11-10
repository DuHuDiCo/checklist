
package com.checklist.checklist.services;



import com.checklist.checklist.models.Evidencia;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    
    public void init();
    
    public String save(MultipartFile file) throws Exception;
    
    public String guardarFile(String file, MultipartFile archi) throws Exception;
    
    public Resource load(String name) throws Exception;
    
    public void save(List<MultipartFile> file) throws Exception;
    
    public Stream<Path> loadAll() throws Exception;
    
    public Evidencia save(Evidencia file);
   
    
}
