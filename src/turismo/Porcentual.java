package turismo;

import java.util.List;

public class Porcentual extends Promocion {
	private int porcentaje;

	public Porcentual(String nombre, List<Atraccion> atraccionesList, int porcentaje) {
		super(nombre, atraccionesList);
		this.porcentaje = porcentaje;
	}

/*	public int getCosto() {
		int costo = 0;
		for(int i = 0; i < atraccionesList.size(); i++)
			costo += atraccionesList.getCosto[i];
		return costo -= costo * porcentaje / 100;
*/
	}
	
