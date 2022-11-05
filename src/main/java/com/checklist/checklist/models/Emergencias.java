
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
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Emergencias {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private boolean ruta_evacuacion_señalizada_libre_obstaculos;
    
    private boolean salida_emergencia_punto_encuentro_señalizadas_despejadas;
    
    private boolean lista_telefonos_emergencia_publicada_socializada;
    
    private boolean extintores_buen_estado_recargados_señalizados_libre_obstaculos;
    
    private boolean camilla_emergencia_buen_estado_cuello_inmovilizador;
    
    private boolean botiquin_buen_estado_elementos_necesarios;
    
    @Column(length = 10000)
    private String observaciones;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade  = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "emergencia_evidencias", joinColumns =  @JoinColumn(name = "emergencia_id"), inverseJoinColumns = @JoinColumn(name = "evidencia_id"))
    private List<Evidencia> evidencias = new ArrayList<>();
   
    
    
    
    

}
