package model.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;
import model.data_structures.Lista;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {


	public final String RUTA = "./data/comparendos_dei_2018.geojson";
	public final String COMPARENDO_NO_ENCONTRADO = "No se encontro un comparendo con los requerimientos solicitados";

	/**
	 * Atributos del modelo del mundo
	 */
	private IArregloDinamico datos;	
	/***
	 * La lista de los comparendos 
	 */
	private Lista<Comparendo> comparendos;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		//datos = new ArregloDinamico(7);
		comparendos = new Lista<>();
		cargar();
	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datos = new ArregloDinamico(capacidad);
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return comparendos.darTamaño();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(String dato)
	{	
		datos.agregar(dato);
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public String buscar(String dato)
	{
		return datos.buscar(dato);
	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public String eliminar(String dato)
	{
		return datos.eliminar(dato);
	}

	/**
	 * Carga la informacion en el archivo indicado.
	 */
	public void cargar() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(RUTA));
			JsonElement element = JsonParser.parseReader(bf);
			if (element.isJsonObject()) {
				JsonObject admin = element.getAsJsonObject();
				JsonArray listaComparendos = admin.getAsJsonArray("features");
				Gson gson = new Gson();
				for (int i = 0; i < listaComparendos.size(); i++) {
					JsonObject comparendo = listaComparendos.get(i).getAsJsonObject();
					agregarJsonObject(comparendo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Recibe un JsonObject que representa un comparendo y lo guarda en la lista
	 * tras convertirlo en un objeto en java
	 * @param jComparendo el JsonObject que representa un comparendo
	 */
	public void agregarJsonObject(JsonObject jComparendo) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		Comparendo comparendo = gson.fromJson(jComparendo, Comparendo.class);
		agregarLista(comparendo);
	}
	/**
	 * agrega un comparendo recibido por parametro al final de la lista
	 * @param comparendo el comparendo a agregar
	 */
	public void agregarLista(Comparendo comparendo) {

		comparendos.agregarAlFinal(comparendo);
	}
	/**
	 * Imprime la informacion del comparendo en posicion recibida por parametro
	 * @param i la posicon a imprimir
	 */
	public Comparendo get(int i) {

		return comparendos.darElementoPosicion(i);
	}



	/**
	 * retorna la zona minimax de los comparendos
	 * @return la zona minimax de los comparendos en el siguiente orden: minLat, minLong, maxLat, MaxLong
	 */
	public double[] darZonaMinimax()
	{
		double minLatitud = comparendos.darElementoPosicion(0).darUbicacion().darLatitud();
		double maxLatitud = comparendos.darElementoPosicion(0).darUbicacion().darLatitud();
		double minLongitud = comparendos.darElementoPosicion(0).darUbicacion().darLongitud();
		double maxLongitud = comparendos.darElementoPosicion(0).darUbicacion().darLongitud();
		for(int i= 1; i < comparendos.darTamaño(); i++)
		{
			Comparendo actual = comparendos.darElementoPosicion(i);
			if(actual.darUbicacion().darLatitud() < minLatitud)
				minLatitud = actual.darUbicacion().darLatitud();
			else if (actual.darUbicacion().darLatitud() > maxLatitud)
				maxLatitud = actual.darUbicacion().darLatitud();

			if(actual.darUbicacion().darLongitud() < minLongitud)
				minLongitud = actual.darUbicacion().darLongitud();
			else if(actual.darUbicacion().darLongitud() > maxLongitud)
				maxLongitud = actual.darUbicacion().darLongitud();
		}
		double[] respuesta = {minLatitud, minLongitud, maxLatitud, maxLongitud};
		return respuesta;
	}
	/**
	 * Busca el primero comparendo que aparezca en la lista con la localidad buscada
	 * si no lo encuentra lanza una excepcion
	 * @param Localidad, la localidad buscada
	 * @return el primer comparendo encontrado con la localidad
	 * @throws Exception si no encuentra ninguno, avisa al usuario
	 */
	public Comparendo buscarPrimeroenLocalidad(String localidad) throws Exception
	{
		//Es mas eficaz buscarlo en la lista desordenada que ordenarla y luego buscarlo
		Comparendo buscado = null;
		for(int i = 0; i < comparendos.darTamaño() && buscado == null; i++)
		{
			if(comparendos.darElementoPosicion(i).darDetalles().darLocalidad().equals(localidad))
				buscado = comparendos.darElementoPosicion(i);
		}
		if(buscado == null)
			throw new Exception(COMPARENDO_NO_ENCONTRADO);
		return buscado;
	}
	/**
	 * Busca el primer comparendo dada su infraccion. Si no lo encuentra, lanza una excepci
	 * @param infraccion Infraccion que se desea buscar 
	 * @return Primer comparendo encontrado con la infraccion dada
	 * @throws Exception Si no encuentra el comparendo
	 */
	public Comparendo buscarPrimeroInfraccion (String infraccion) throws Exception {
		Comparendo buscado= null;
		for (int i =0; i <comparendos.darTamaño()&& buscado==null;i++) {
			if (comparendos.darElementoPosicion(i).darDetalles().darInfraccion().equals(infraccion)) {
				buscado=comparendos.darElementoPosicion(i);
			}
		}
		if (buscado==null) {
			throw new Exception(COMPARENDO_NO_ENCONTRADO);
		}


		return buscado;
	}
	public Comparendo[] copiarComparendos()
	{
		Comparendo[] comp = new Comparendo[comparendos.darTamaño()];
		comparendos.reiniciarActual();
		for (int i = 0; i < comparendos.darTamaño(); i++) {
			comp[i] = comparendos.darElementoActual();
			comparendos.avanzarActual();
		}
		return comp;
	}
	/**
	 * retorna una lista enlazada con los comparendos en una fecha dada
	 * @param Fecha la fecha solicitada
	 * @return lista con comparendos en la fecha buscada
	 */
	public  Lista<Comparendo> darComparendosenFecha(String fecha) throws Exception
	{ 
		try
		{
		Date fecha1 = new SimpleDateFormat("yyyy/MM/dd").parse(fecha);
		}
		catch(Exception e)
		{
			throw new Exception("La fecha no está en el formato esperado");
		}
		Comparendo[] ordenados = copiarComparendos();
		Sorting.mergeSort(ordenados);
		Lista<Comparendo> respuesta = new Lista<Comparendo>();
		boolean seguir = true;
		Comparendo ficticio = new Comparendo("", "", 0, 0, 0, 0, fecha, "", "", "", "", "", "");
		for(int i = 0; i < ordenados.length && seguir; i++)
		{
			int iguales = ficticio.compareFecha(ordenados[i]);
			if(iguales == 0)
				respuesta.agregarAlFinal(ordenados[i]);
			else if(iguales < 0)
				seguir = false;
		}
		ficticio = null;
		return respuesta;
	}
	public Lista<String[]> crearTablaComparativa(String fecha1, String fecha2) throws Exception
	{
		Lista<Comparendo> lista = darListadosFechas(fecha1, fecha2);
		Comparendo[] comparendos = new Comparendo[lista.darTamaño()];
		lista.reiniciarActual();
		for (int i = 0; i < lista.darTamaño(); i++) {
			comparendos[i] = lista.darElementoActual();
			lista.avanzarActual();
		}
		lista = null;
		Sorting.mergeSortCodigo(comparendos);
		Lista<String[]> respuesta = new Lista<>();
		int i = 0;
		while(i < comparendos.length)
		{
			String actual = comparendos[i].darDetalles().darInfraccion();
			int enfecha1 = 0;
			int enfecha2 = 0;
			while( i < comparendos.length && actual.equals(comparendos[i].darDetalles().darInfraccion()))
			{
				if(comparendos[i].darDetalles().darfecha().equals(fecha1))
					enfecha1++;
				else
					enfecha2++;
				i++;
			}
			String[] elemento = {actual, ""  + enfecha1, "" + enfecha2};
			respuesta.agregarAlFinal(elemento);
		}
		return respuesta;
		
	}

	public Lista<Comparendo> darListadosFechas(String fecha1, String fecha2) throws Exception
	{
		String fechaMayor = darFechaMayor(fecha1, fecha2);
		String fechaMenor = fechaMayor.equals(fecha1)? fecha2: fecha1;
		Comparendo[] ordenados = copiarComparendos();
		Sorting.mergeSort(ordenados);
		Lista<Comparendo> respuesta = new Lista<Comparendo>();
		boolean seguir = true;
		Comparendo ficticio1 = new Comparendo("", "", 0, 0, 0, 0, fechaMenor, "", "", "", "", "", "");
		Comparendo ficticio2 = new Comparendo("", "", 0, 0, 0, 0, fechaMayor, "", "", "", "", "", "");
		for(int i = 0; i < ordenados.length && seguir; i++)
		{
			if(ficticio1.compareFecha(ordenados[i]) == 0 || ficticio2.compareFecha(ordenados[i]) == 0)
				respuesta.agregarAlFinal(ordenados[i]);
			else if(ficticio2.compareFecha(ordenados[i]) < 0)
				seguir = false;
		}
		ficticio1 = null;
		ficticio2 = null;
		return respuesta;
	}
	public String darFechaMayor(String fecha1, String fecha2) throws Exception
	{
		String mayor = fecha1;
		String menor = fecha2;
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
			if(sf.parse(fecha1).compareTo(sf.parse(fecha2)) < 0)
			{
				mayor = fecha2;
				menor = fecha1;
			}
		} catch (ParseException e) {
			throw new Exception("La fecha no está en el formato esperado");
		}
		return mayor;	
	}
	public Lista<Comparendo> consultarPorInfraccion(String infraccion)
	{
		Comparendo[] copia= copiarComparendos();
		Lista<Comparendo> infracciones= new Lista<Comparendo>();
		for (int i =0; i<comparendos.darTamaño();i++) {
			if(comparendos.darElementoPosicion(i).darDetalles().darInfraccion().equals(infraccion))
				infracciones.agregarElemento(comparendos.darElementoPosicion(i));

		}
		//Recordatorio... finalizar para ordenar 
		return infracciones;
	}
}