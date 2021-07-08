package tiendaOnlie;

import java.io.Serializable;

public class ListaCompras implements Serializable {
	/**
	 * Declaramos las variables
	 */
	private static final long serialVersionUID = 1L;
	private Compra[] listaC;
	public int numLiniasC = 0;
	private static double Longitud;
	private static double Latitud;
	private String Fecha;
	/**
	 * Creamos la lista
	 */
	public ListaCompras(int dimension) {
		listaC = new Compra[dimension];
		numLiniasC = 0;
	}

	/**
	 * @return devuelve la fecha
	 */
	public String getFecha() {
		return Fecha;
	}

	/**
	 * @param fecha 
	 * Cambia la fecha
	 */
	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	/**
	 * @return devuelve la longitud
	 */
	public static double getLongitud() {
		return Longitud;
	}

	/**
	 * @param longitud
	 * Cambia la longitud
	 */
	public void setLongitud(double longitud) {
		Longitud = longitud;
	}

	/**
	 * @return devuelve la latitud
	 */
	public static double getLatitud() {
		return Latitud;
	}

	/**
	 * @param latitud
	 * Cambia la longitud
	 */
	public void setLatitud(double latitud) {
		Latitud = latitud;
	}

	/**
	 * @return devuelve el numero de linias 
	 */
	public int getNumLiniasC() {
		return numLiniasC;
	}

	/**
	 * @param c
	 * Este método añade una Compra nueva a la lista 
	 */
	public void AfegirComrpa(Compra c) {
		if (numLiniasC >= listaC.length) {
			Compra[] aux = new Compra[listaC.length * 2];
			for (int i = 0; i < numLiniasC; i++)
				aux[i] = listaC[i];
			listaC = aux;
		}
		listaC[numLiniasC] = c.copia();
		numLiniasC++;
		setLatitud(Latitud);
		setLongitud(Longitud);
	}
	/**
	 *Este método devuelve el toString con la información de la lista
	 */
	public String toString() {
		String aux;
		aux = "Compra => numLiniasC " + numLiniasC + ", Fecha " + Fecha + ", Longitud " + Latitud + ", Longitud "
				+ Longitud;
		for (int j = 0; j < numLiniasC; j++) {
			aux = aux + "\nCompra " + (j + 1) + "\t " + listaC[j];
		}
		return aux;
	}

	/**
	 * @param i
	 * @return
	 * Este método retorna la Compra especificada por teclado
	 */
	public Compra listar(int i) {
		return listaC[i];
	}

	/**
	 * @return
	 * Este método retorna un String con la lista de Compras de la más nueva a la más antigua (se hace así para enviar esta información al JTextArea)
	 */
	public String listarMasNuevo() {
		String aux;
		aux = "Compra => numLiniasC " + numLiniasC;
		for (int j = numLiniasC - 1; j >= 0; j--) {
			aux = aux + "\nCompra " + (j + 1) + "\t " + listaC[j];
		}
		return aux;
	}

	/**
	 * @param pesoMax
	 * @param pesoMin
	 * @param p
	 * @return
	 * Este método retorna un String con la lista de Compras especificadas por un peso máximo y uno mínimo; y calcula el peso en las que son en unidades; de la más nueva a la más antigua (se hace así para enviar esta información al JTextArea)
	 */
	public String listaPorPeso(int pesoMax, int pesoMin, ListaProductos p) {
		String aux;
		Producto aux2;
		int peso = 0;
		aux = " ";
		for (int j = numLiniasC - 1; j >= 0; j--) {
			if (listaC[j].getId().contains("GT") && listaC[j].getCantComp() <= pesoMax
					&& listaC[j].getCantComp() >= pesoMin) {
				aux = aux + "\nCompra " + (j + 1) + "\t " + listaC[j];

			}
			if (listaC[j].getId().contains("UT") && listaC[j].getCantComp() <= pesoMax
					&& listaC[j].getCantComp() >= pesoMin) {
				for (int i = 0; i < p.numLinias; i++) {
					aux2 = p.listare(i);
					if (aux2.getId().equalsIgnoreCase(listaC[j].getId())) {
						peso = ((Unidades) aux2).getPeso() * listaC[j].getCantComp();
						aux = aux + "\nCompra " + (j + 1) + "\t " + listaC[j] + "\t Peso " + peso;
					}
				}

			}
		}

		return aux;
	}

	/**
	 * @param id
	 * @param p
	 * @return
	 * Este método retorna un String con la lista de Compras especificadas por el id del Productor; de la más nueva a la más antigua (se hace así para enviar esta información al JTextArea)
	 */
	public String listarPorProductor(String id, ListaProductos p) {
		String aux;
		aux = " ";
		for (int j = numLiniasC - 1; j >= 0; j--) {
			for (int i = 0; i < p.numLinias; i++) {
				if (p.listare(i).getIDproductor().equalsIgnoreCase(id)) {
					if (listaC[j].getId().equalsIgnoreCase(p.listare(i).getId())) {
						aux = aux + "\nCompra " + (j + 1) + "\t " + listaC[j];
					}
				}
			}

		}

		return aux;
	}

}
