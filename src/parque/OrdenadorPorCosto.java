package parque;

import java.util.Comparator;

public class OrdenadorPorCosto implements Comparator<Ofertable>{

	@Override
	public int compare(Ofertable o1, Ofertable o2) {
		// TODO Auto-generated method stub
		if(o1.getCosto().compareTo(o2.getCosto())==0) {
			return o1.getTiempo().compareTo(o2.getTiempo());
		}
		else {
			return o1.getCosto().compareTo(o2.getCosto());
		}
	}
	
}
