package turismo;

import java.util.ArrayList;
import java.util.List;

public class AxB extends Promocion {
	private List<Atraccion> atraccionesGratis = new ArrayList<Atraccion>();

	public AxB(String nombre, List<Atraccion> atraccionesList, List<Atraccion> atraccionesGratis) {
		super(nombre, atraccionesList);
		this.atraccionesGratis = atraccionesGratis;
	}
	
	

}
