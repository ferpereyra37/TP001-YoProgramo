package turismo;

import java.util.ArrayList;
import java.util.List;


public class Usuario {
	private String nombre;
	private int presupuesto;
	private double tiempo;
	protected List<Producto> itinerarioList = new ArrayList<Producto>();
	
	public Usuario(String nombre, int presupuesto, double tiempo) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempo = tiempo;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", presupuesto=" + presupuesto + ", tiempo=" + tiempo + "]";
	}

	public String getNombre() {
		return nombre;
	}
	public int getPresupuesto() {
		return presupuesto;
	}

	public double getTiempo() {
		return tiempo;
	}

	public List<Producto> getItinerarioList() {
		return itinerarioList;
	}	
	
	public void restarTiempo(double tiempo) {
		this.tiempo-=tiempo;
	}
	
	public void restarPresupuesto(double dineroGastado) {
		this.presupuesto -= dineroGastado;
	}
	
	public void setItinerario(List<Producto> itinerarioDado) {
		itinerarioList = itinerarioDado;
	}


	
	

}
