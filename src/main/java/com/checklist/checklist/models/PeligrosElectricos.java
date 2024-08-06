
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
public class PeligrosElectricos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private boolean cables_buen_estado_entubados;
    
    private boolean empalmes_conexiones_buen_estado;
    
    private boolean tomas_buen_estado_tapa_proteccion;
    
    private boolean cajas_breakers_sin_sobrecarga_señaladas;
    
    private boolean tableros_caja_breakers_sin_material_combustible;
    
    @Column(length = 10000)
    private String observaciones;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade  = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "peligros_electricos_evidencias", joinColumns =  @JoinColumn(name = "peligros_electricos_id"), inverseJoinColumns = @JoinColumn(name = "evidencia_id"))
    private List<Evidencia> evidencias = new ArrayList<>();

    public PeligrosElectricos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCables_buen_estado_entubados() {
        return cables_buen_estado_entubados;
    }

    public void setCables_buen_estado_entubados(boolean cables_buen_estado_entubados) {
        this.cables_buen_estado_entubados = cables_buen_estado_entubados;
    }

    public boolean isEmpalmes_conexiones_buen_estado() {
        return empalmes_conexiones_buen_estado;
    }

    public void setEmpalmes_conexiones_buen_estado(boolean empalmes_conexiones_buen_estado) {
        this.empalmes_conexiones_buen_estado = empalmes_conexiones_buen_estado;
    }

    public boolean isTomas_buen_estado_tapa_proteccion() {
        return tomas_buen_estado_tapa_proteccion;
    }

    public void setTomas_buen_estado_tapa_proteccion(boolean tomas_buen_estado_tapa_proteccion) {
        this.tomas_buen_estado_tapa_proteccion = tomas_buen_estado_tapa_proteccion;
    }

    public boolean isCajas_breakers_sin_sobrecarga_señaladas() {
        return cajas_breakers_sin_sobrecarga_señaladas;
    }

    public void setCajas_breakers_sin_sobrecarga_señaladas(boolean cajas_breakers_sin_sobrecarga_señaladas) {
        this.cajas_breakers_sin_sobrecarga_señaladas = cajas_breakers_sin_sobrecarga_señaladas;
    }

    public boolean isTableros_caja_breakers_sin_material_combustible() {
        return tableros_caja_breakers_sin_material_combustible;
    }

    public void setTableros_caja_breakers_sin_material_combustible(
            boolean tableros_caja_breakers_sin_material_combustible) {
        this.tableros_caja_breakers_sin_material_combustible = tableros_caja_breakers_sin_material_combustible;
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
