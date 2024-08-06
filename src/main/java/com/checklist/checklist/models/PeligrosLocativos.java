
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
    
    @ManyToMany(fetch = FetchType.LAZY, cascade  = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "peligros_locativos_evidencias", joinColumns =  @JoinColumn(name = "peligros_locativos_id"), inverseJoinColumns = @JoinColumn(name = "evidencia_id"))
    private List<Evidencia> evidencias = new ArrayList<>();

    public PeligrosLocativos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLuminarias_buen_estado() {
        return luminarias_buen_estado;
    }

    public void setLuminarias_buen_estado(boolean luminarias_buen_estado) {
        this.luminarias_buen_estado = luminarias_buen_estado;
    }

    public boolean isMuros_buen_estado() {
        return muros_buen_estado;
    }

    public void setMuros_buen_estado(boolean muros_buen_estado) {
        this.muros_buen_estado = muros_buen_estado;
    }

    public boolean isEscaleras_buen_estado() {
        return escaleras_buen_estado;
    }

    public void setEscaleras_buen_estado(boolean escaleras_buen_estado) {
        this.escaleras_buen_estado = escaleras_buen_estado;
    }

    public boolean isPisos_buen_estado() {
        return pisos_buen_estado;
    }

    public void setPisos_buen_estado(boolean pisos_buen_estado) {
        this.pisos_buen_estado = pisos_buen_estado;
    }

    public boolean isVentanas_puertas_buen_estado() {
        return ventanas_puertas_buen_estado;
    }

    public void setVentanas_puertas_buen_estado(boolean ventanas_puertas_buen_estado) {
        this.ventanas_puertas_buen_estado = ventanas_puertas_buen_estado;
    }

    public boolean isTechos_buen_estado() {
        return techos_buen_estado;
    }

    public void setTechos_buen_estado(boolean techos_buen_estado) {
        this.techos_buen_estado = techos_buen_estado;
    }

    public boolean isAreas_circulacion_despejadas() {
        return areas_circulacion_despejadas;
    }

    public void setAreas_circulacion_despejadas(boolean areas_circulacion_despejadas) {
        this.areas_circulacion_despejadas = areas_circulacion_despejadas;
    }

    public boolean isSilla_escritorios_buen_estado() {
        return silla_escritorios_buen_estado;
    }

    public void setSilla_escritorios_buen_estado(boolean silla_escritorios_buen_estado) {
        this.silla_escritorios_buen_estado = silla_escritorios_buen_estado;
    }

    public boolean isDivisiones_modulares_escritorio_cajones_buenas_condiciones() {
        return divisiones_modulares_escritorio_cajones_buenas_condiciones;
    }

    public void setDivisiones_modulares_escritorio_cajones_buenas_condiciones(
            boolean divisiones_modulares_escritorio_cajones_buenas_condiciones) {
        this.divisiones_modulares_escritorio_cajones_buenas_condiciones = divisiones_modulares_escritorio_cajones_buenas_condiciones;
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
