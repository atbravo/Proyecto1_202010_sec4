package model.logic;

public class Informacion {
	private int OBJECTID;
	private String FECHA_HORA;
	private String MEDIO_DETE;
	private String CLASE_VEHI;
	private String TIPO_SERVI;
	private String INFRACCION;
	private String DES_INFRACCION;
	private String LOCALIDAD;

	
	public Informacion(int ID, String fecha, String detencion, String claseVehiculo, String servicio, 
						String  infrac, String descInfraccion, String localidad)
	{
		OBJECTID = ID;
		FECHA_HORA = fecha;
		MEDIO_DETE = detencion;
		CLASE_VEHI = claseVehiculo;
		TIPO_SERVI = servicio;
		INFRACCION = infrac;
		DES_INFRACCION = descInfraccion;
		LOCALIDAD = localidad;
	}
	public int darID()
	{
		return OBJECTID;
	}
	public String darfecha()
	{
		return FECHA_HORA;
	}
	public String darTipoDetencion()
	{
		return MEDIO_DETE;
	}
	public String darclaseVehiculo()
	{
		return CLASE_VEHI;
	}
	public String darTipoServicio()
	{
		return TIPO_SERVI;
	}
	public String darInfraccion()
	{
		return INFRACCION;
	}
	public String darLocalidad()
	{
		return LOCALIDAD;
	}
	public String darDescripcion()
	{
		return DES_INFRACCION;
	}
	public String toString()
	{
		return "Id: " + darID() + "\n" + "Fecha:" + darfecha() + "\n" + "Detencion: " + MEDIO_DETE
				+ "\n" + "Vehiculo: " + CLASE_VEHI + "\n" + "Servicio: " + TIPO_SERVI + "\n" +
				"Infraccion: " + INFRACCION + "\n" + "Localidad:" + LOCALIDAD;
	}
}
