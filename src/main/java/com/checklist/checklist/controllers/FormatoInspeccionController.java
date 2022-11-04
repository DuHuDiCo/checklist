
package com.checklist.checklist.controllers;

import com.checklist.checklist.models.FormatoInspeccion;
import com.checklist.checklist.services.FormatoInspeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/formato")
@CrossOrigin(origins = "*")
public class FormatoInspeccionController {
    
    @Autowired
    private FormatoInspeccionService fis;
    
    
    @PostMapping("/")
    public ResponseEntity<?> guardarFormato(@RequestBody FormatoInspeccion fi){
        if(fi == null){
            return ResponseEntity.noContent().build();
        }
        
        FormatoInspeccion formatoInspeccion = fis.save(fi);
        
        return ResponseEntity.ok(formatoInspeccion);
    }

}
