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


	@Override
	public boolean hayCupo() {
		return this.cupo > 0;
	}

	@Override
	public void reservarCupo() {
		this.cupo--;
	}

	@Override
	protected boolean esPromocion() {
		return false;
	}
}
	
