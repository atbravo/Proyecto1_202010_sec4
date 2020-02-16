package test.data_structures;

import model.data_structures.Lista;
import model.logic.Comparendo;
import model.logic.Ubicacion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestLista {

	private Lista lista;
	private static int TAMANO=100;
	
	@Before
	public void setUp1() {
		lista= new Lista();
	}

	public void setUp2() 
	{
		for(int i =0; i< TAMANO*2; i++)
		{
			lista.agregarAlFinal(new Ubicacion("" + i + i, + i, +i ,+ i));
		}
	}

	@Test
	public void testAgregarFinal()
	{
		setUp1();
		new Ubicacion("a", 1, 1, 1);
		
	}
	public void testBuscar()
	{
		setUp1();
		setUp2();
		lista.darElementoPosicion(TAMANO-1);
	}
	public void testEliminar()
	{
		setUp1();
		setUp2();
		lista.eliminarElemento(6);
	}

	@Test
	public void testDarElemento()
	{
		setUp1();
		setUp2();
		lista.darElementoActual();
	}

}
