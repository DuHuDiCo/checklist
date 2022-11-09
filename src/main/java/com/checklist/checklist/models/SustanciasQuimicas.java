
package com.checklist.checklist.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    
    
    @ManyToMany(fetch = FetchType.LAZY, cascade  = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sustancias_quimicas_evidencias", joinColumns =  @JoinColumn(name = "sustancias_quimicas_id"), inverseJoinColumns = @JoinColumn(name = "evidencia_id"))
    private List<Evidencia> evidencias = new ArrayList<>();
    
    
    

}
