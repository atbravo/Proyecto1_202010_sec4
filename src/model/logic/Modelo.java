package model.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;

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


	public final String RUTA = "./data/comparendos_dei_2018_small.geojson";
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
	public  Lista<Comparendo> darComparendosenFecha(String fecha)
	{ 
		Comparendo[] ordenados = copiarComparendos();
		Sorting.mergeSort(ordenados);
		Lista<Comparendo> respuesta = new Lista<Comparendo>();
		boolean seguir = true;
		Comparendo ficticio = new Comparendo("", "",0,0,0,0, "",fecha,"", "","","","");
		for(int i = 0; i < ordenados.length && seguir; i++)
		{
			int iguales = ficticio.compareTo(ordenados[i]);
			if(i == 0)
				respuesta.agregarAlFinal(ordenados[i]);
			else if(i < 0)
				seguir = false;
		}
		return respuesta;
	}
}
