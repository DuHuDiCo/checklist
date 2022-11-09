
package com.checklist.checklist.services;

import com.checklist.checklist.models.Almacen;
import com.checklist.checklist.repository.AlmacenRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlmacenService {
    
    @Autowired
    private AlmacenRepository almacenRepository;
    
    
    public List<Almacen> getAll(){
        return almacenRepository.findAll();
    }

}
