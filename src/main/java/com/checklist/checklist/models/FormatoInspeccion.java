
package com.checklist.checklist.models;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
public class FormatoInspeccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private  String codigo;
    private final int version = 1;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private String realizadoBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date fecha_inspeccion;
    
    private String punto_venta;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "sustancias_quimicas_id", referencedColumnName = "id")
    private SustanciasQuimicas sustanciasQuimicas ;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "peligros_electricos_id", referencedColumnName = "id")
    private PeligrosElectricos peligrosElectricos;
    
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "peligros_mecanicos_id", referencedColumnName = "id")
    private PeligrosMecanicos peligrosMecanicos;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "peligros_locativos_id", referencedColumnName = "id")    
    private PeligrosLocativos peligrosLocativos;
    
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "emergencias_id", referencedColumnName = "id")
    private Emergencias emergencias;
    
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "orden_aseo_id", referencedColumnName = "id")
    private OrdenAseo ordenAseo;
    
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "saneamiento_basico_id", referencedColumnName = "id")
    private SaneamientoBasico saneamientoBasico;
    
    
    
    
    

}
