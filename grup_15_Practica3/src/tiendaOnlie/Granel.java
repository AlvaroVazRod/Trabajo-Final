package tiendaOnlie;

public class Granel extends Producto {
	private String Celiacos;

	public Granel(String id, String nom, int preu, int stock, String iDproductor, String nomProductor, double latitud,
			double longitud, String celiacos) {
		super(id, nom, preu, stock, iDproductor, nomProductor, latitud, longitud);
		this.Celiacos = celiacos;
	}

	public String getCeliacos() {
		return Celiacos;
	}

	public Producto copia() {
		Granel c = new Granel(Id, Nom, Preu, Stock, IDproductor, NomProductor, Latitud, Longitud, Celiacos);
		return c;
	}

	public String toString() {
		return (super.toString() + this.Celiacos);
	}

}
