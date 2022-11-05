
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
public class PeligrosElectricos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private boolean cables_buen_estado_entubados;
    
    private boolean empalmes_conexiones_buen_estado;
    
    private boolean tomas_buen_estado_tapa_proteccion;
    
    private boolean cajas_breakers_sin_sobrecarga_señaladas;
    
    private boolean tableros_caja_breakers_sin_material_combustible;
    
    @Column(length = 10000)
    private String observaciones;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade  = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "peligros_electricos_evidencias", joinColumns =  @JoinColumn(name = "peligros_electricos_id"), inverseJoinColumns = @JoinColumn(name = "evidencia_id"))
    private List<Evidencia> evidencias = new ArrayList<>();

}
