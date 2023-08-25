package com.checklist.checklist.services;

import com.checklist.checklist.models.FormatoInspeccion;
import com.checklist.checklist.models.ReporteExcel;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class GenerarExcel {
    
    private final FormatoInspeccionService formatoInspeccionService;
    
    private final AlmacenService almacenService;
    
    public GenerarExcel(FormatoInspeccionService formatoInspeccionService, AlmacenService almacenService) {
        this.formatoInspeccionService = formatoInspeccionService;
        this.almacenService = almacenService;
    }
    
    public ReporteExcel generarReporteExcel() {
        List<FormatoInspeccion> reportes = formatoInspeccionService.getAll();
        System.out.println(reportes.size());
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Hoja1");
            crearEncabezadosYtitulos(sheet, reportes);
            
            try (FileOutputStream output = new FileOutputStream("J:\\Descargas\\ejemplo.xlsx")) {
                workbook.write(output);
                
            } catch (Exception e) {
            }
            
        } catch (Exception e) {
        }
        return null;
    }
    
    public void crearEncabezadosYtitulos(Sheet sheet, List<FormatoInspeccion> reportes) {
        Row row = sheet.createRow(0);
        for (int i = 1; i < reportes.size(); i++) {
            
            Cell headerCell = row.createCell(i);
            headerCell.setCellValue(reportes.get(i).getPunto_venta());
            
        }
        
        for (int i = 1; i < 2; i++) {
            Row rowTitles = sheet.createRow(i);
            Cell cellTitel = rowTitles.createCell(0);
            cellTitel.setCellValue("PELIGROS ELECTRICOS");
            
           
            for (int j = 0; j < 4; j++) {
                 Row rowItems = sheet.createRow(j+2);
                Cell itemsCell = rowItems.createCell(0);
                itemsCell.setCellValue("Cables eléctricos en buen estado y debidamente entubados. (sin adiciones o cintas):");
                
            }
            
            
        }
        
    }
    
}
