package tiendaOnlie;

public class DistanciaIncorrecta extends Exception {
	private static final long serialVersionUID = 1L;

	public DistanciaIncorrecta(Double distancia) {
		super("La distancia " + distancia + " es inferior a 0");
	}
}
