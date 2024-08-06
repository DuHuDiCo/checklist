
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

    public Emergencias() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRuta_evacuacion_señalizada_libre_obstaculos() {
        return ruta_evacuacion_señalizada_libre_obstaculos;
    }

    public void setRuta_evacuacion_señalizada_libre_obstaculos(boolean ruta_evacuacion_señalizada_libre_obstaculos) {
        this.ruta_evacuacion_señalizada_libre_obstaculos = ruta_evacuacion_señalizada_libre_obstaculos;
    }

    public boolean isSalida_emergencia_punto_encuentro_señalizadas_despejadas() {
        return salida_emergencia_punto_encuentro_señalizadas_despejadas;
    }

    public void setSalida_emergencia_punto_encuentro_señalizadas_despejadas(
            boolean salida_emergencia_punto_encuentro_señalizadas_despejadas) {
        this.salida_emergencia_punto_encuentro_señalizadas_despejadas = salida_emergencia_punto_encuentro_señalizadas_despejadas;
    }

    public boolean isLista_telefonos_emergencia_publicada_socializada() {
        return lista_telefonos_emergencia_publicada_socializada;
    }

    public void setLista_telefonos_emergencia_publicada_socializada(
            boolean lista_telefonos_emergencia_publicada_socializada) {
        this.lista_telefonos_emergencia_publicada_socializada = lista_telefonos_emergencia_publicada_socializada;
    }

    public boolean isExtintores_buen_estado_recargados_señalizados_libre_obstaculos() {
        return extintores_buen_estado_recargados_señalizados_libre_obstaculos;
    }

    public void setExtintores_buen_estado_recargados_señalizados_libre_obstaculos(
            boolean extintores_buen_estado_recargados_señalizados_libre_obstaculos) {
        this.extintores_buen_estado_recargados_señalizados_libre_obstaculos = extintores_buen_estado_recargados_señalizados_libre_obstaculos;
    }

    public boolean isCamilla_emergencia_buen_estado_cuello_inmovilizador() {
        return camilla_emergencia_buen_estado_cuello_inmovilizador;
    }

    public void setCamilla_emergencia_buen_estado_cuello_inmovilizador(
            boolean camilla_emergencia_buen_estado_cuello_inmovilizador) {
        this.camilla_emergencia_buen_estado_cuello_inmovilizador = camilla_emergencia_buen_estado_cuello_inmovilizador;
    }

    public boolean isBotiquin_buen_estado_elementos_necesarios() {
        return botiquin_buen_estado_elementos_necesarios;
    }

    public void setBotiquin_buen_estado_elementos_necesarios(boolean botiquin_buen_estado_elementos_necesarios) {
        this.botiquin_buen_estado_elementos_necesarios = botiquin_buen_estado_elementos_necesarios;
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
