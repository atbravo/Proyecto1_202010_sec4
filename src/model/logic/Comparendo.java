package model.logic;

public class Comparendo
{
	private String type;
	private Informacion properties;
	private Ubicacion geometry;



	public Comparendo(String typ, String tipo, double latitud, double longitud, double ceros,
			int OBJECTID, String FECHA_HORA, String MEDIO_DETE, String CLASE_VEHI, String TIPO_SERVI, 
			String  INFRACCION, String DES_INFRACCION, String LOCALIDAD	)
	{
		properties = new Informacion(OBJECTID, FECHA_HORA, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, DES_INFRACCION, LOCALIDAD);
		geometry = new Ubicacion(tipo, latitud, longitud, ceros);
		type = typ;
		
	}
	public Informacion darDetalles()
	{
		return properties;
	}
	public Ubicacion darUbicacion()
	{
		return geometry;
	}
	public String toString()
	{
		String string = properties.toString() + "\n" ;
		String string2 = geometry.toString();
		return string + string2;
	}
}
