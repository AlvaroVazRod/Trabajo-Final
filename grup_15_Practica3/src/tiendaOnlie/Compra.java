package tiendaOnlie;

import java.io.Serializable;

public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;
	private String Id;
	private String Nom;
	private int CostoTotal;
	private int CantComp;

	public Compra(String id, String nom, int costoTotal, int cantComp) {
		Id = id;
		Nom = nom;
		CostoTotal = costoTotal;
		CantComp = cantComp;
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

	public int getCostoTotal() {
		return CostoTotal;
	}

	public void setCostoTotal(int costoTotal) {
		CostoTotal = costoTotal;
	}

	public int getCantComp() {
		return CantComp;
	}

	public void setCantComp(int cantComp) {
		CantComp = cantComp;
	}

	public Compra copia() {
		Compra c = new Compra(Id, Nom, CostoTotal, CantComp);
		return c;
	}

	public String toString() {
		String aux;
		aux = this.Id + ";" + this.Nom + ";" + this.CostoTotal + ";" + this.CantComp;
		return (aux);
	}
}
