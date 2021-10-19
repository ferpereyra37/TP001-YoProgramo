package turismo;

public class Atraccion extends Producto {
	private int cupo;

	public Atraccion(String nombre, int costo, double tiempo, int cupo) {
		super(nombre, costo, tiempo);
		this.cupo = cupo;
	}

	@Override
	public String toString() {
		return super.toString() + " Atraccion [cupo=" + cupo + "]";
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getCosto() {
		return this.costo;
	}

	public Double getTiempo() {
		return this.tiempo;
	}
	
	public int getCupo() {
		return this.cupo;
	}
	
	public boolean estaDisponible() {
		return this.cupo > 0;
	}
	
	public void restarCupo() {
		this.cupo -= 1;
	}

	
	

	
	


}
