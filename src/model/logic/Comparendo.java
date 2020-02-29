package model.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comparendo implements Comparable<Comparendo> {
	private String type;
	private Informacion properties;
	private Ubicacion geometry;

	public Comparendo(String typ, String tipo, double latitud, double longitud, double ceros, int OBJECTID,
			String FECHA_HORA, String MEDIO_DETE, String CLASE_VEHI, String TIPO_SERVI, String INFRACCION,
			String DES_INFRACCION, String LOCALIDAD) {
		properties = new Informacion(OBJECTID, FECHA_HORA, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION,
				DES_INFRACCION, LOCALIDAD);
		geometry = new Ubicacion(tipo, latitud, longitud, ceros);
		type = typ;

	}

	public Informacion darDetalles() {
		return properties;
	}

	public Ubicacion darUbicacion() {
		return geometry;
	}

	public String toString() {
		String string = properties.toString() + "\n";
		String string2 = geometry.toString();
		return string + string2;
	}

	@Override
	/**
	 * Compara el comparendo actual con el que llega por parametro
	 * se compara por fecha, si las fechas son iguales copmara por ID
	 * return >0 si el actual es mayor al parametro, 0 si son iguales
	 *  <0 de lo contario
	 */
	public int compareTo(Comparendo o) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		int respuesta = 0;
		try {
			Date f1 = sf.parse(properties.darfecha());
			Date f2 = sf.parse(o.darDetalles().darfecha());
			respuesta = (f1.compareTo(f2));
		} catch (ParseException e) {
		}
		if (respuesta == 0) {
			if (properties.darID() > o.darDetalles().darID())
				respuesta = 1;
			else if (properties.darID() < o.darDetalles().darID())
				respuesta = -1;
		}
		return respuesta;
	}
}
