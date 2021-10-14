package turismo;

import java.util.List;

public class ConDescuento extends Promocion {
	private int descuento;

	public ConDescuento(String nombre, int descuento, List<Atraccion> atraccionesList) {
		super(nombre, atraccionesList);
		this.descuento = descuento;
		this.costo = 4;
	}
}
