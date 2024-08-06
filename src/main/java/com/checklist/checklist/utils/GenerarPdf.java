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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GenerarPdf {

    @Value("${ruta.pdf}")
    String ruta;

    public Pdf generarPdf(FormatoInspeccion formatoInspeccion) throws IOException {
        String titulo = "Formato de Inspeccion";
        String version = "Version: " + String.valueOf(formatoInspeccion.getVersion());
        String fecha_formato = "Fecha: " + formatoInspeccion.getFecha().toString();

        String name;

        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);
            int height = (int) page.getTrimBox().getHeight();//792
            int width = (int) page.getTrimBox().getWidth();//612
            PDPageContentStream contens = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.OVERWRITE, true);
            nuevaLinea(titulo, 180, 750, contens, PDType1Font.HELVETICA_BOLD, 18);
            nuevaLinea("Codigo:", 20, 720, contens, PDType1Font.HELVETICA_BOLD, 15);
            nuevaLinea(version, 191, 720, contens, PDType1Font.HELVETICA_BOLD, 15);
            nuevaLinea(fecha_formato, 382, 720, contens, PDType1Font.HELVETICA_BOLD, 15);
            nuevaLinea("Inspeccion Realizada Por: " + formatoInspeccion.getRealizadoBy(), 20, 680, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Fecha Inspeccion: " + formatoInspeccion.getFecha_inspeccion().toString(), 20, 660, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Almacen: " + formatoInspeccion.getPunto_venta(), 20, 640, contens, PDType1Font.HELVETICA_BOLD, 13);
            nuevaLinea("Elementos a Inspeccionar: ", 180, 560, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("Se cuenta con las hojas de seguridad de las sustancias quimicas: ", 20, 520, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:     " + validarSIoNo(formatoInspeccion.getSustanciasQuimicas().isHojas_serguridad_sustancias_quimicas()), 20, 510, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Las sustancias quimicas se encuentran debidamente etiquetadas: ", 20, 480, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:" + validarSIoNo(formatoInspeccion.getSustanciasQuimicas().isSustancias_quimacas_etiquetadas()), 20, 470, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Se cuenta con gafas de seguridad y guantes de nitrilo o caucho  para manipular las sustancias quimicas: ", 20, 440, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getSustanciasQuimicas().isGafas_seguridad_guantes_nitrilo()), 20, 430, contens, PDType1Font.HELVETICA_BOLD, 11);

            nuevaLinea("Las sustancias quimicas se encuentran debidamente almacenadas: ", 20, 400, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getSustanciasQuimicas().isSustancias_quimicas_almacenadas()), 20, 390, contens, PDType1Font.HELVETICA_BOLD, 11);

            //observaciones
            nuevaLinea("Observaciones: ", 20, 360, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea(formatoInspeccion.getSustanciasQuimicas().getObservaciones(), 20, 350, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("PELIGROS MECÁNICOS: ", 180, 330, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("Equipos y herramientas en buen estado: ", 20, 310, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosMecanicos().isEquipos_herramientas_buen_estado()), 20, 300, contens, PDType1Font.HELVETICA_BOLD, 11);

            //observaciones
            nuevaLinea("Observaciones: ", 20, 270, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea(formatoInspeccion.getSustanciasQuimicas().getObservaciones(), 20, 260, contens, PDType1Font.HELVETICA_BOLD, 11);

            nuevaLinea("PELIGROS ELÉCTRICOS: ", 180, 240, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("Cables eléctricos en buen estado y debidamente entubados. (sin adiciones o cintas): ", 20, 220, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosElectricos().isCables_buen_estado_entubados()), 20, 210, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Los empalmes o conexiones están en buen estado: ", 20, 180, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosElectricos().isEmpalmes_conexiones_buen_estado()), 20, 170, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Tomas e interruptores en buen estado, cuentan con la tapa de protección,  ", 20, 140, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("están debidamente anclados y señalizados: ,  ", 20, 130, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosElectricos().isTomas_buen_estado_tapa_proteccion()), 20, 120, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Las cajas de breakers están sin sobrecarga y señalizadas: ", 20, 100, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosElectricos().isCajas_breakers_sin_sobrecarga_señaladas()), 20, 90, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Los tableros y cajas de breakers están libres de material combustible alrededor: ", 20, 60, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:     " + validarSIoNo(formatoInspeccion.getPeligrosElectricos().isTableros_caja_breakers_sin_material_combustible()), 20, 50, contens, PDType1Font.HELVETICA_BOLD, 11);

            //observaciones
            nuevaLinea("Observaciones: ", 20, 20, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea(formatoInspeccion.getSustanciasQuimicas().getObservaciones(), 20, 10, contens, PDType1Font.HELVETICA_BOLD, 11);
            contens.close();
            PDPage page2 = new PDPage();
            doc.addPage(page2);
            contens = new PDPageContentStream(doc, page2, PDPageContentStream.AppendMode.OVERWRITE, true);
            nuevaLinea("PELIGROS LOCATIVOS: ", 180, 750, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("Las luminarias se encuentran en buen estado: ", 20, 730, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosLocativos().isLuminarias_buen_estado()), 20, 720, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Los muros están en buen estado (Sin grietas, sin humedad, pintura buen estado): ", 20, 690, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosLocativos().isMuros_buen_estado()), 20, 680, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Escaleras en buen estado (pasamanos, cintas antideslizantes): ", 20, 650, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosLocativos().isEscaleras_buen_estado()), 20, 640, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Pisos en buen estado, sin grietas o desniveles: ", 20, 610, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosLocativos().isPisos_buen_estado()), 20, 600, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Ventanas, puertas en buen estado (manijas, chapas, sin vidrios fracturados): ", 20, 570, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosLocativos().isVentanas_puertas_buen_estado()), 20, 560, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Techos en buen estado (tejas sin fisuras o rotas, sin goteras): ", 20, 530, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosLocativos().isTechos_buen_estado()), 20, 520, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Áreas de circulación despejadas (escaleras, zonas de transito en almacén, etc.): ", 20, 490, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosLocativos().isAreas_circulacion_despejadas()), 20, 480, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("La sillas y escritorios se encuentran en buen estado: ", 20, 450, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosLocativos().isSilla_escritorios_buen_estado()), 20, 440, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Las divisiones modulares, escritorio y cajones se encuentran en buenas condiciones. (anclados y ajustados): ", 20, 410, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getPeligrosLocativos().isDivisiones_modulares_escritorio_cajones_buenas_condiciones()), 20, 400, contens, PDType1Font.HELVETICA_BOLD, 11);

            //observaciones
            nuevaLinea("Observaciones: ", 20, 370, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea(formatoInspeccion.getPeligrosLocativos().getObservaciones(), 20, 360, contens, PDType1Font.HELVETICA_BOLD, 11);

            nuevaLinea("EMERGENCIAS: ", 180, 340, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("La ruta de evacuación se encuentra señalizada y libre de obstáculos: ", 20, 320, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getEmergencias().isRuta_evacuacion_señalizada_libre_obstaculos()), 20, 310, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Las salida de emergencia y punto de encuentro se encuentran despejadas y señalizadas: ", 20, 280, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getEmergencias().isSalida_emergencia_punto_encuentro_señalizadas_despejadas()), 20, 270, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("La lista de teléfonos de emergencia publicada y socializada: ", 20, 240, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getEmergencias().isLista_telefonos_emergencia_publicada_socializada()), 20, 230, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Los extintores se encuentran en buen estado, recargados, señalizados y libres de obstáculos: ", 20, 200, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getEmergencias().isExtintores_buen_estado_recargados_señalizados_libre_obstaculos()), 20, 190, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Se cuenta con camilla de emergencia, esta en buen estado,", 20, 170, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("cuenta con cuello inmovilizador, correas de sujeción y se encuentra libre de obstáculos: ", 20, 160, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getEmergencias().isCamilla_emergencia_buen_estado_cuello_inmovilizador()), 20, 150, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("El botiquín se encuentra en buen estado y cuenta con los elementos necesarios", 20, 130, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("para la atención de primeros auxilios: ", 20, 120, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getEmergencias().isBotiquin_buen_estado_elementos_necesarios()), 20, 110, contens, PDType1Font.HELVETICA_BOLD, 11);

            //observaciones
            nuevaLinea("Observaciones: ", 20, 70, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea(formatoInspeccion.getEmergencias().getObservaciones(), 20, 60, contens, PDType1Font.HELVETICA_BOLD, 11);
            contens.close();
            PDPage page3 = new PDPage();
            doc.addPage(page3);
            contens = new PDPageContentStream(doc, page3, PDPageContentStream.AppendMode.OVERWRITE, true);
            nuevaLinea("ORDEN Y ASEO: ", 180, 750, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("El punto de venta se encuentra en buen estado de aseo y mantenimiento.: ", 20, 730, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:     " + validarSIoNo(formatoInspeccion.getOrdenAseo().isPunto_venta_buen_estado_aseo_mantenimiento()), 20, 720, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Los archivadores se encuentran organizados y los documentos o libros  debidamente almacenados: ", 20, 690, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getOrdenAseo().isArchivadores_organizados_documento_libros_almacenados()), 20, 680, contens, PDType1Font.HELVETICA_BOLD, 11);

            //observaciones
            nuevaLinea("Observaciones: ", 20, 650, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea(formatoInspeccion.getOrdenAseo().getObservaciones(), 20, 640, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("SANEAMIENTO BÁSICO: ", 180, 620, contens, PDType1Font.HELVETICA_BOLD, 18);
            //opcion
            nuevaLinea("Se cuenta con puntos ecológicos de disposición de residuos: ", 20, 600, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getSaneamientoBasico().isPuntos_ecologicos_residuos()), 20, 590, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Se cuenta con guantes ne nitrilo o caucho para manipular los residuos: ", 20, 560, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getSaneamientoBasico().isGuantes_nitrilo_caucho()), 20, 550, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("El cuarto de residuos se encuentra limpio: ", 20, 520, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getSaneamientoBasico().isCuarto_residuos_limpio()), 20, 510, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("El servicio sanitario esta en optimas condiciones: ", 20, 480, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getSaneamientoBasico().isServicio_sanitario_optimas_condiciones()), 20, 470, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Los baños cuentan con papel higiénico, jabón, toallas y papeleras con pedal y tapa: ", 20, 440, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getSaneamientoBasico().isBaños_papel_higienico_jabon_toallas_papeleras()), 20, 430, contens, PDType1Font.HELVETICA_BOLD, 11);

            //opcion
            nuevaLinea("Se han identificado insectos o roedores, se han fumigado las áreas: ", 20, 400, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea("Cumple:    " + validarSIoNo(formatoInspeccion.getSaneamientoBasico().isInsectos_roedores_fumigado_areas()), 20, 390, contens, PDType1Font.HELVETICA_BOLD, 11);

            //observaciones
            nuevaLinea("Observaciones: ", 20, 360, contens, PDType1Font.HELVETICA_BOLD, 11);
            nuevaLinea(formatoInspeccion.getSaneamientoBasico().getObservaciones(), 20, 350, contens, PDType1Font.HELVETICA_BOLD, 11);
            name = "formato_" + formatoInspeccion.getPunto_venta() + "_" + obtenerFechaServer("yyyy-MM-dd") + ".pdf";

            contens.close();
            doc.save(ruta.concat(name));
            doc.close();
        }

        Pdf pdf = new Pdf();
        pdf.setNombre(name);
        pdf.setRuta(ruta.concat(name));

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

    public static String validarSIoNo(boolean dato) throws IOException {
        if (dato) {
            return "SI";
        } else {
            return "NO";
        }
    }

    public String obtenerFechaServer(String formato) {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat(formato);

        java.util.Date datObj = calendar.getTime();
        String formattedDate = sdf.format(datObj);

        return formattedDate;
    }

}
