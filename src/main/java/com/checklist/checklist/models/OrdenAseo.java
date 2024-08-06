
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
public class OrdenAseo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private boolean punto_venta_buen_estado_aseo_mantenimiento;
    
    private boolean archivadores_organizados_documento_libros_almacenados;
    
    @Column(length = 10000)
    private String observaciones;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade  = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "orden_aseo_evidencias", joinColumns =  @JoinColumn(name = "orden_aseo_id"), inverseJoinColumns = @JoinColumn(name = "evidencia_id"))
    private List<Evidencia> evidencias = new ArrayList<>();

    public OrdenAseo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPunto_venta_buen_estado_aseo_mantenimiento() {
        return punto_venta_buen_estado_aseo_mantenimiento;
    }

    public void setPunto_venta_buen_estado_aseo_mantenimiento(boolean punto_venta_buen_estado_aseo_mantenimiento) {
        this.punto_venta_buen_estado_aseo_mantenimiento = punto_venta_buen_estado_aseo_mantenimiento;
    }

    public boolean isArchivadores_organizados_documento_libros_almacenados() {
        return archivadores_organizados_documento_libros_almacenados;
    }

    public void setArchivadores_organizados_documento_libros_almacenados(
            boolean archivadores_organizados_documento_libros_almacenados) {
        this.archivadores_organizados_documento_libros_almacenados = archivadores_organizados_documento_libros_almacenados;
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
