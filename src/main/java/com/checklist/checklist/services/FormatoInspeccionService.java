
package com.checklist.checklist.services;

import com.checklist.checklist.models.FormatoInspeccion;
import com.checklist.checklist.repository.FormatoInspeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormatoInspeccionService {
    
    @Autowired
    private FormatoInspeccionRepository fir;
    
    
    public FormatoInspeccion save(FormatoInspeccion formatoInspeccion){
        return fir.save(formatoInspeccion);
    }

}
