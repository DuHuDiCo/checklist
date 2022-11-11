package com.checklist.checklist.utils;

import com.checklist.checklist.models.FormatoInspeccion;
import com.checklist.checklist.models.Pdf;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

@Service
public class GenerarPdf {

    public Pdf generarPdf(FormatoInspeccion formatoInspeccion) throws IOException {
        String titulo = "Formato de Inspeccion";
        String version = "Version: " + String.valueOf(formatoInspeccion.getVersion());
        String fecha_formato = "Fecha: " + formatoInspeccion.getFecha().toString();

        String name;
        String ruta;
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);
            int height = (int) page.getTrimBox().getHeight();//792
            int width = (int) page.getTrimBox().getWidth();//612
            PDPageContentStream contens = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.OVERWRITE, true);
            nuevaLinea(titulo, 220, 750, contens, PDType1Font.HELVETICA_BOLD, 18);
            nuevaLinea("Codigo:", 20, 720, contens, PDType1Font.HELVETICA_BOLD, 15);
            nuevaLinea(version, 191, 720, contens, PDType1Font.HELVETICA_BOLD, 15);
            nuevaLinea(fecha_formato, 382, 720, contens, PDType1Font.HELVETICA_BOLD, 15);
            nuevaLinea("Inspeccion Realizada Por: " + formatoInspeccion.getRealizadoBy(), 20, 680, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Fecha Inspeccion: " + formatoInspeccion.getFecha_inspeccion().toString(), 20, 660, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Almacen: " + formatoInspeccion.getPunto_venta(), 20, 640, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Elementos a Inspeccionar: ", 220, 560, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("Se cuenta con las hojas de seguridad de las sustancias quimicas: ", 20, 520, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 510, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 500, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getSustanciasQuimicas().isHojas_serguridad_sustancias_quimicas());
            //opcion
            nuevaLinea("Las sustancias quimicas se encuentran debidamente etiquetadas: ", 20, 480, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 470, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 460, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getSustanciasQuimicas().isSustancias_quimacas_etiquetadas());
            //opcion
            nuevaLinea("Se cuenta con gafas de seguridad y guantes de nitrilo o caucho para manipular las sustancias quimicas: ", 20, 440, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 430, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 420, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getSustanciasQuimicas().isGafas_seguridad_guantes_nitrilo());
            //opcion
            nuevaLinea("Las sustancias quimicas se encuentran debidamente almacenadas: ", 20, 400, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 390, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 380, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getSustanciasQuimicas().isSustancias_quimicas_almacenadas());
            //observaciones
            nuevaLinea("Observaciones: ", 20, 360, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea(formatoInspeccion.getSustanciasQuimicas().getObservaciones(), 20, 350, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("PELIGROS MECÁNICOS: ", 220, 330, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("Equipos y herramientas en buen estado: ", 20, 310, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 300, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 290, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosMecanicos().isEquipos_herramientas_buen_estado());
            //observaciones
            nuevaLinea("Observaciones: ", 20, 270, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea(formatoInspeccion.getSustanciasQuimicas().getObservaciones(), 20, 260, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("PELIGROS ELÉCTRICOS: ", 220, 240, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("Cables eléctricos en buen estado y debidamente entubados. (sin adiciones o cintas): ", 20, 220, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 210, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 200, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosElectricos().isCables_buen_estado_entubados());
            //opcion
            nuevaLinea("Los empalmes o conexiones están en buen estado: ", 20, 180, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 170, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 160, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosElectricos().isEmpalmes_conexiones_buen_estado());
            //opcion
            nuevaLinea("Tomas e interruptores en buen estado, cuentan con la tapa de protección, están debidamente anclados y señalizados: ", 20, 140, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 130, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 120, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosElectricos().isTomas_buen_estado_tapa_proteccion());
            //opcion
            nuevaLinea("Las cajas de breakers están sin sobrecarga y señalizadas: ", 20, 100, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 90, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 80, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosElectricos().isCajas_breakers_sin_sobrecarga_señaladas());
            //opcion
            nuevaLinea("Los tableros y cajas de breakers están libres de material combustible alrededor: ", 20, 60, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 50, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 40, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosElectricos().isTableros_caja_breakers_sin_material_combustible());
            //observaciones
            nuevaLinea("Observaciones: ", 20, 20, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea(formatoInspeccion.getSustanciasQuimicas().getObservaciones(), 20, 10, contens, PDType1Font.HELVETICA_BOLD, 13);
            contens.close();
            PDPage page2 = new PDPage();
            doc.addPage(page2);
            contens = new PDPageContentStream(doc, page2, PDPageContentStream.AppendMode.OVERWRITE, true);
            nuevaLinea("PELIGROS LOCATIVOS: ", 220, 750, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("Las luminarias se encuentran en buen estado: ", 20, 730, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 720, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 710, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosLocativos().isLuminarias_buen_estado());
            //opcion
            nuevaLinea("Los muros están en buen estado (Sin grietas, sin humedad, pintura buen estado): ", 20, 690, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 680, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 670, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosLocativos().isMuros_buen_estado());
            //opcion
            nuevaLinea("Escaleras en buen estado (pasamanos, cintas antideslizantes): ", 20, 650, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 640, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 630, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosLocativos().isEscaleras_buen_estado());
            //opcion
            nuevaLinea("Pisos en buen estado, sin grietas o desniveles: ", 20, 610, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 600, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 590, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosLocativos().isPisos_buen_estado());
            //opcion
            nuevaLinea("Ventanas, puertas en buen estado (manijas, chapas, sin vidrios fracturados): ", 20, 570, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 560, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 550, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosLocativos().isVentanas_puertas_buen_estado());
            //opcion
            nuevaLinea("Techos en buen estado (tejas sin fisuras o rotas, sin goteras): ", 20, 530, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 520, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 510, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosLocativos().isTechos_buen_estado());
            //opcion
            nuevaLinea("Áreas de circulación despejadas (escaleras, zonas de transito en almacén, etc.): ", 20, 490, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 480, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 470, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosLocativos().isAreas_circulacion_despejadas());
            //opcion
            nuevaLinea("La sillas y escritorios se encuentran en buen estado: ", 20, 450, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 440, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 430, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosLocativos().isSilla_escritorios_buen_estado());
            //opcion
            nuevaLinea("Las divisiones modulares, escritorio y cajones se encuentran en buenas condiciones. (anclados y ajustados): ", 20, 410, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 400, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 390, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getPeligrosLocativos().isDivisiones_modulares_escritorio_cajones_buenas_condiciones());
            //observaciones
            nuevaLinea("Observaciones: ", 20, 370, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea(formatoInspeccion.getPeligrosLocativos().getObservaciones(), 20, 360, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("EMERGENCIAS: ", 220, 340, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("La ruta de evacuación se encuentra señalizada y libre de obstáculos: ", 20, 320, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 310, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 300, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getEmergencias().isRuta_evacuacion_señalizada_libre_obstaculos());
            //opcion
            nuevaLinea("Las salida de emergencia y punto de encuentro se encuentran despejadas y señalizadas: ", 20, 280, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 270, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 260, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getEmergencias().isSalida_emergencia_punto_encuentro_señalizadas_despejadas());
            //opcion
            nuevaLinea("La lista de teléfonos de emergencia publicada y socializada: ", 20, 240, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 230, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 220, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getEmergencias().isLista_telefonos_emergencia_publicada_socializada());
            //opcion
            nuevaLinea("Los extintores se encuentran en buen estado, recargados, señalizados y libres de obstáculos: ", 20, 200, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 190, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 180, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getEmergencias().isExtintores_buen_estado_recargados_señalizados_libre_obstaculos());
            //opcion
            nuevaLinea("Se cuenta con camilla de emergencia, esta en buen estado, cuenta con cuello inmovilizador, correas de sujeción y se encuentra libre de obstáculos: ", 20, 160, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 150, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 140, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getEmergencias().isCamilla_emergencia_buen_estado_cuello_inmovilizador());
            //opcion
            nuevaLinea("El botiquín se encuentra en buen estado y cuenta con los elementos necesarios para la atención de primeros auxilios: ", 20, 120, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 110, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 100, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getEmergencias().isBotiquin_buen_estado_elementos_necesarios());
            //observaciones
            nuevaLinea("Observaciones: ", 20, 70, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea(formatoInspeccion.getEmergencias().getObservaciones(), 20, 60, contens, PDType1Font.HELVETICA_BOLD, 13);
            contens.close();
            PDPage page3 = new PDPage();
            doc.addPage(page3);
            contens = new PDPageContentStream(doc, page3, PDPageContentStream.AppendMode.OVERWRITE, true);
            nuevaLinea("ORDEN Y ASEO: ", 220, 750, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("El punto de venta se encuentra en buen estado de aseo y mantenimiento.: ", 20, 730, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 720, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 710, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getOrdenAseo().isPunto_venta_buen_estado_aseo_mantenimiento());
            //opcion
            nuevaLinea("Los archivadores se encuentran organizados y los documentos o libros debidamente almacenados: ", 20, 690, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 680, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 670, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getOrdenAseo().isArchivadores_organizados_documento_libros_almacenados());
            //observaciones
            nuevaLinea("Observaciones: ", 20, 650, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea(formatoInspeccion.getOrdenAseo().getObservaciones(), 20, 640, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("SANEAMIENTO BÁSICO: ", 220, 620, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("Se cuenta con puntos ecológicos de disposición de residuos: ", 20, 600, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 590, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 580, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getSaneamientoBasico().isPuntos_ecologicos_residuos());
            //opcion
            nuevaLinea("Se cuenta con guantes ne nitrilo o caucho para manipular los residuos: ", 20, 560, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 550, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 540, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getSaneamientoBasico().isGuantes_nitrilo_caucho());
            //opcion
            nuevaLinea("El cuarto de residuos se encuentra limpio: ", 20, 520, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 510, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 500, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getSaneamientoBasico().isCuarto_residuos_limpio());
            //opcion
            nuevaLinea("El servicio sanitario esta en optimas condiciones: ", 20, 480, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 470, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 460, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getSaneamientoBasico().isServicio_sanitario_optimas_condiciones());
            //opcion
            nuevaLinea("Los baños cuentan con papel higiénico, jabón, toallas y papeleras con pedal y tapa: ", 20, 440, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 430, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 420, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getSaneamientoBasico().isBaños_papel_higienico_jabon_toallas_papeleras());
            //opcion
            nuevaLinea("Se han identificado insectos o roedores, se han fumigado las áreas: ", 20, 400, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Cumple: ", 20, 390, contens, PDType1Font.HELVETICA_BOLD, 13);
            validarSIoNo(20, 380, contens, PDType1Font.HELVETICA_BOLD, 13, formatoInspeccion.getSaneamientoBasico().isInsectos_roedores_fumigado_areas());
            //observaciones
            nuevaLinea("Observaciones: ", 20, 360, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea(formatoInspeccion.getSaneamientoBasico().getObservaciones(), 20, 350, contens, PDType1Font.HELVETICA_BOLD, 13);
            name = "formato_"+formatoInspeccion.getPunto_venta()+"_"+obtenerFechaServer("yyyy-MM-dd")+".pdf";
            ruta = "J:\\Descargas\\pdf\\"+name;
            contens.close();
            doc.save(ruta);
            doc.close();
        }
        
        Pdf pdf = new Pdf();
        pdf.setNombre(name);
        pdf.setRuta(ruta);
        
        return pdf;
    }

    public static void nuevaLinea(String linea, int x, int y, PDPageContentStream contens, PDFont fuente, int tamañoFont) throws IOException {
        contens.beginText();
        PDFont font = fuente;
        contens.setFont(font, tamañoFont);
        contens.newLineAtOffset(x, y);

        contens.showText(linea);
        contens.endText();
    }

    public static void validarSIoNo(int x, int y, PDPageContentStream contens, PDFont fuente, int tamañoFont, boolean dato) throws IOException {
        if (dato) {
            nuevaLinea("SI", x, y, contens, fuente, tamañoFont);
        } else {
            nuevaLinea("NO", x, y, contens, fuente, tamañoFont);
        }
    }
    
     public static String obtenerFechaServer(String formato) {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat(formato);

        java.util.Date datObj = calendar.getTime();
        String formattedDate = sdf.format(datObj);
       

        return formattedDate;
    }

}
