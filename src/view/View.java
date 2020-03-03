package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import model.data_structures.Lista;
import model.logic.Comparendo;
import model.logic.Modelo;

/**
 *Clase que permite la interaccion del usuario y el programa
 */
public class View<E> 
{

	private final String CANCELAR = "CANCELAR";
	/**
	 * Metodo constructor
	 */
	public View()
	{

	}
	/**
	 * Despliega menu de opciones al usuario
	 * @return la opcion escogida por el usuario
	 */
	public int darOpciones()
	{
		System.out.println();
		int respuesta = 0;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Escoja que opcion quiere realizar:");
		System.out.println("1. Buscar el primer comparendo en la localidad específica");
		System.out.println("2. Consultar los comparendos registrados en el archivo determinada fecha y hora");
		System.out.println("3. Comparativo copmarendos en dos fechas dadas ");
		System.out.println("4. Buscar el primer comparendo según la infracción específica");
		System.out.println("5.");
		System.out.println("6.");
		System.out.println("7. Dar comparendos por infraccion en localidad, entre dos fechas");
		System.out.println("8. Dar ranking mas comparendos por infraccion");
		try
		{
			respuesta = Integer.parseInt(bf.readLine());
		}
		catch(Exception e)
		{
			respuesta = darOpciones();
		}
		return respuesta;
	}
	/**
	 * Solicita al usuario que ingrese lo que desea buscar de los comparendos, retorna null para cancelar
	 * @param parametro, lo que se solicitara al usuario
	 * @return la localidad solicitada o Null si desea cancelar 
	 */
	public String pedir(String parametro)
	{
		System.out.println("Favor inserte " + parametro + " buscada.");
		System.out.println("Escriba CANCELAR para volver al menu principal");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String respuesta = "";
		try{
			respuesta = bf.readLine();
		}
		catch(Exception e)
		{
			pedir(parametro);
		}
		if (respuesta.equals(CANCELAR))
			return null;
		return respuesta;
	}
	/**
	 * Imprime el objeto generico que recibe
	 * @param cosa, objeto a imprimir, debe poder convertirse a string (los copmarendos si sirven)
	 */
	public void imprimir(Object cosa)
	{
		System.out.println();
		System.out.println(cosa);
		System.out.println();
	}
	/**
	 * Imprime los parametros en forma de una tabla de tres columnas
	 * @param columna1 elemento de la primera tabla
	 * @param columna2 elemento de la segunda tabla
	 * @param columna3 elemento de la segunda tabla
	 */
	public void imprimirFormatotablaTresColumnas(String columna1 ,String columna2, String columna3)
	{
		System.out.println(columna1 + darEspacio(columna1, 10) + "|" + columna2 + darEspacio(columna2, 10) + "|" + columna3);
	}
	/**
	 * Imprime los parametros en forma de una tabla de dos columnas
	 * @param columna1 elemento de la primera tabla
	 * @param columna2 elemento de la segunda tabla
	 */
	public void imprimirFormatoTablaDosColumnas(String columna1, String columna2)
	{
		System.out.println(columna1 + darEspacio(columna1, 10) + "|" + columna2);
	}
	/**
	 * Retorna una cadena de espacios con longitud igual a lngdeseada - impresion.lenght(9
	 * @param impresion cadena a ajustar
	 * @param longitudDeseada longitud deseada de la nueva cadena
	 * @return cadena con espacios en blanco
	 */
	private String darEspacio(String impresion, int lngdeseada)
	{
		String corrimiento = "";
		for(int i = 0; i < lngdeseada - impresion.length(); i++)
		{
			corrimiento = " " + corrimiento;
		}
		return corrimiento;
	}
	public void printMenu()
	{
		System.out.println("1. Crear Arreglo Dinamico de Strings");
		System.out.println("2. Agregar String");
		System.out.println("3. Buscar String");
		System.out.println("4. Eliminar String");
		System.out.println("5. Imprimir el Arreglo");
		System.out.println("6. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}		
	/**
	 * Imprime los resultados tras haber cargado los comparendos a la lista
	 */
	public void imprimirResultadosCarga(int tamaño, Comparendo primero, Comparendo ultimo, double[] minimax)
	{
		System.out.println("Total comparendos: " +  tamaño);
		System.out.println();
		System.out.println("Primer comparendo:");
		System.out.println();
		System.out.println(primero);
		System.out.println();
		System.out.println("Ultimo Comparendo:");
		System.out.println();
		System.out.println(ultimo);
		System.out.println();
		System.out.println("Zona minimax:" );
		System.out.println("(" + minimax[0] + "," + minimax[1] + ") (" + minimax[2] + "," + minimax[3] + ")");


	}
	public void printModelo(Modelo modelo)
	{
		// TODO implementar
	}
	public void impresionHistograma(Lista<String> arreglo) {
		System.out.println("Aproximación del número de comparendos por localidad.");
		for(String actual: arreglo) {
			String[] partes = actual.split("-");
			int num= 16-partes[0].length();
			String numeroRayas="";
			for (int i=0; i<num;i++ ) {
				numeroRayas+="-";
			}
			
			String numeroAsteriscos="";
			for (int i=0; i<Integer.parseInt(partes[1]);i++ ) {
				numeroAsteriscos+="*";
			}
			System.out.println(partes[0]+numeroRayas+"|"+numeroAsteriscos);		
			}
	}
	public void impresionTabla(Lista<String> arreglo) {
		System.out.println("Infracción  "+ "|Particular  |Público");
		for(String actual: arreglo) {
			String[] partes = actual.split("-");
			
			int num= 12-partes[0].length();
			String numeroEspaciosInfraccion="";
			for (int i=0; i<num;i++ ) {
				numeroEspaciosInfraccion+=" ";
			}
			String numeroEspacioParticular="";
			for (int i=0; i<12-partes[1].length();i++ ) {
				numeroEspacioParticular+=" ";
			}
			System.out.println(partes[0]+numeroEspaciosInfraccion+"|"+partes[1]+numeroEspacioParticular+"|" +partes[2]);		
			}
	}
}
