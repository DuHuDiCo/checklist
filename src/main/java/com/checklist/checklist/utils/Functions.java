
package com.checklist.checklist.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Functions {
    
   public static Date  obtenerFechaYhora() throws ParseException{
        Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaTexto = formatter.format(fecha);
        Date date = formatter.parse(fechaTexto);
        return date;
        
    }

}
