package turismo;

import java.util.Objects;

public abstract class Producto {
	protected String nombre;
	protected int costo;
	protected double tiempo;
	
	public Producto(String nombre, int costo, double tiempo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
	}

	public Producto(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", costo=" + costo + ", tiempo=" + tiempo + "]";
	}



	public String getNombre() {
		return nombre;
	}

	public Integer getCosto() {
		return this.costo;
	}
	
	public Double getTiempo() {
		return this.tiempo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costo, nombre, tiempo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return costo == other.costo && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo);
	}
	
	
	
}
	

