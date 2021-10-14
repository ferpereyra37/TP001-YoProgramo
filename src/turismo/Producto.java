package turismo;

public abstract class Producto {
	private String nombre;
	protected int costo;
	private double tiempo;


	
	public Producto(String nombre, int costo, double tiempo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public double getTiempo() {
		return this.tiempo;
	}

	public int getCosto() {
		return this.costo;
	}
	
	
	

//	public void ordenarProductos() {
		
	}

	

