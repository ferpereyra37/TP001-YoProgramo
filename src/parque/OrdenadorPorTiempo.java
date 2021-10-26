package parque;

import java.util.Comparator;

public class OrdenadorPorTiempo implements Comparator<Ofertable> {

	@Override
	public int compare(Ofertable o1, Ofertable o2) {
		// TODO Auto-generated method stub
		return -o1.getTiempo().compareTo(o2.getTiempo());
	}

}
