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
	    	int respuesta = 0;
	    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    	System.out.println("Escoja que opcion quiere realizar:");
	    	System.out.println("1. Buscar el primer comparendo en la localidad específica");
	    	System.out.println("2. Consultar los comparendos registrados en el archivo determinada fecha y hora");
	    	System.out.println("3. Comparativo copmarendos en dos fechas dadas ");
	    	System.out.println("4. Buscar el primer comparendo según la infracción específica");
	    	try
	    	{
	    		respuesta = Integer.parseInt(bf.readLine());
	    	}
	    	catch(Exception e)
	    	{
	    		darOpciones();
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
}
