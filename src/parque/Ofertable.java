package parque;

import java.util.List;

public interface Ofertable {
// 	devuelve el costo
	 
	public Integer getCosto();

// 	devuelve el tiempo

	public Double getTiempo();

//	pregunta si hay cupo disponible

	public boolean hayCupo();


// 	si hay cupo , resta un cupo disponible en las atracciones involucradas.

	public void reservarCupo();


//	devuelve el nombre del ofertable.

	public String getNombre();
	
//	devuelve el nombre de la atracción o promoción.
	
	public boolean esPromocion();
	
//	devuelve un booleano, true si es promoción falso sino.

	public void imprimirOferta();

//	imprime un syso con la oferta sea promocion o atraccion.
	
	public void agregarAtraccionesALista(Ofertable ofertable, List<Atraccion> listaAtracciones);
	
//	agrega atracciones a una lista que se utilizará para revisar los ofertables ya comprados por el usuario.
}
