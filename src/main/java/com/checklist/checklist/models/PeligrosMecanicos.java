
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
public class PeligrosMecanicos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private  boolean equipos_herramientas_buen_estado;
    
    @Column(length = 10000)
    private String observaciones;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade  = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "peligros_mecanicos_evidencias", joinColumns =  @JoinColumn(name = "peligros_mecanicos_id"), inverseJoinColumns = @JoinColumn(name = "evidencia_id"))
    private List<Evidencia> evidencias = new ArrayList<>();

    public PeligrosMecanicos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEquipos_herramientas_buen_estado() {
        return equipos_herramientas_buen_estado;
    }

    public void setEquipos_herramientas_buen_estado(boolean equipos_herramientas_buen_estado) {
        this.equipos_herramientas_buen_estado = equipos_herramientas_buen_estado;
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
