package turismo;

import java.util.List;

public class Porcentual extends Promocion {
	private int porcentaje;

	public Porcentual(String nombre, List<Atraccion> atraccionesList, int porcentaje) {
		super(nombre, atraccionesList);
		this.porcentaje = porcentaje;
	}

	}
	
