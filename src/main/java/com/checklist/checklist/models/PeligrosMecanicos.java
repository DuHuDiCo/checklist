
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
public class PeligrosMecanicos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private  boolean equipos_herramientas_buen_estado;
    
    @Column(length = 10000)
    private String observaciones;
    
    private List<String> evidencia = new ArrayList<>();

}
