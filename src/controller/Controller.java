package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.data_structures.Lista;
import model.logic.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo */
	private Modelo modelo;

	/* Instancia de la Vista */
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * 
	 * @param capacidad
	 *            tamaNo inicial del arreglo
	 */
	public Controller() {
		view = new View();
		modelo = new Modelo();
		view.imprimirResultadosCarga(modelo.darTamano(), modelo.get(0), modelo.get(modelo.darTamano() - 1),
				modelo.darZonaMinimax());
		iniciarPrograma();
	}

	/**
	 * Este metodo llama a la vista para que despliegue el menu de opciones al
	 * usuario y ejecuta la opcion escogida.
	 */
	public void iniciarPrograma() {

		while (true) {
			int opcion = view.darOpciones();
			if (opcion == 1) {
				String localidad = view.pedir("localidad");
				if (localidad == null)
					iniciarPrograma();
				else {
					try {
						Comparendo buscado = modelo.buscarPrimeroenLocalidad(localidad);
						view.imprimir(buscado);
					} catch (Exception e) {
						view.imprimir(e.getMessage());
					}
				}
			} else if (opcion == 2) {
				String fecha = view.pedir("fecha (yyyy/MM/dd) ");
				if (fecha == null) {
					iniciarPrograma();
				} else {
					try
					{
						Lista<Comparendo> buscados = modelo.darComparendosenFecha(fecha);
						for (Comparendo comparendo : buscados) {
							view.imprimir(comparendo);
						}
						view.imprimir("Total comparendos " + buscados.darTamaño());
					}
					catch(Exception e)
					{
						view.imprimir(e.getMessage());
					}
				}
			}

			else if (opcion == 3) {

				String fecha1 = view.pedir("primera fecha (yyyy/MM/dd) ");
				if (fecha1 == null)
					iniciarPrograma();
				else {
					String fecha2 = view.pedir("segunda fecha (yyyy/MM/dd) ");
					if (fecha2 == null)
						iniciarPrograma();
					else {
						try
						{
							Lista<String[]> lista = modelo.crearTablaComparativa(fecha1, fecha2);
							view.imprimir("Comparación de comparendos por Infracción en dos fechas:");
							view.imprimirFormatotablaTresColumnas("Infraccion", fecha1, fecha2);
							for (String[] e : lista) {
								view.imprimirFormatotablaTresColumnas(e[0], e[1], e[2]);
							}
						}
						catch(Exception e)
						{
							view.imprimir(e.getMessage());
						}
					}
				}

			} else if (opcion == 4) {
				String infraccion = view.pedir("la infraccion");
				if (infraccion != null) {
					try {
						view.imprimir(modelo.buscarPrimeroInfraccion(infraccion));
					} catch (Exception e) {
						view.imprimir(e.getMessage());
					}
				} else
					iniciarPrograma();

			}
			else if(opcion == 5)
			{
				String datoUsuario=view.pedir("la infraccion");
				Lista<Comparendo> rta= modelo.consultarPorInfraccion(datoUsuario);
				for(Comparendo comparendo : rta) {
					view.imprimir(comparendo);
				}
				view.imprimir("El total de comparendos es: "+rta.darTamaño());

			}
			else if(opcion == 6){
				view.impresionTabla(modelo.darInfraccionYTipoServi());	
			}
			else if(opcion == 7)
			{
				String fecha1 = view.pedir("primera fecha (yyyy/MM/dd)");
				if (fecha1 == null)
					iniciarPrograma();
				else {
					String fecha2 = view.pedir("segunda fecha (yyyy/MM/dd)");
					if (fecha2 == null)
						iniciarPrograma();
					else {
						String localidad = view.pedir("localidad ");
						if(localidad == null)
							iniciarPrograma();
						else
						{
							try
							{
								Lista<String[]> lista = modelo.crearTablaLocalidadFechas(fecha1, fecha2, localidad);
								view.imprimir("Comparación de comparendos en " + localidad +" del " + fecha1 + " al " + fecha2);
								view.imprimirFormatoTablaDosColumnas("Infraccion", "#Comparendos");
								for (String[] e : lista) {
									view.imprimirFormatoTablaDosColumnas(e[0], e[1]);
								}
							}
							catch(Exception e)
							{
								view.imprimir(e.getMessage());
							}
						}
					}
				}
			}
			else if(opcion == 8)
			{
				String fecha1 = view.pedir("primera fecha (yyyy/MM/dd) ");
				if (fecha1 == null)
					iniciarPrograma();
				else {
					String fecha2 = view.pedir("segunda fecha (yyyy/MM/dd) ");
					if (fecha2 == null)
						iniciarPrograma();
					else {
						try
						{
							Lista<String[]> lista = modelo.darRankingMasComparendos(fecha1, fecha2);
							view.imprimir("Ranking de las 3 mayores infracciones del " + fecha1 + " al " + fecha2);
							view.imprimirFormatoTablaDosColumnas("Infraccion", "#Comparendos");
							for (String[] e : lista) {
								view.imprimirFormatoTablaDosColumnas(e[0], e[1]);
							}
						}
						catch(Exception e)
						{
							view.imprimir(e.getMessage());
						}
					}
				}

			}
			else if (opcion ==9) {
			view.impresionHistograma(modelo.darCantidadporLocalidad());			
			}
		}
	}

	public void run() {
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while (!fin) {
			view.printMenu();

			int option = lector.nextInt();
			switch (option) {
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
				if (respuesta != null) {
					view.printMessage("Dato encontrado: " + respuesta);
				} else {
					view.printMessage("Dato NO encontrado");
				}
				view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");
				break;

			case 4:
				view.printMessage("--------- \nDar cadena (simple) a eliminar: ");
				dato = lector.next();
				respuesta = modelo.eliminar(dato);
				if (respuesta != null) {
					view.printMessage("Dato eliminado " + respuesta);
				} else {
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
