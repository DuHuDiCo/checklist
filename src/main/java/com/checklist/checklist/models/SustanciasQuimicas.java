
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


@Entity
public class SustanciasQuimicas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private boolean hojas_serguridad_sustancias_quimicas;
    
    private boolean sustancias_quimacas_etiquetadas;
    
    private boolean gafas_seguridad_guantes_nitrilo;
    
    private boolean sustancias_quimicas_almacenadas;
    
    @Column(length = 10000)
    private String observaciones;
    
    
    @ManyToMany(fetch = FetchType.LAZY, cascade  = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sustancias_quimicas_evidencias", joinColumns =  @JoinColumn(name = "sustancias_quimicas_id"), inverseJoinColumns = @JoinColumn(name = "evidencia_id"))
    private List<Evidencia> evidencias = new ArrayList<>();


    public SustanciasQuimicas() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public boolean isHojas_serguridad_sustancias_quimicas() {
        return hojas_serguridad_sustancias_quimicas;
    }


    public void setHojas_serguridad_sustancias_quimicas(boolean hojas_serguridad_sustancias_quimicas) {
        this.hojas_serguridad_sustancias_quimicas = hojas_serguridad_sustancias_quimicas;
    }


    public boolean isSustancias_quimacas_etiquetadas() {
        return sustancias_quimacas_etiquetadas;
    }


    public void setSustancias_quimacas_etiquetadas(boolean sustancias_quimacas_etiquetadas) {
        this.sustancias_quimacas_etiquetadas = sustancias_quimacas_etiquetadas;
    }


    public boolean isGafas_seguridad_guantes_nitrilo() {
        return gafas_seguridad_guantes_nitrilo;
    }


    public void setGafas_seguridad_guantes_nitrilo(boolean gafas_seguridad_guantes_nitrilo) {
        this.gafas_seguridad_guantes_nitrilo = gafas_seguridad_guantes_nitrilo;
    }


    public boolean isSustancias_quimicas_almacenadas() {
        return sustancias_quimicas_almacenadas;
    }


    public void setSustancias_quimicas_almacenadas(boolean sustancias_quimicas_almacenadas) {
        this.sustancias_quimicas_almacenadas = sustancias_quimicas_almacenadas;
    }


    public String getObservaciones() {
        return observaciones;
    }


    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


    public List<Evidencia> getEvidencias() {
        return evidencias;
    }


    public void setEvidencias(List<Evidencia> evidencias) {
        this.evidencias = evidencias;
    }
    
    
    
    

}
