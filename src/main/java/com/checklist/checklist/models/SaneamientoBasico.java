
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
public class SaneamientoBasico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private boolean puntos_ecologicos_residuos;
    
    private boolean guantes_nitrilo_caucho;
    
    private boolean cuarto_residuos_limpio;
    
    private boolean servicio_sanitario_optimas_condiciones;
    
    private boolean baños_papel_higienico_jabon_toallas_papeleras;
    
    private boolean insectos_roedores_fumigado_areas;
    
    @Column(length = 10000)
    private String observaciones;
    
    
    @ManyToMany(fetch = FetchType.LAZY, cascade  = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "saneamiento_basico_evidencias", joinColumns =  @JoinColumn(name = "saneamiento_basico_id"), inverseJoinColumns = @JoinColumn(name = "evidencia_id"))
    private List<Evidencia> evidencias = new ArrayList<>();

}
