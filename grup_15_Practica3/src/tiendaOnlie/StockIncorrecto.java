package tiendaOnlie;

public class StockIncorrecto extends Exception {
	private static final long serialVersionUID = 1L;

	public StockIncorrecto(int stock) {
		super("El stock: " + stock + " es inferior a cero");
	}
}
