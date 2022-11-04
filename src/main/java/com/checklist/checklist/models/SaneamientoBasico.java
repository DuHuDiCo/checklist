
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
    
    
    private List<String> evidencias = new ArrayList<>();

}
