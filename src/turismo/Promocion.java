package turismo;

import java.util.List;


public abstract class Promocion extends Producto {
	
	private List<Atraccion> atraccionesList;
	
	public Promocion(String nombre, List<Atraccion> atraccionesList) {
		super(nombre);
		this.atraccionesList = atraccionesList;
		
	}

	@Override
	public String toString() {
		return "Promocion [nombre=" + nombre + ", atraccionesList=" + atraccionesList + "]";
	}

	
/*	
	public boolean estaDisponible() {
		return for(Atraccion cupo: this.atraccionesList) {
		;
	}

	public int getCosto() {
		return 0;
	}

	public double getTiempo() {

	}*/


}
