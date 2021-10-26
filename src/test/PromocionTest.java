package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import parque.Atraccion;
import parque.AxB;
import parque.Descuento;
import parque.Promocion;

public class PromocionTest {
	Atraccion a1 = new Atraccion("Moria", 10, 2, 1);
	Atraccion a2 = new Atraccion("BosqueNegro", 3, 4, 12);
	List<Atraccion> atraccionesList = new ArrayList<Atraccion>();
	Promocion p1 = new Promocion("PromoAventura", atraccionesList);
	Promocion axb = new AxB("PromoAventura", atraccionesList, atraccionesList);
	Promocion descuento = new Descuento("PromoAventura", 20, atraccionesList);

	@Test
	public void creacionPromocion() {
		assertNotNull(p1);
	}
	
	@Test
	public void creacionAxB() {
		assertNotNull(axb);
	}
	
	@Test
	public void creacionConDescuento() {
		assertNotNull(descuento);
	}

}
