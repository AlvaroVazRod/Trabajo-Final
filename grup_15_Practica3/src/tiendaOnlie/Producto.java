package tiendaOnlie;


public abstract class Producto {
	protected String Id;
	protected String Nom;
	protected int Preu;
	protected int Stock;
	protected String IDproductor;
	protected String NomProductor;
	protected double Latitud;
	protected double Longitud;
	public Producto(String id, String nom, int preu, int stock, String iDproductor, String nomProductor, double latitud, double longitud) {
		Id = id;
		Nom = nom;
		Preu = preu;
		Stock = stock;
		IDproductor = iDproductor;
		NomProductor = nomProductor;
		Latitud = latitud;
		Longitud = longitud;
}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public int getPreu() {
		return Preu;
	}
	public void setPreu(int preu) {
		Preu = preu;
	}
	public int getStock() {
		return Stock;
	}
	public void setStock(int stock) {
		Stock = stock;
	}
	public String getIDproductor() {
		return IDproductor;
	}
	public void setIDproductor(String iDproductor) {
		IDproductor = iDproductor; 
	}
	public String getNomProductor() {
		return NomProductor;
	}
	public void setNomProductor(String nomProductor) {
		NomProductor = nomProductor;
	}
	public double getLatitud() {
		return Latitud;
	}
	public void setLatitud(double latitud) {
		Latitud = latitud;
	}
	public double getLongitud() {
		return Longitud;
	}
	public void setLongitud(double longitud) {
		Longitud = longitud;
	}

	public abstract Producto copia();
public String toString () {
	String aux;
	aux = this.Id  +";"+ this.Nom +";"
			+ this.Preu + ";" + this.Stock + ";" + this.IDproductor + ";" + this.NomProductor + ";"
			+  this.Latitud + " " + this.Longitud +";";
	return (aux);
}
}



