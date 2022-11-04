
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
    
    private List<String> evidencias = new ArrayList<>();
   
    
    
    
    

}
