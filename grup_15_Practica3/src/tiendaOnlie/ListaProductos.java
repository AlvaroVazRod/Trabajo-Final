package tiendaOnlie;



public class ListaProductos {
	/**
	 * Declaramos las variables
	 */
	private static int MAX = 1000;
	private Producto[] lista;
	public int numLinias = 0;
	public int numGranel = 0;
	public int numUnidades = 0;
	/**
	 * Creamos la lista
	 */
	public ListaProductos() {
		lista = new Producto[MAX];
		numLinias = 0;
	}

	/**
	 * @param id
	 * @param nom
	 * @param preu
	 * @param stock
	 * @param iDproductor
	 * @param nomProductor
	 * @param latitud
	 * @param longitud
	 * @param celiacos
	 * Este método añade un producto(Granel) nuevo a la lista 
	 */
	public void AfegirGranel(String id, String nom, int preu, int stock, String iDproductor, String nomProductor,
			double latitud, double longitud, String celiacos) {
		Granel g = new Granel(id, nom, preu, stock, iDproductor, nomProductor, latitud, longitud, celiacos);
		if (numLinias < lista.length) {
			lista[numLinias] = g;
			numLinias++;
			numGranel++;
		}

	}

	/**
	 * @param id
	 * @param nom
	 * @param preu
	 * @param stock
	 * @param iDproductor
	 * @param nomProductor
	 * @param latitud
	 * @param longitud
	 * @param peso
	 * Este método añade un producto(Unidades) nuevo a la lista 
	 */
	public void AfegirUnidades(String id, String nom, int preu, int stock, String iDproductor, String nomProductor,
			double latitud, double longitud, int peso) {
		Unidades u = new Unidades(id, nom, preu, stock, iDproductor, nomProductor, latitud, longitud, peso);
		if (numLinias < lista.length) {
			lista[numLinias] = u;
			numLinias++;
			numUnidades++;
		}

	}

	/**
	 *Este método devuelve el toString con la información de la lista
	 */
	public String toString() {
		String aux;
		aux = "Producto => numLinies " + numLinias;
		for (int j = 0; j < numLinias; j++) {
			aux = aux + "\nProducto " + (j + 1) + "\t " + lista[j];
		}
		return aux;
	}

	/**
	 * @param i
	 * @return retorna el producto especificado
	 * Este método devuelve el producto especificado por su posicion, pero solo con la informaicon de id, nombre, precio y stock
	 */
	public Producto CogerInfo(int i) {
		Producto aux;
		String Id, Nom;
		int Precio, Stock;
		if (lista[i].getId().contains("UT")) {
			Id = lista[i].getId();
			Nom = lista[i].getNom();
			Precio = lista[i].getPreu();
			Stock = lista[i].getStock();
			aux = new Unidades(Id, Nom, Precio, Stock, null, null, 0, 0, 0);
		} else {
			Id = lista[i].getId();
			Nom = lista[i].getNom();
			Precio = lista[i].getPreu();
			Stock = lista[i].getStock();
			aux = new Granel(Id, Nom, Precio, Stock, null, null, 0, 0, null);
		}
		

		return aux;
	}

	/**
	 * @param idproductor
	 * @return
	 * @throws ProductorInexistente
	 * Este método devuelve una lista de Productos que tienen el mismo id de productor
	 */
	public Producto[] listarProductores(String idproductor) throws ProductorInexistente {
		Producto[] pro = new Producto[numLinias];
		int j, pos = 0;

		for (j = 0; j < numLinias; j++) {
			if (lista[j].getIDproductor().equalsIgnoreCase(idproductor)) {
				pro[pos] = lista[j];
				pos++;
			}

		}
		if (pos != 0) {
			Producto[] exo = new Producto[pos];
			for (int i = 0; i < pos; i++) {
				exo[i] = pro[i];
			}
			return (exo);
		} else {
			throw new ProductorInexistente(idproductor);
		}

	}

	/**
	 * @param idproductor
	 * @throws ProductorInexistente
	 * Este método borra todos los productos de la lista que tienen el id de productor que se les pasa por parametro 
	 */
	public void borrarProductor(String idproductor) throws ProductorInexistente {
		int i = 0, numeronull = 0;
		while (i < numLinias) {

			if (lista[i].getIDproductor().equals(idproductor)) {
				lista[i] = null;
				numeronull++;
			}
			i++;
		}
		if (numeronull != 0) {
			ordenarlista(numeronull);
		} else {
			throw new ProductorInexistente(idproductor);
		}

	}

	/**
	 * @param numeronull
	 * Este método reordena la lista quitando los espacios en blanco y renombrando los numeros de las id de producto
	 */
	public void ordenarlista(int numeronull) {
		int j = 0;
		Producto aux;
		while (j < numLinias) {
			for (int i = 0; i < numLinias; i++) {

				if (lista[i] == (null)) {
					aux = lista[i + 1];
					lista[i + 1] = lista[i];
					lista[i] = aux;
					;
				}

			}
			j++;
		}
		numLinias = numLinias - numeronull;
		int numProducto = 1;
		for (int i = 0; i < numLinias; i++) {
			String id;
			id = lista[i].getId().substring(0, 3) + numProducto;
			lista[i].setId(id);
			numProducto++;

		}
	}



