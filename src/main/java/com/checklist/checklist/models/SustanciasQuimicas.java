
package com.checklist.checklist.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class SustanciasQuimicas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private boolean hojas_serguridad_sustancias_quimicas;
    
    private boolean sustancias_quimacas_etiquetadas;
    
    private boolean gafas_seguridad_guantes_nitrilo;
    
    private boolean sustancias_quimicas_almacenadas;
    
    @Column(length = 10000)
    private String observaciones;

    
    private List<String> evidencia = new ArrayList<>();

}
