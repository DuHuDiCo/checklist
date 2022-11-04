
package com.checklist.checklist.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data

public class FormatoInspeccion {
    
    
    
    private  String codigo;
    private final int version = 1;
    
    private Date fecha;
    
    private String realizadoBy;
    
    private Date fecha_inspeccion;
    
    private String punto_venta;
    
    private List<SustanciasQuimicas> sustanciasQuimicas = new ArrayList<>();
    
    
    
    

}
