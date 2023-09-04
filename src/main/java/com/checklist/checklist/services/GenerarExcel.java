package com.checklist.checklist.services;

import com.checklist.checklist.models.FormatoInspeccion;
import com.checklist.checklist.models.ReporteExcel;
import com.checklist.checklist.repository.ReporteExcelRepository;
import com.checklist.checklist.utils.Functions;
import com.checklist.checklist.utils.GenerarPdf;
import com.checklist.checklist.utils.SaveFiles;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class GenerarExcel {

    private final FormatoInspeccionService formatoInspeccionService;

    private final AlmacenService almacenService;
    
    private final GenerarPdf  generarPdf;
    
    private final ReporteExcelRepository excelRepository;
    
    private final SaveFiles  saveFiles;
    
//    private final String RUTA = "/var/lib/tomcat9/webapps/checklist/uploads/";
    private final String RUTA = "J:\\Descargas\\upload\\";

    public GenerarExcel(FormatoInspeccionService formatoInspeccionService, AlmacenService almacenService, GenerarPdf generarPdf, ReporteExcelRepository excelRepository, SaveFiles saveFiles) {
        this.formatoInspeccionService = formatoInspeccionService;
        this.almacenService = almacenService;
        this.generarPdf = generarPdf;
        this.excelRepository = excelRepository;
        this.saveFiles = saveFiles;
    }

    

    


    public ReporteExcel generarReporteExcel() {
        List<FormatoInspeccion> reportes = formatoInspeccionService.getAll();
        System.out.println(reportes.size());

        
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Hoja1");

            Row row = sheet.createRow(0);
            for (int i = 1; i < reportes.size(); i++) {

                Cell headerCell = row.createCell(i);
                headerCell.setCellValue(reportes.get(i - 1).getPunto_venta());

            }

            String[] items = {"Se cuenta con las hojas de seguridad de las sustancias quimicas:", "Las sustancias quimicas se encuentran debidamente etiquetadas:", "Se cuenta con gafas de seguridad y guantes de nitrilo o caucho para manipular las sustancias quimicas:", "Las sustancias quimicas se encuentran debidamente almacenadas:"};
            crearEncabezadosYtitulos(sheet, 1, 2, "ELEMENTOS A INSPECCIONAR", items, workbook);

            String[] items2 = {"Equipos y herramientas en buen estado:"};
            crearEncabezadosYtitulos(sheet, 6, 7, "PELIGROS MECÁNICOS", items2, workbook);

            String[] items3 = {"Cables eléctricos en buen estado y debidamente entubados. (sin adiciones o cintas):", "Los empalmes o conexiones están en buen estado.:",
                "Tomas e interruptores en buen estado, cuentan con la tapa de protección, están debidamente anclados y señalizados:", "Las cajas de breakers están sin sobrecarga y señalizadas:", "Los tableros y cajas de breakers están libres de material combustible alrededor:"};
            crearEncabezadosYtitulos(sheet, 8, 9, "PELIGROS ELÉCTRICOS", items3, workbook);

            String[] items4 = {"Las luminarias se encuentran en buen estado:", "Los muros están en buen estado (Sin grietas, sin humedad, pintura buen estado).:",
                "Escaleras en buen estado (pasamanos, cintas antideslizantes):", "Pisos en buen estado, sin grietas o desniveles:", "Ventanas, puertas en buen estado (manijas, chapas, sin vidrios fracturados).:",
                "Techos en buen estado (tejas sin fisuras o rotas, sin goteras).:", "Áreas de circulación despejadas (escaleras, zonas de transito en almacén, etc.).:",
                "La sillas y escritorios se encuentran en buen estado:", "Las divisiones modulares, escritorio y cajones se encuentran en buenas condiciones. (anclados y ajustados):"
            };
            crearEncabezadosYtitulos(sheet, 13, 14, "PELIGROS LOCATIVOS", items4, workbook);

            String[] items5 = {"La ruta de evacuación se encuentra señalizada y libre de obstáculos:", "Las salida de emergencia y punto de encuentro se encuentran despejadas y señalizadas:",
                "La lista de teléfonos de emergencia publicada y socializada:", "Los extintores se encuentran en buen estado, recargados, señalizados y libres de obstáculos:", "Se cuenta con camilla de emergencia, esta en buen estado, cuenta con cuello inmovilizador, correas de sujeción y se encuentra libre de obstáculos:",
                "El botiquín se encuentra en buen estado y cuenta con los elementos necesarios para la atención de primeros auxilios:"};
            crearEncabezadosYtitulos(sheet, 23, 24, "EMERGENCIAS", items5, workbook);

            String[] items6 = {"El punto de venta se encuentra en buen estado de aseo y mantenimiento.:", "Los archivadores se encuentran organizados y los documentos o libros debidamente almacenados:"};
            crearEncabezadosYtitulos(sheet, 30, 31, "ORDEN Y ASEO", items6, workbook);

            String[] items7 = {"Se cuenta con puntos ecológicos de disposición de residuos:", "Se cuenta con guantes ne nitrilo o caucho para manipular los residuos:",
                "El cuarto de residuos se encuentra limpio:", "El servicio sanitario esta en optimas condiciones:", "Los baños cuentan con papel higiénico, jabón, toallas y papeleras con pedal y tapa.:",
                "Se han identificado insectos o roedores, se han fumigado las áreas:"};
            crearEncabezadosYtitulos(sheet, 33, 34, "SANEAMIENTO BÁSICO", items7, workbook);
            
            
            String filename = "reporte_".concat(Long.toString(System.currentTimeMillis())).concat(".xlsx");
            String path = RUTA.concat(filename);

            try (FileOutputStream output = new FileOutputStream(path)) {
                workbook.write(output);
                
                ReporteExcel reporte = new ReporteExcel();
                reporte.setNombreReporte(filename);
                reporte.setRuta(path);
                reporte.setFechaCreacion(Functions.obtenerFechaYhora());
                
                ReporteExcel reporteGenerated = excelRepository.save(reporte);
                reporteGenerated.setRuta(saveFiles.imagenToBase64(reporteGenerated.getRuta()).getRuta());
                return reporteGenerated;
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void crearEncabezadosYtitulos(Sheet sheet, int fila, int filaCelda, String titulo, String[] items, Workbook workbook) {
        List<FormatoInspeccion> reportes = formatoInspeccionService.getAll();
        for (int i = fila; i < filaCelda; i++) {
            Row rowTitles = sheet.createRow(i);
            CellStyle cell = workbook.createCellStyle();
            cell.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            cell.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            rowTitles.setRowStyle(cell);

            Cell cellTitel = rowTitles.createCell(0);
            cellTitel.setCellValue(titulo);
            cellTitel.setCellStyle(cell);

            for (int j = 0; j < items.length; j++) {

                Row rowItems = sheet.createRow(j + filaCelda);
                Cell itemsCell = rowItems.createCell(0);
                CellStyle cellStyle = workbook.createCellStyle();
                 cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                itemsCell.setCellStyle(cellStyle);

                itemsCell.setCellValue(items[j]);

                if (titulo.equals("ELEMENTOS A INSPECCIONAR")) {
                    int dato = j;
                    switch (dato) {
                        case 0:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);
                                String value = reportes.get(k - 1).getSustanciasQuimicas().isHojas_serguridad_sustancias_quimicas() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);

                            }
                            break;
                        case 1:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getSustanciasQuimicas().isSustancias_quimacas_etiquetadas() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);

                            }
                            break;
                        case 2:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getSustanciasQuimicas().isGafas_seguridad_guantes_nitrilo() ? "CUMPLE" : "NO CUMPLE";;
                                itCell.setCellValue(value);
                            }
                            break;
                        case 3:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getSustanciasQuimicas().isSustancias_quimicas_almacenadas() ? "CUMPLE" : "NO CUMPLE";;
                                itCell.setCellValue(value);

                            }
                            break;
                    }

                }

                if (titulo.equals("PELIGROS MECÁNICOS")) {
                    int dato = j;
                    switch (dato) {
                        case 0:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosMecanicos().isEquipos_herramientas_buen_estado() ? "CUMPLE" : "NO CUMPLE";;
                                itCell.setCellValue(value);

                            }
                            break;

                    }

                }

                if (titulo.equals("PELIGROS ELÉCTRICOS")) {
                    int dato = j;
                    switch (dato) {
                        case 0:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosElectricos().isCables_buen_estado_entubados() ? "CUMPLE" : "NO CUMPLE";;
                                itCell.setCellValue(value);

                            }
                            break;
                        case 1:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosElectricos().isEmpalmes_conexiones_buen_estado() ? "CUMPLE" : "NO CUMPLE";;
                                itCell.setCellValue(value);

                            }
                            break;
                        case 2:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosElectricos().isTomas_buen_estado_tapa_proteccion() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 3:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosElectricos().isCajas_breakers_sin_sobrecarga_señaladas() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);

                            }
                            break;
                        case 4:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosElectricos().isTableros_caja_breakers_sin_material_combustible() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                    }

                }

                if (titulo.equals("PELIGROS LOCATIVOS")) {
                    int dato = j;
                    switch (dato) {
                        case 0:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosLocativos().isLuminarias_buen_estado() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 1:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosLocativos().isMuros_buen_estado() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);

                            }
                            break;
                        case 2:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosLocativos().isEscaleras_buen_estado() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 3:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosLocativos().isPisos_buen_estado() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);

                            }
                            break;
                        case 4:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosLocativos().isVentanas_puertas_buen_estado() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 5:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosLocativos().isTechos_buen_estado() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 6:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosLocativos().isAreas_circulacion_despejadas() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 7:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosLocativos().isSilla_escritorios_buen_estado() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 8:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getPeligrosLocativos().isDivisiones_modulares_escritorio_cajones_buenas_condiciones() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                    }

                }

                if (titulo.equals("EMERGENCIAS")) {
                    int dato = j;
                    switch (dato) {
                        case 0:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getEmergencias().isRuta_evacuacion_señalizada_libre_obstaculos() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 1:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getEmergencias().isSalida_emergencia_punto_encuentro_señalizadas_despejadas() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 2:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getEmergencias().isLista_telefonos_emergencia_publicada_socializada() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 3:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getEmergencias().isExtintores_buen_estado_recargados_señalizados_libre_obstaculos() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 4:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getEmergencias().isCamilla_emergencia_buen_estado_cuello_inmovilizador() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 5:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getEmergencias().isBotiquin_buen_estado_elementos_necesarios() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                    }

                }

                if (titulo.equals("ORDEN Y ASEO")) {
                    int dato = j;
                    switch (dato) {
                        case 0:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getOrdenAseo().isPunto_venta_buen_estado_aseo_mantenimiento() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 1:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getOrdenAseo().isArchivadores_organizados_documento_libros_almacenados() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                    }

                }

                if (titulo.equals("SANEAMIENTO BÁSICO")) {
                    int dato = j;
                    switch (dato) {
                        case 0:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getSaneamientoBasico().isPuntos_ecologicos_residuos() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 1:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getSaneamientoBasico().isGuantes_nitrilo_caucho() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 2:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getSaneamientoBasico().isCuarto_residuos_limpio() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 3:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getSaneamientoBasico().isServicio_sanitario_optimas_condiciones() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 4:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getSaneamientoBasico().isBaños_papel_higienico_jabon_toallas_papeleras() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                        case 5:
                            for (int k = 1; k < reportes.size(); k++) {
                                Cell itCell = rowItems.createCell(k);

                                String value = reportes.get(k - 1).getSaneamientoBasico().isInsectos_roedores_fumigado_areas() ? "CUMPLE" : "NO CUMPLE";
                                itCell.setCellValue(value);
                            }
                            break;
                    }

                }

            }

        }

    }

}
