package turismo;

import java.util.Comparator;

public class OrdenadorPorTiempo implements Comparator<Producto>{

	@Override
	public int compare(Producto o1, Producto o2) {
		return -o1.getTiempo().compareTo(o2.getTiempo());
	}

}
