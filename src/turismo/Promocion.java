package turismo;

import java.util.List;


public abstract class Promocion extends Producto {
	protected List<Atraccion> atraccionesList;


	public List<Atraccion> getAtraccionesList() {
		return atraccionesList;
	}

	public Promocion(String nombre, List<Atraccion> atraccionesList) {
		super(nombre);
		this.atraccionesList = atraccionesList;
		
	}

	@Override
	public String toString() {
		return "Promocion [nombre=" + nombre + ", atraccionesList=" + atraccionesList + "]";
	}

	@Override
	public boolean hayCupo() {
		int i = 0;
		boolean estaDisponible = true;
		while (atraccionesList.size() < i && estaDisponible) {
			if (atraccionesList.get(i).hayCupo() == false) {
				estaDisponible = false;
			}
			i++;
		}
		return estaDisponible;
	}

	@Override
	public Integer getCosto() {
		int costoTotal = 0;
		for (int i = 0; i < atraccionesList.size(); i++) {
			costoTotal += atraccionesList.get(i).getCosto();
		}
		return costoTotal;
	}
	
	public Integer getCostoSinDescuento() {
		int costoTotal = 0;
		for (int i = 0; i < atraccionesList.size(); i++) {
			costoTotal += atraccionesList.get(i).getCosto();
		}
		return costoTotal;
	}

	@Override
	public Double getTiempo() {
		double tiempoHastaAhora = 0;
		for (int i = 0; i < atraccionesList.size(); i++) {
			tiempoHastaAhora += atraccionesList.get(i).getTiempo();
		}
		return tiempoHastaAhora;
	}

	@Override
	public void reservarCupo() {
		for (int i = 0; i < atraccionesList.size(); i++) {
			atraccionesList.get(i).reservarCupo();
		}
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}
	
	public boolean esPromocion() {
		return true;
	}

}
