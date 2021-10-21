package turismo;


public class App {

	public static void main(String[] args) {
		Sistema s1 = new Sistema();
		s1.crearUsuarios();
		s1.crearAtracciones();
		s1.crearPromociones();
		s1.listaSugerencias();
		s1.ordenarSugerencias();
		for (int i = 0; i < s1.getUsuariosList().size(); i++) {
			System.out.println("Hola " + u1.getNombre() + "!!!");
		s1.mensajeBienvenida();
		s1.ofertar(s1.getUsuariosList().get(i));
			
			
		}
		}
}