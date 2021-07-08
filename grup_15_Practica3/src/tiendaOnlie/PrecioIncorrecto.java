package tiendaOnlie;

public class PrecioIncorrecto extends Exception {
	private static final long serialVersionUID = 1L;

	public PrecioIncorrecto(int precio) {
		super("El precio: " + precio + " es cero o inferior a cero");
	}
}
