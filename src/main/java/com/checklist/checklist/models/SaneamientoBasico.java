
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
public class SaneamientoBasico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private boolean puntos_ecologicos_residuos;
    
    private boolean guantes_nitrilo_caucho;
    
    private boolean cuarto_residuos_limpio;
    
    private boolean servicio_sanitario_optimas_condiciones;
    
    private boolean baños_papel_higienico_jabon_toallas_papeleras;
    
    private boolean insectos_roedores_fumigado_areas;
    
    @Column(length = 10000)
    private String observaciones;
    
    
    @ManyToMany(fetch = FetchType.LAZY, cascade  = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "saneamiento_basico_evidencias", joinColumns =  @JoinColumn(name = "saneamiento_basico_id"), inverseJoinColumns = @JoinColumn(name = "evidencia_id"))
    private List<Evidencia> evidencias = new ArrayList<>();


    public SaneamientoBasico() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public boolean isPuntos_ecologicos_residuos() {
        return puntos_ecologicos_residuos;
    }


    public void setPuntos_ecologicos_residuos(boolean puntos_ecologicos_residuos) {
        this.puntos_ecologicos_residuos = puntos_ecologicos_residuos;
    }


    public boolean isGuantes_nitrilo_caucho() {
        return guantes_nitrilo_caucho;
    }


    public void setGuantes_nitrilo_caucho(boolean guantes_nitrilo_caucho) {
        this.guantes_nitrilo_caucho = guantes_nitrilo_caucho;
    }


    public boolean isCuarto_residuos_limpio() {
        return cuarto_residuos_limpio;
    }


    public void setCuarto_residuos_limpio(boolean cuarto_residuos_limpio) {
        this.cuarto_residuos_limpio = cuarto_residuos_limpio;
    }


    public boolean isServicio_sanitario_optimas_condiciones() {
        return servicio_sanitario_optimas_condiciones;
    }


    public void setServicio_sanitario_optimas_condiciones(boolean servicio_sanitario_optimas_condiciones) {
        this.servicio_sanitario_optimas_condiciones = servicio_sanitario_optimas_condiciones;
    }


    public boolean isBaños_papel_higienico_jabon_toallas_papeleras() {
        return baños_papel_higienico_jabon_toallas_papeleras;
    }


    public void setBaños_papel_higienico_jabon_toallas_papeleras(boolean baños_papel_higienico_jabon_toallas_papeleras) {
        this.baños_papel_higienico_jabon_toallas_papeleras = baños_papel_higienico_jabon_toallas_papeleras;
    }


    public boolean isInsectos_roedores_fumigado_areas() {
        return insectos_roedores_fumigado_areas;
    }


    public void setInsectos_roedores_fumigado_areas(boolean insectos_roedores_fumigado_areas) {
        this.insectos_roedores_fumigado_areas = insectos_roedores_fumigado_areas;
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
