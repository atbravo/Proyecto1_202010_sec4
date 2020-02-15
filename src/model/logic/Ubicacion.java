package model.logic;

public class Ubicacion {
	private String type;
	private double[] coordinates;
	
	public Ubicacion(String tipo, double latitud, double longitud, double ceros)
	{
		type = tipo;
		coordinates = new double[3];
		coordinates[0] = latitud;
		coordinates[1] = longitud;
		coordinates[2] = ceros;
		
	}
	public double darLatitud()
	{
		return coordinates[0];
	}
	public double darLongitud()
	{
		return coordinates[1];
	}
	public String toString()
	{
		return "Latitud: " + darLatitud() + "\n" + "Longitud:" + darLongitud();
	}
}
