package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.data_structures.Lista;
import model.logic.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
		view.imprimirResultadosCarga(modelo.darTamano(), modelo.get(0), modelo.get(modelo.darTamano()-1), modelo.darZonaMinimax());
		iniciarPrograma();
	}
	/**
	 * Este metodo llama a la vista para que despliegue el menu de opciones al usuario y
	 * ejecuta la opcion escogida.
	 */
	public void iniciarPrograma()
	{
		
		while(true)
		{
			int opcion = view.darOpciones();
			if(opcion ==1)
			{
				String localidad = view.pedir("localidad");
				if(localidad == null)
					iniciarPrograma();
				else
				{
					try {
						Comparendo buscado = modelo.buscarPrimeroenLocalidad(localidad);
						view.imprimir(buscado);
					} 
					catch (Exception e) 
					{
						view.imprimir(e.getMessage());
					}
				}
			}
			else if(opcion == 2)
			{
				Lista<Comparendo> buscados = modelo.darComparendosenFecha(view.pedir("fecha"));
				for (Comparendo comparendo : buscados) {
					view.imprimir(comparendo);
				}
				view.imprimir("Total copmarendos" + buscados.darTamaño());
			}
		}
	}
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					view.printMessage("--------- \nCrear Arreglo \nDar capacidad inicial del arreglo: ");
				    int capacidad = lector.nextInt();
				    modelo = new Modelo(capacidad); 
				    view.printMessage("Arreglo Dinamico creado");
				    view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 2:
					view.printMessage("--------- \nDar cadena (simple) a ingresar: ");
					dato = lector.next();
					modelo.agregar(dato);
					view.printMessage("Dato agregado");
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 3:
					view.printMessage("--------- \nDar cadena (simple) a buscar: ");
					dato = lector.next();
					respuesta = modelo.buscar(dato);
					if ( respuesta != null)
					{
						view.printMessage("Dato encontrado: "+ respuesta);
					}
					else
					{
						view.printMessage("Dato NO encontrado");
					}
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 4:
					view.printMessage("--------- \nDar cadena (simple) a eliminar: ");
					dato = lector.next();
					respuesta = modelo.eliminar(dato);
					if ( respuesta != null)
					{
						view.printMessage("Dato eliminado "+ respuesta);
					}
					else
					{
						view.printMessage("Dato NO eliminado");							
					}
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 5: 
					view.printMessage("--------- \nContenido del Arreglo: ");
					view.printModelo(modelo);
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;	
					
				case 6: 
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
