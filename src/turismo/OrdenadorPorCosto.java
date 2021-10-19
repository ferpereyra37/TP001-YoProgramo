package turismo;

import java.util.Comparator;

public class OrdenadorPorCosto implements Comparator<Producto>{

	@Override
	public int compare(Producto o1, Producto o2) {
		return -o1.getCosto().compareTo(o2.getCosto());
	}
}