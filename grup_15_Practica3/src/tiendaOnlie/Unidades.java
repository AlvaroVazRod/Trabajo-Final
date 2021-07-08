package tiendaOnlie;

public class Unidades extends Producto {
	private int peso;

	public Unidades(String id, String nom, int preu, int stock, String iDproductor, String nomProductor, double latitud,
			double longitud, int peso) {
		super(id, nom, preu, stock, iDproductor, nomProductor, latitud, longitud);
		this.peso = peso;
	}

	public int getPeso() {
		return peso;
	}

	public Producto copia() {
		Unidades c = new Unidades(Id, Nom, Preu, Stock, IDproductor, NomProductor, Latitud, Longitud, peso);
		return c;

	}

	public String toString() {
		return (super.toString() + this.peso);
	}

}
