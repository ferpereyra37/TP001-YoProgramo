package turismo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Sistema {
	private List<Atraccion> atraccionesList = new ArrayList<Atraccion>();
	private List<Usuario> usuariosList = new ArrayList<Usuario>();
	private List<Promocion> promosList = new ArrayList<Promocion>();
	protected List<Producto> sugerencias = new ArrayList<Producto>();

	public void crearAtracciones() {
		try {
			File file = new File("archivo atracciones");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String[] temp = sc.nextLine().strip().split(" ");
				String nombre = temp[0];
				int costo = Integer.parseInt(temp[1]);
				double tiempo = Double.parseDouble(temp[2]);
				int cupo = Integer.parseInt(temp[3]);
				Atraccion a1 = new Atraccion(nombre, costo, tiempo, cupo);
				System.out.println(a1);
				atraccionesList.add(a1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crearPromociones() {
		try {
			File file = new File("Archivo promociones");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String[] temp = sc.nextLine().strip().split(",");
				List<Atraccion> listaAtraccionesTemp = new ArrayList<Atraccion>();
				if (temp[0].equalsIgnoreCase("ConDescuento")) {
					for (int i = 3; i < temp.length; i++) {
						String n = temp[i];
						atraccionesList.stream()
							.filter(e -> (e.getNombre().equalsIgnoreCase(n)))
							.forEach(e -> listaAtraccionesTemp.add(e));
					}
					String nombre = temp[1];
					int costo = Integer.parseInt(temp[2]);
					ConDescuento p1 = new ConDescuento(nombre, costo, listaAtraccionesTemp);
					System.out.println(p1);
					promosList.add(p1);
				}
				if (temp[0].equalsIgnoreCase("Porcentual")) {
					for (int i = 3; i < temp.length; i++) {
						String n = temp[i];
						atraccionesList.stream()
							.filter(e -> (e.getNombre().equalsIgnoreCase(n)))
							.forEach(e -> listaAtraccionesTemp.add(e));
					}
					String nombre = temp[1];
					int dcto = Integer.parseInt(temp[2]);
					Porcentual p1 = new Porcentual(nombre, dcto, listaAtraccionesTemp);
					System.out.println(p1);
					promosList.add(p1);
				}
				if (temp[0].equalsIgnoreCase("AxB")) {
					String nombre = temp[1];
					String[] arrTemp1 = Arrays.copyOfRange(temp, 2, temp.length);
					String[] arrTemp2 = sc.nextLine().strip().split("/");
					AxB p1 = new AxB(nombre, arrTemp1, arrTemp2);
					System.out.println(p1);
					promosList.add(p1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void crearUsuarios() {
		try {
			File file = new File("archivo usuarios");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String[] temp = sc.nextLine().strip().split(" ");
				String nombre = temp[0];
				int presupuesto = Integer.parseInt(temp[1]);
				double tiempo = Double.parseDouble(temp[2]);
				Usuario u1 = new Usuario(nombre, presupuesto, tiempo);
				System.out.println(u1);
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
		this.promosList.sort(new OrdenadorPorCosto());
		this.atraccionesList.sort(new OrdenadorPorCosto());
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
			if (sugerencias.get(i).getTiempo() < tiempoMinimo) {
				tiempoMinimo = sugerencias.get(i).getTiempo();
			}
		}
		return tiempoMinimo;
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
		for (Atraccion unaAtraccion : promocion.getAtraccionesList()) {
			if (estaAtraccionEnAtracciones(unaAtraccion, atraccionesAceptadas)) {
				return true;
			}
		}
		return false;
	}



	public void ofertar(Usuario usuario) throws IOException {
		List<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();
		List<Producto> itinerario = new ArrayList<Producto>();
		int i = 0;
		this.ordenarSugerencias();
		this.listaSugerencias();

		while (usuario.getTiempo() < tiempoMinimoAtraccionOPromocion()
				&& usuario.getPresupuesto() < costoMinimoAtraccionOPromocion()) {
			if (sugerencias.get(i).esPromocion()) {
				if (!estaAtraccionEnPromocion((Promocion) sugerencias.get(i), atraccionesAceptadas)
						&& sugerencias.get(i).hayCupo() && sugerencias.get(i).getCosto() < usuario.getPresupuesto()
						&& sugerencias.get(i).getTiempo() < usuario.getTiempo()) {
					// aquí se mostraría la sugerencia en consola y segun la opción S/N se agrega al
					// itinerario, se resta tiempo y plata
				}
			} else {
				if (!estaAtraccionEnAtracciones((Atraccion) sugerencias.get(i), atraccionesAceptadas)
						&& sugerencias.get(i).hayCupo() && sugerencias.get(i).getCosto() < usuario.getPresupuesto()
						&& sugerencias.get(i).getTiempo() < usuario.getTiempo()) {
					// aquí se mostraría la sugerencia en consola y segun la opción S/N se agrega al
					// itinerario, se resta tiempo y plata
				}
			}
			usuario.setItinerario(itinerario);
			this.imprimirItinerario(usuario);
		}
	}

		public void imprimirItinerario(Usuario usuario) throws IOException {
			int costoTotal = 0;
			double tiempoTotal = 0.0;
			PrintWriter salida = new PrintWriter(new FileWriter(usuario.getNombre() + " Itinerario.out"));
			
			salida.println("Ud. ha adquirido: ");
			System.out.println("Ud. ha adquirido: ");
			
			for (int i = 0; i < usuario.getItinerarioList().size(); i++) {
				if (usuario.getItinerarioList().get(i) != null) {
					costoTotal += usuario.getItinerarioList().get(i).getCosto();
					tiempoTotal += usuario.getItinerarioList().get(i).getTiempo();
					salida.println((i + 1) + ". " + usuario.getItinerarioList().get(i).getNombre().toUpperCase());
					System.out.println((i + 1) + ". " + usuario.getItinerarioList().get(i).getNombre().toUpperCase());
				}
				
				salida.println("El costo total de su itinerario es: " + costoTotal + " monedas.");
				salida.println("El tiempo total de su itinerario es: " + tiempoTotal + " horas.");
				System.out.println("El costo total de su itinerario es: " + costoTotal + " monedas.");
				System.out.println("El tiempo total de su itinerario es " + tiempoTotal + " horas.");
		}
		}

		public List<Usuario> getUsuariosList() {
			return usuariosList;
		}
		
		
		public void mensajeBienvenida() {
			System.out.println("Bienvenid@ a nuestro Parque!");
			System.out.println(
					"A continuación vas a ver nuestras Atracciones y Promociones. Pulsa S para aceptar, o N para rechazar");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------");
		}

		

	
	}
	
