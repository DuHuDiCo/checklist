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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class FormatoInspeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String codigo;
    private final int version = 1;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String realizadoBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date fechaInspeccion;

    private String punto_venta;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "sustancias_quimicas_id", referencedColumnName = "id")
    private SustanciasQuimicas sustanciasQuimicas;

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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pdf_id", referencedColumnName = "id")
    private Pdf pdf;

    private String estado;

    public FormatoInspeccion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getVersion() {
        return version;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRealizadoBy() {
        return realizadoBy;
    }

    public void setRealizadoBy(String realizadoBy) {
        this.realizadoBy = realizadoBy;
    }

    public String getPunto_venta() {
        return punto_venta;
    }

    public void setPunto_venta(String punto_venta) {
        this.punto_venta = punto_venta;
    }

    public SustanciasQuimicas getSustanciasQuimicas() {
        return sustanciasQuimicas;
    }

    public void setSustanciasQuimicas(SustanciasQuimicas sustanciasQuimicas) {
        this.sustanciasQuimicas = sustanciasQuimicas;
    }

    public PeligrosElectricos getPeligrosElectricos() {
        return peligrosElectricos;
    }

    public void setPeligrosElectricos(PeligrosElectricos peligrosElectricos) {
        this.peligrosElectricos = peligrosElectricos;
    }

    public PeligrosMecanicos getPeligrosMecanicos() {
        return peligrosMecanicos;
    }

    public void setPeligrosMecanicos(PeligrosMecanicos peligrosMecanicos) {
        this.peligrosMecanicos = peligrosMecanicos;
    }

    public PeligrosLocativos getPeligrosLocativos() {
        return peligrosLocativos;
    }

    public void setPeligrosLocativos(PeligrosLocativos peligrosLocativos) {
        this.peligrosLocativos = peligrosLocativos;
    }

    public Emergencias getEmergencias() {
        return emergencias;
    }

    public void setEmergencias(Emergencias emergencias) {
        this.emergencias = emergencias;
    }

    public OrdenAseo getOrdenAseo() {
        return ordenAseo;
    }

    public void setOrdenAseo(OrdenAseo ordenAseo) {
        this.ordenAseo = ordenAseo;
    }

    public SaneamientoBasico getSaneamientoBasico() {
        return saneamientoBasico;
    }

    public void setSaneamientoBasico(SaneamientoBasico saneamientoBasico) {
        this.saneamientoBasico = saneamientoBasico;
    }

    public Pdf getPdf() {
        return pdf;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInspeccion() {
        return fechaInspeccion;
    }

    public void setFechaInspeccion(Date fechaInspeccion) {
        this.fechaInspeccion = fechaInspeccion;
    }

}
