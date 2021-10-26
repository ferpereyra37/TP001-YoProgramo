package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import parque.Atraccion;
import parque.Ofertable;

public class AtraccionTest {
	Atraccion a1 = new Atraccion("Moria", 10, 2, 1);
	List <Atraccion> listaAtraccion = new ArrayList<Atraccion>();
	@Test
	public void creacionAtraccion() {
		assertNotNull(a1);
	}
	
	@Test(expected = Error.class)
	public void esPromocionLaAtraccion() {
		assertTrue(a1.esPromocion());
	}
	
	@Test
	public void hayCupoDeAtraccion() {
		assertTrue(a1.hayCupo());
	}
	
	@Test
	public void agregarAtraccionesALista() {
		a1.agregarAtraccionesALista((Ofertable)a1,  listaAtraccion);
		assertNotNull(listaAtraccion);
	}

}
