
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
    
    private List<String> evidencia = new ArrayList<>();

}
