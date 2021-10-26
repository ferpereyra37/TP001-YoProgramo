package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import parque.Atraccion;
import parque.OrdenadorPorCosto;
import parque.OrdenadorPorTiempo;

public class AtraccionesTest {

	@Test
	public void crearAtracciones() {
		Atraccion Moria = new Atraccion("Moria", 10, 2, 6);
		Atraccion Mordor = new Atraccion("Mordor", 25, 3, 4);
		Atraccion Erebor = new Atraccion("Erebor", 12, 3, 32);
		Atraccion LaComarca = new Atraccion("LaComarca", 3, 6.5, 150);
		List<Atraccion> listado = new ArrayList<Atraccion>();
		listado.add(Moria);
		listado.add(Mordor);
		listado.add(Erebor);
		listado.add(LaComarca);
		System.out.println(listado);
	}
	
	@Test
	public void ordenarAtracciones() {
		Atraccion Moria = new Atraccion("Moria", 10, 2, 6);
		Atraccion Mordor = new Atraccion("Mordor", 25, 3, 4);
		Atraccion Erebor = new Atraccion("Erebor", 12, 3, 32);
		Atraccion LaComarca = new Atraccion("LaComarca", 3, 6.5, 150);
		List<Atraccion> listado = new ArrayList<Atraccion>();
		listado.add(Moria);
		listado.add(Mordor);
		listado.add(Erebor);
		listado.add(LaComarca);
		listado.sort(new OrdenadorPorCosto());
		System.out.println(listado);
		listado.sort(new OrdenadorPorTiempo());
		System.out.println(listado);
	}

}
