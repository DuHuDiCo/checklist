
package com.checklist.checklist.utils;

import com.checklist.checklist.models.Evidencia;
import com.checklist.checklist.services.CustomMultipartFile;
import com.checklist.checklist.services.FileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SaveFiles {
    
    
     @Autowired
    private FileService fileService;
     
     
     
      public Evidencia guardarFile(String base64) throws Exception {

        final String[] base64Array = base64.split(",");

        String dataUir, data;

        if (base64Array.length > 1) {
            dataUir = base64Array[0];
            data = base64Array[1];
        } else {
            dataUir = "data:image/jpg;base64";
            data = base64Array[0];
        }

        MultipartFile multipartFile = new CustomMultipartFile(data, dataUir);
        String name = fileService.guardarFile(multipartFile);
          System.out.println(name);
        Evidencia file = new Evidencia();
        
        file.setRuta(name);

        

        Resource f = fileService.load(file.getRuta());

        String url = f.getFilename();
        file.setArchivo(url);

        return file;

    }
      
      
      public Evidencia imagenToBase64(String ruta) throws FileNotFoundException, IOException {
        File img = new File(ruta);
        Base64 base64 = new Base64();
        byte[] imageBytes = new byte[(int) img.length()];
        InputStream inputStream = new FileInputStream(img);
        inputStream.read(imageBytes);
        String encodedFile = base64.encodeToString(imageBytes);
        Evidencia file = new Evidencia();
        file.setArchivo(img.getName());
        file.setRuta(encodedFile);

        return file;
    }
}
