package turismo;

import java.util.Comparator;

public class OrdenadorPorCosto implements Comparator<Producto>{

	@Override
	public int compare(Producto o1, Producto o2) {
		if(o1.getCosto().compareTo(o2.getCosto())==0) {
			return o1.getTiempo().compareTo(o2.getTiempo());
		}
		else {
			return -o1.getCosto().compareTo(o2.getCosto());
		}
	}
	
}