package com.checklist.checklist.services;

import com.checklist.checklist.models.Evidencia;
import com.checklist.checklist.models.FormatoInspeccion;
import com.checklist.checklist.repository.FormatoInspeccionRepository;
import com.checklist.checklist.utils.SaveFiles;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class FormatoInspeccionService {

    @Autowired
    private FormatoInspeccionRepository fir;

    @Autowired
    private SaveFiles saveFiles;

    public FormatoInspeccion save(FormatoInspeccion formatoInspeccion) {
        return fir.save(formatoInspeccion);
    }

    public List<FormatoInspeccion> getAll() {

        List<FormatoInspeccion> formatos = fir.findByEstado("EN REVISION");
        if (CollectionUtils.isEmpty(formatos)) {
            return null;
        }

        for (FormatoInspeccion formato : formatos) {
            for (Evidencia evidencia : formato.getSustanciasQuimicas().getEvidencias()) {
                try {
                    evidencia.setRuta(saveFiles.imagenToBase64(evidencia.getRuta()).getRuta());
                } catch (IOException ex) {
                    Logger.getLogger(FormatoInspeccionService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            for (Evidencia evidencia : formato.getPeligrosMecanicos().getEvidencias()) {
                try {
                    evidencia.setRuta(saveFiles.imagenToBase64(evidencia.getRuta()).getRuta());
                } catch (IOException ex) {
                    Logger.getLogger(FormatoInspeccionService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            for (Evidencia evidencia : formato.getPeligrosElectricos().getEvidencias()) {
                try {
                    evidencia.setRuta(saveFiles.imagenToBase64(evidencia.getRuta()).getRuta());
                } catch (IOException ex) {
                    Logger.getLogger(FormatoInspeccionService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            for (Evidencia evidencia : formato.getPeligrosLocativos().getEvidencias()) {
                try {
                    evidencia.setRuta(saveFiles.imagenToBase64(evidencia.getRuta()).getRuta());
                } catch (IOException ex) {
                    Logger.getLogger(FormatoInspeccionService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            for (Evidencia evidencia : formato.getEmergencias().getEvidencias()) {
                try {
                    evidencia.setRuta(saveFiles.imagenToBase64(evidencia.getRuta()).getRuta());
                } catch (IOException ex) {
                    Logger.getLogger(FormatoInspeccionService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
             for (Evidencia evidencia : formato.getOrdenAseo().getEvidencias()) {
                try {
                    evidencia.setRuta(saveFiles.imagenToBase64(evidencia.getRuta()).getRuta());
                } catch (IOException ex) {
                    Logger.getLogger(FormatoInspeccionService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             
              for (Evidencia evidencia : formato.getSaneamientoBasico().getEvidencias()) {
                try {
                    evidencia.setRuta(saveFiles.imagenToBase64(evidencia.getRuta()).getRuta());
                } catch (IOException ex) {
                    Logger.getLogger(FormatoInspeccionService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return formatos;
    }

    public FormatoInspeccion getById(int id) {
        return fir.findById(id).orElse(null);
    }

    public boolean cambiarEstado(int id) {
        FormatoInspeccion fi = fir.findById(id).orElse(null);
        if (fi != null) {
            fi.setEstado("REVISADO");
            fir.save(fi);
            return true;
        }
        return false;

    }

}
