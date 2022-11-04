
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
public class OrdenAseo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private boolean punto_venta_buen_estado_aseo_mantenimiento;
    
    private boolean archivadores_organizados_documento_libros_almacenados;
    
    @Column(length = 10000)
    private String observaciones;
    
    private List<String> evidencias = new ArrayList<>();

}
