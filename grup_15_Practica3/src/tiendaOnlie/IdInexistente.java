package tiendaOnlie;

public class IdInexistente extends Exception {
	private static final long serialVersionUID = 1L;

	public IdInexistente(String id) {
		super("El id " + id + " no esta en la lista");
	}

}
