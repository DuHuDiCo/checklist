
package com.checklist.checklist.services;

import com.checklist.checklist.models.FormatoInspeccion;
import com.checklist.checklist.repository.FormatoInspeccionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormatoInspeccionService {
    
    @Autowired
    private FormatoInspeccionRepository fir;
    
    
    public FormatoInspeccion save(FormatoInspeccion formatoInspeccion){
        return fir.save(formatoInspeccion);
    }
    
    public List<FormatoInspeccion> getAll(){
        return fir.findByEstado("EN REVISION");
    }
    
    
    public FormatoInspeccion getById(int id){
        return fir.findById(id).orElse(null);
    }
    
    public boolean cambiarEstado(int id){
        FormatoInspeccion fi = fir.findById(id).orElse(null);
        if(fi!= null){
            fi.setEstado("REVISADO");
            fir.save(fi);
            return true;
        }    
        return false;    
            
        
        
    }
    
   

}
