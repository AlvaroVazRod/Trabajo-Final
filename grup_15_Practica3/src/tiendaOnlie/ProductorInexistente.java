package tiendaOnlie;

public class ProductorInexistente extends Exception {
	private static final long serialVersionUID = 1L;

	public ProductorInexistente(String idProductor) {
		super("El productor " + idProductor + " no existe");
	}
}
