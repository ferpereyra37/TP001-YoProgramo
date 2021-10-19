package turismo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
	private List<Atraccion> atraccionesList = new ArrayList<Atraccion>();
	private List<Usuario> usuariosList = new ArrayList<Usuario>();
	private List<Promocion> promosList = new ArrayList<Promocion>();

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
			File file = new File("archivo promociones");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String[] temp = sc.nextLine().strip().split(" ");
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
	
	public void ordenarPorCosto() {
		this.promosList.sort(new OrdenadorPorCosto());
		this.atraccionesList.sort(new OrdenadorPorCosto());
	}
	
	public void ordenarPorTiempo() {
		this.promosList.sort(new OrdenadorPorTiempo());
		this.atraccionesList.sort(new OrdenadorPorTiempo());
	}
	
	/*Filtrar*/
	/* Ofertar*/ 
}