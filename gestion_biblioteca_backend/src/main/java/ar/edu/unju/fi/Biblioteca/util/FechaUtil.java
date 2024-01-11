package ar.edu.unju.fi.Biblioteca.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FechaUtil {
	

    // Verifica si un libro está aún prestado (fecha de devolución nula)
    // y calcula los días transcurridos desde el préstamo
    public static int diasTranscurridos(LocalDate fechaInicio, LocalDate fechaFin) {
        // Verificar si la fecha de devolución es nula
        if (fechaInicio == null || fechaFin == null) {
            return -1; // Código de error o valor indicando que las fechas son nulas
        }

        // Calcular los días transcurridos utilizando ChronoUnit
        return (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }
	
	
	//devuelve la fecha formateada como una cadena en el formato DD/MM/YYYY
	  public static String formatearFecha(LocalDate fecha) {
	        if (fecha == null) {
	            return null; // en caso de una fecha nula no hacer nada
	        }
	        // Formatear la fecha utilizando DateTimeFormatter
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        return fecha.format(formatter);
	}
	

}