	/**
	 * @param id
	 * @return
	 * @throws IdInexistente
	 * Este método retorna la posicion del producto que se especifica por id
	 */
	public int buscarProducId(String id) throws IdInexistente {
		int i = 0,numeroid=0;
		boolean encontrar = false;
		if(encontrar==false) {
		while (encontrar != true && i < numLinias) {
			if (lista[i].getId().equalsIgnoreCase(id)) {
				encontrar = true;
				numeroid=i;
				
			}

			i++;
		}
		return numeroid;
		}else {
			throw new IdInexistente(id);
		}
		
		
	}

	/**
	 * @param i
	 * @return
	 * Este método retorna la latitud del usuario
	 */
	public double distanciaLatitud(int i) {
		return lista[i].getLatitud();
	}

	/**
	 * @param i
	 * @return
	 * Este método retorna la longitud del usuario
	 */
	public double distanciaLongitud(int i) {
		return lista[i].getLongitud();
	}

	/**
	 * @return
	 * Este método retorna la lista de Productos entera
	 */
	public Producto[] listar() {
		Producto[] pro = new Producto[numLinias];
		int j;

		for (j = 0; j < numLinias; j++) {
			pro[j] = lista[j];
		}

		return (pro);
	}

	/**
	 * @return
	 * Este método retorna la lista de Productos de Unidades
	 */
	public Producto[] listarPeso() {
		Producto[] pro = new Producto[numLinias];
		int j, pos = 0;

		for (j = 0; j < numLinias; j++) {
			if (lista[j] instanceof Unidades) {
				pro[pos] = lista[j];
				pos++;
			}

		}

		Producto[] exo = new Producto[pos];
		for (int i = 0; i < pos; i++) {
			exo[i] = pro[i];
		}

		return (exo);
	}

	/**
	 * @return
	 * Este método retorna la lista de Productos de Granel
	 */
	public Producto[] listarCeliacos() {
		Granel[] pro = new Granel[numLinias];
		int j, pos = 0;

		for (j = 0; j < numLinias; j++) {
			if (lista[j] instanceof Granel) {
				pro[pos] = (Granel) lista[j];
				pos++;
			}

		}

		Producto[] exo = new Producto[pos];
		for (int i = 0; i < pos; i++) {
			exo[i] = pro[i];
		}

		return (exo);
	}

	/**
	 * @param i
	 * @return
	 * Este método retorna el producto que se le especifica por posicion
	 */
	public Producto listare(int i) {
		return lista[i];
	}

	/**
	 * Este método borra los numeros del id de cada producto
	 */
	public void restaurarid() {
		for (int i = 0; i < numLinias; i++) {
			lista[i].setId(lista[i].getId().substring(0, 3));
		}
		
	}

	/**
	 * @param id
	 * @param stock
	 * @throws StockIncorrecto
	 * Este método cambia el stock de un producto en especifico
	 */
	public void modificarstock(int id, int stock) throws StockIncorrecto {
		if (stock >= 0) {
			lista[id].setStock(stock);
		} else {
			throw new StockIncorrecto(stock);
		}
	}

	/**
	 * @param id
	 * @param precio
	 * @throws PrecioIncorrecto
	 * Este método cambia el precio de un producto en especifico
	 */
	public void modificarprecio(int id, int precio) throws PrecioIncorrecto {
		if (precio >= 0) {
			lista[id].setPreu(precio);
		} else {
			throw new PrecioIncorrecto(precio);
		}
	}

	/**
	 * @param valordistancia
	 * @return
	 * @throws DistanciaIncorrecta
	 * Este método calcula la distancia en base a la longitud y la latitud del usuario y los objetos y devuelve una lista de Productos que estan mas cerca de la distancia indicada por parametro
	 */
	public Producto[] listarpordistancia(double valordistancia) throws DistanciaIncorrecta {
		double lon1, lat1, lon2, lat2;
		double earthRadius = 6371;
		double distanceInMeters = 0;
		int i = 0;
		Producto[] pro = new Producto[numLinias];
		int pos = 0;
		if (valordistancia >= 0) {
			while (numLinias > i) {
				lat1 = Math.toRadians(ListaCompras.getLatitud());
				lon1 = Math.toRadians(ListaCompras.getLongitud());
				lat2 = Math.toRadians(lista[i].getLatitud());
				lon2 = Math.toRadians(lista[i].getLongitud());
				double dlon = (lon2 - lon1);
				double dlat = (lat2 - lat1);

				double sinlat = Math.sin(dlat / 2);
				double sinlon = Math.sin(dlon / 2);

				double a = (sinlat * sinlat) + Math.cos(lat1) * Math.cos(lat2) * (sinlon * sinlon);
				double j = 2 * Math.asin(Math.min(1.0, Math.sqrt(a)));

				distanceInMeters = earthRadius * j * 1000;
				if (distanceInMeters <= valordistancia) {
					pro[pos] = lista[i];
					pos++;
				}
				i++;
			}
			Producto[] aux = new Producto[pos];
			for (int j = 0; j < pos; j++) {
				aux[j] = pro[j];
			}
			return aux;
		} else {
			throw new DistanciaIncorrecta(valordistancia);
		}
	}

}
