
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
public class PeligrosLocativos {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private boolean luminarias_buen_estado;
    
    private boolean muros_buen_estado;
    
    private boolean escaleras_buen_estado;
    
    private boolean pisos_buen_estado;
    
    private boolean ventanas_puertas_buen_estado;
    
    private boolean techos_buen_estado;
    
    private boolean areas_circulacion_despejadas;
    
    private boolean silla_escritorios_buen_estado;
    
    private boolean divisiones_modulares_escritorio_cajones_buenas_condiciones;
    
    @Column(length = 10000)
    private String observaciones;
    
    private List<String> evidencias = new ArrayList<>();

}
