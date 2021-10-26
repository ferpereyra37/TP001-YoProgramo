package parque;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Parque {
	private List<Atraccion> atraccionesList = new ArrayList<Atraccion>();
	private List<Usuario> usuariosList = new ArrayList<Usuario>();
	private List<Promocion> promosList = new ArrayList<Promocion>();
	private List<Ofertable> sugerencias = new ArrayList<Ofertable>();

	public void crearAtracciones() {
		try {
			File file = new File("C:\\Users\\Fer\\eclipse-workspace\\TP001-TurismoTierraMedia\\src\\turismo\\archivosEntrada\\atracciones.txt");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String[] temp = sc.nextLine().strip().split(",");
				String nombre = temp[0];
				int costo = Integer.parseInt(temp[1]);
				double tiempo = Double.parseDouble(temp[2]);
				int cupo = Integer.parseInt(temp[3]);
				Atraccion a1 = new Atraccion(nombre, costo, tiempo, cupo);
				atraccionesList.add(a1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crearPromociones() {
		try {
			File file = new File("C:\\Users\\Fer\\eclipse-workspace\\TP001-TurismoTierraMedia\\src\\turismo\\archivosEntrada\\promociones.txt");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String[] temp = sc.nextLine().strip().split(",");
				List<Atraccion> listaAtraccionesTemp = new ArrayList<Atraccion>();
				if (temp[0].equalsIgnoreCase("Absoluto")) {
					String[] listaAtracciones = new String[temp.length - 3];
					for (int i = 3, k = 0; i < temp.length; i++, k++) {
						listaAtracciones[k] = temp[i];
					}
					for (int i = 0; i < listaAtracciones.length; i++) {
						for (int k = 0; k < atraccionesList.size(); k++) {
							if (listaAtracciones[i].equals(atraccionesList.get(k).getNombre())) {
								listaAtraccionesTemp.add(atraccionesList.get(k));
							}
						}
					}
					String nombre = temp[1];
					int costo = Integer.parseInt(temp[2]);
					Absoluto p1 = new Absoluto(nombre, listaAtraccionesTemp, costo);
					promosList.add(p1);
				}
				if (temp[0].equalsIgnoreCase("Porcentual")) {
					for (int i = 3; i < temp.length; i++) {
						String n = temp[i];
						atraccionesList.stream().filter(e -> (e.getNombre().equalsIgnoreCase(n)))
								.forEach(e -> listaAtraccionesTemp.add(e));
					}
					String nombre = temp[1];
					int dcto = Integer.parseInt(temp[2]);
					Descuento p1 = new Descuento(nombre, dcto, listaAtraccionesTemp);
					promosList.add(p1);
				}
				if (temp[0].equalsIgnoreCase("AxB")) {
					String nombre = temp[1];
					List<Atraccion> atraccionesGratisTemp = new ArrayList<Atraccion>();
					for (int i = 2; i < 4; i++) {
						String n = temp[i];
						atraccionesList.stream().filter(e -> (e.getNombre().equalsIgnoreCase(n)))
								.forEach(e -> listaAtraccionesTemp.add(e));
					}
					atraccionesList.stream().filter(e -> (e.getNombre().equalsIgnoreCase(temp[4])))
							.forEach(e -> atraccionesGratisTemp.add(e));
					AxB p1 = new AxB(nombre, listaAtraccionesTemp, atraccionesGratisTemp);
					promosList.add(p1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crearUsuarios() {
		try {
			File file = new File("C:\\Users\\Fer\\eclipse-workspace\\TP001-TurismoTierraMedia\\src\\turismo\\archivosEntrada\\usuarios.txt");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String[] temp = sc.nextLine().strip().split(",");
				String nombre = temp[0];
				int presupuesto = Integer.parseInt(temp[1]);
				double tiempo = Double.parseDouble(temp[2]);
				Usuario u1 = new Usuario(nombre, presupuesto, tiempo);
				usuariosList.add(u1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listaSugerencias() {
		this.sugerencias.addAll(promosList);
		this.sugerencias.addAll(atraccionesList);
	}

	public void ordenarSugerencias() {
		Collections.sort(promosList, new Ordenador());
		Collections.sort(atraccionesList, new Ordenador());
	}

	public int costoMinimoAtraccionOPromocion() {
		int costoMinimo = sugerencias.get(0).getCosto();
		for (int i = 0; i < sugerencias.size(); i++) {
			if (sugerencias.get(i).getCosto() < costoMinimo) {
				costoMinimo = sugerencias.get(i).getCosto();
			}
		}
		return costoMinimo;
	}

	public double tiempoMinimoAtraccionOPromocion() {
		Double tiempoMinimo = sugerencias.get(0).getTiempo();
		for (int i = 0; i < sugerencias.size(); i++) {
			if (sugerencias.get(i).getCosto() < tiempoMinimo) {
				tiempoMinimo = sugerencias.get(i).getTiempo();
			}
		}
		return tiempoMinimo;
	}

	public void ofertarMientrasHayaOroYtiempo(Usuario usuario) throws IOException {

		List<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();
		List<Ofertable> itinerario = new ArrayList<Ofertable>();
		int i = 0;
		this.ordenarSugerencias();
		this.listaSugerencias();

		while (usuario.getTiempo() > tiempoMinimoAtraccionOPromocion()
				&& usuario.getPresupuesto() > costoMinimoAtraccionOPromocion()) {
			if (i == sugerencias.size()) {
				i = 0;
			}
			if (sugerencias.get(i).esPromocion()) {
				if (!estaAtraccionEnPromocion((Promocion) sugerencias.get(i), atraccionesAceptadas)
						&& sugerencias.get(i).hayCupo() && sugerencias.get(i).getCosto() < usuario.getPresupuesto()
						&& sugerencias.get(i).getTiempo() < usuario.getTiempo()) {
					if (usuario.aceptaOferta(sugerencias.get(i))) {
						ofertaAceptada(usuario, itinerario, i, atraccionesAceptadas);
					}
				}
			} else if (!sugerencias.get(i).esPromocion()) {
				if (!estaAtraccionEnAtracciones((Atraccion) sugerencias.get(i), atraccionesAceptadas)
						&& sugerencias.get(i).hayCupo() && sugerencias.get(i).getCosto() < usuario.getPresupuesto()
						&& sugerencias.get(i).getTiempo() < usuario.getTiempo()) {
					if (usuario.aceptaOferta(sugerencias.get(i))) {
						ofertaAceptada(usuario, itinerario, i, atraccionesAceptadas);
					}
				}
			}
			if (i < sugerencias.size()) {
				i++;
			}
			usuario.setItinerario(itinerario);
		}
	}

	public void ofertaAceptada(Usuario usuario, List<Ofertable> itinerario, int i,
			List<Atraccion> atraccionesAceptadas) {
		usuario.restarPresupuesto(sugerencias.get(i).getCosto());
		usuario.restarTiempo(sugerencias.get(i).getTiempo());
		sugerencias.get(i).reservarCupo();
		itinerario.add(sugerencias.get(i));
		sugerencias.get(i).agregarAtraccionesALista(sugerencias.get(i), atraccionesAceptadas);
	}

	public boolean estaAtraccionEnAtracciones(Atraccion atraccion, List<Atraccion> atraccionesAceptadas) {

		for (Atraccion unaAtraccion : atraccionesAceptadas) {

			if (unaAtraccion.equals(atraccion)) {
				return true;
			}
		}
		return false;
	}

	public boolean estaAtraccionEnPromocion(Promocion promocion, List<Atraccion> atraccionesAceptadas) {

		for (Atraccion unaAtraccion : promocion.getAtraccionesList()) { // atracciones incluídas.

			if (estaAtraccionEnAtracciones(unaAtraccion, atraccionesAceptadas)) {
				return true;
			}
		}
		return false;
	}

	public void imprimirItinerario(Usuario usuario) throws IOException {
		int costoTotal = 0;
		double tiempoTotal = 0.0;
		PrintWriter salida = new PrintWriter(new FileWriter(usuario.getNombre() + " Itinerario.out"));

		salida.println("Usted ha comprado: ");
		System.out.println("Usted ha comprado: ");

		for (int i = 0; i < usuario.getItinerario().size(); i++) {
			costoTotal += usuario.getItinerario().get(i).getCosto();
			tiempoTotal += usuario.getItinerario().get(i).getTiempo();
			salida.println((i + 1) + ". " + usuario.getItinerario().get(i).getNombre().toUpperCase());
			System.out.println((i + 1) + ". " + usuario.getItinerario().get(i).getNombre().toUpperCase());
			salida.println("El costo total de su itinerario es: " + costoTotal + " monedas.");
			salida.println("El tiempo total de su itinerario es: " + tiempoTotal + " horas.");
			System.out.println("El costo total de su itinerario es: " + costoTotal + " monedas.");
			System.out.println("El tiempo total de su itinerario es " + tiempoTotal + " horas.");
		}
		salida.close();
	}

	public List<Usuario> getUsuariosList() {
		return usuariosList;
	}

	public void ofertarMientrasHayaOroYtiempoAtodosLosUsuarios() throws IOException {
		for (int i = 0; i < usuariosList.size(); i++) {
			mensajeBienvenida();
			System.out.println("Bienvenido/a: " + usuariosList.get(i).getNombre());
			ofertarMientrasHayaOroYtiempo(usuariosList.get(i));
			imprimirItinerario(usuariosList.get(i));
			System.out.println("-----------------------------------------------------------------");
		}
	}

	public void mensajeBienvenida() {
		System.out.println("Bienvenido/a Turismo en la Tierra Media!");
		System.out.println(
				"A continuación encontrarás nuestras Atracciones y Promociones. Pulsa S para aceptar, o N para rechazar");
		System.out.println("-----------------------------------------------------------------");
	}

	public static void main(String[] args) throws IOException {

		Parque sistema = new Parque();

		sistema.crearUsuarios();
		sistema.crearAtracciones();
		sistema.crearPromociones();

		sistema.ordenarSugerencias();
		sistema.listaSugerencias();

		sistema.ofertarMientrasHayaOroYtiempoAtodosLosUsuarios();

	}
}

	
