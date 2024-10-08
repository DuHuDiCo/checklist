
package com.checklist.checklist.controllers;

import com.checklist.checklist.models.Almacen;
import com.checklist.checklist.models.Evidencia;
import com.checklist.checklist.models.FormatoInspeccion;
import com.checklist.checklist.models.Login;
import com.checklist.checklist.models.Pdf;
import com.checklist.checklist.models.ReporteExcel;
import com.checklist.checklist.models.SustanciasQuimicas;
import com.checklist.checklist.services.AlmacenService;
import com.checklist.checklist.services.FormatoInspeccionService;
import com.checklist.checklist.services.GenerarExcel;
import com.checklist.checklist.utils.GenerarPdf;
import com.checklist.checklist.utils.SaveFiles;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/formato")
@CrossOrigin(origins = { "http://localhost:4200/", "http://192.168.1.171:8600", "http://201.184.154.82:8500",
        "https://checklistgmjlocal.duckdns.org/", "http://checklistgmjexterno.duckdns.org" })
public class FormatoInspeccionController {

    @Autowired
    private FormatoInspeccionService fis;

    @Autowired
    private AlmacenService almacenService;

    @Autowired
    private SaveFiles saveFiles;

    @Autowired
    private GenerarPdf generarPdf;

    @Autowired
    private GenerarExcel generarExcel;

    @PostMapping("/save")
    public ResponseEntity<?> guardarFormato(@RequestBody FormatoInspeccion fi) throws IOException {
        if (fi == null) {
            return ResponseEntity.noContent().build();
        }

        List<Evidencia> sq = guardarEvidencias(fi.getSustanciasQuimicas().getEvidencias());
        fi.getSustanciasQuimicas().setEvidencias(sq);

        List<Evidencia> pe = guardarEvidencias(fi.getPeligrosElectricos().getEvidencias());
        fi.getPeligrosElectricos().setEvidencias(pe);

        List<Evidencia> pm = guardarEvidencias(fi.getPeligrosMecanicos().getEvidencias());
        fi.getPeligrosMecanicos().setEvidencias(pm);

        List<Evidencia> pl = guardarEvidencias(fi.getPeligrosLocativos().getEvidencias());
        fi.getPeligrosLocativos().setEvidencias(pl);

        List<Evidencia> emer = guardarEvidencias(fi.getEmergencias().getEvidencias());
        fi.getEmergencias().setEvidencias(emer);

        List<Evidencia> ordenAseo = guardarEvidencias(fi.getOrdenAseo().getEvidencias());
        fi.getOrdenAseo().setEvidencias(ordenAseo);

        List<Evidencia> saneamiento = guardarEvidencias(fi.getSaneamientoBasico().getEvidencias());
        fi.getSaneamientoBasico().setEvidencias(saneamiento);

        Pdf pfd = generarPdf.generarPdf(fi);
        fi.setPdf(pfd);
        fi.setEstado("EN REVISION");

        FormatoInspeccion formatoInspeccion = fis.save(fi);

        return ResponseEntity.ok(formatoInspeccion);
    }

    @GetMapping("/pdf")
    public ResponseEntity<?> generarPdfs(@Param("fechaStart") Date fechaStart,
            @Param("fechaEnd") Date fechaEnd) {
        List<FormatoInspeccion> formatos = fis.getAll(fechaStart, fechaEnd);
        formatos.forEach((f) -> {
            try {
                Pdf pdf = generarPdf.generarPdf(f);
                f.setPdf(pdf);
                FormatoInspeccion forma = fis.save(f);

            } catch (IOException ex) {
                Logger.getLogger(FormatoInspeccionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return ResponseEntity.ok("Actulizados Correctamente");
    }

    @GetMapping("/all")
    public ResponseEntity<List<?>> getAll(@Param("fechaStart") Date fechaStart,
            @Param("fechaEnd") Date fechaEnd) {
        return ResponseEntity.ok(fis.getAll(fechaStart, fechaEnd));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        FormatoInspeccion inspeccion = fis.getById(id);
        if (inspeccion == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(inspeccion);
    }

    @GetMapping("/almacenes")
    public ResponseEntity<List<?>> getAllAlmacenes() {
        List<Almacen> almacenes = almacenService.getAll();
        return ResponseEntity.ok(almacenes);
    }

    public List<Evidencia> guardarEvidencias(List<Evidencia> evidencias) {

        List<Evidencia> evidenciaPe = new ArrayList<>();
        evidencias.forEach((evi) -> {
            try {
                Evidencia evidencia = saveFiles.guardarFile(evi.getRuta());
                evidencia.setNombreCategoria(evi.getArchivo());
                evidenciaPe.add(evidencia);
            } catch (Exception ex) {
                Logger.getLogger(FormatoInspeccionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return evidenciaPe;
    }

    public boolean stringToBoolean(String dato) {
        return Boolean.parseBoolean(dato);

    }

    // ejemplo para practica del frontend
    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody Login login) {
        if (login.getUsername().equals("Alejo") && login.getPassword().equals("12345")) {
            return ResponseEntity.ok("Datos Correctos");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @GetMapping("/cambiarEstado/{id}")
    public ResponseEntity<HttpStatus> cambiarEstado(@PathVariable("id") int id) {
        if (fis.cambiarEstado(id)) {
            return ResponseEntity.ok(HttpStatus.ACCEPTED);

        }
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/reporte/")
    public ResponseEntity<ReporteExcel> generarExcel(@Param("fechaStart") Date fechaStart,
            @Param("fechaEnd") Date fechaEnd) {
        ReporteExcel rp = generarExcel.generarReporteExcel(fechaStart, fechaEnd);
        return ResponseEntity.ok(rp);
    }
}
