package tiendaOnlie;

import java.io.*;
import java.util.Scanner;

/**
 * @author Alvaro Vazquez Rodriguez, Eloi Cuevas Marcos
 *
 */
public class Tienda {
	static Scanner teclat = new Scanner(System.in);
	static int numProduct = 1, Instancia = 100;

	/**
	 * @param args
	 * @throws IOException
	 * @throws PrecioIncorrecto
	 * @throws StockIncorrecto
	 * @throws ProductorInexistente
	 * @throws DistanciaIncorrecta
	 * @throws IdInexistente
	 * Esto es el main donde se pide la localización y el día y donde se inicia el método menú
	 */
	public static void main(String[] args) throws IOException, PrecioIncorrecto, StockIncorrecto, ProductorInexistente,
			DistanciaIncorrecta, IdInexistente {
		int numLinias = 5;
		ListaProductos p = new ListaProductos();
		ListaCompras c = new ListaCompras(numLinias);
		System.out.println("Buenas, que dia es hoy?");
		c.setFecha(teclat.nextLine());
		while (ListaCompras.getLatitud() == 0) {
			try {
				System.out.println("Pon la latitud de tu posicion?");
				c.setLatitud(Double.parseDouble(teclat.nextLine()));

			} catch (NumberFormatException e) {
				System.out.println("Error el formato es incorrecto " + e);
			}
		}
		while (ListaCompras.getLongitud() == 0) {
			try {
				System.out.println("Pon la longitud de tu posicion?");
				c.setLongitud(Double.parseDouble(teclat.nextLine()));

			} catch (NumberFormatException e) {
				System.out.println("Error el formato es incorrecto " + e);
			}
		}
		menu(p, c);
	}

	/**
	 * @param linea
	 * @param NumProduct
	 * @param p
	 * Este método separa la información de la linea leída y la guarda en la lista de Productos
	 */
	public static void Separador(String linea, int NumProduct, ListaProductos p) {
		String[] aux, aux1;
		int Preu, Stock, Peso = 0;

		String preu, stock, peso = null, celiaco = "0", id;
		Double latitud, longitud;
		aux = linea.split(";");
		preu = aux[2];
		Preu = Integer.parseInt(preu);
		stock = aux[3];
		Stock = Integer.parseInt(stock);
		aux1 = aux[6].split(" ");
		latitud = Double.parseDouble(aux1[0]);
		longitud = Double.parseDouble(aux1[1]);
		id = aux[0] + NumProduct;
		if (aux[0].contains("UT")) {
			peso = aux[7];
			Peso = Integer.parseInt(peso);
			p.AfegirUnidades(id, aux[1], Preu, Stock, aux[4], aux[5], latitud, longitud, Peso);

		}
		if (aux[0].contains("GT")) {
			celiaco = aux[7];
			p.AfegirGranel(id, aux[1], Preu, Stock, aux[4], aux[5], latitud, longitud, celiaco);
		}

	}

	/**
	 * Este método imprime por pantalla todas las opciones del menú
	 */
	public static void mostraMenu() {
		System.out.println("\n\nOpcions del menu:");
		System.out.println("\n\t1. Carregar les dades dels fitxers");
		System.out.println("\t2. Llistar les dades de tots els productes.");
		System.out.println("\t3. Llistar les dades dels productes que són aptes per a celíacs.");
		System.out.println("\t4. Llistar l’oferta de productes d’un determinat productor. ");
		System.out.println("\t5. Afegir un nou producte.");
		System.out.println("\t6. Esborrar tots els productes que ofereix un productor.");
		System.out.println("\t7. Modificar les dades d’un producte (preu i/o estoc). ");
		System.out.println("\t8. Llistar les compres que hem fet.");
		System.out.println("\t9. Modificar la nostra ubicació.");
		System.out.println(
				"\t10. Consultar els productors i els productes que ofereixen i que estan a una distància menor a la indicada per teclat.");
		System.out.println("\t11. Afegir una nova compra d’un producte.");
		System.out.println("\t12. Sortir. Permetre sortir guardant la informació de les classes als fitxers o no.");
		System.out.print("\n\t\t\tIndica opcio:\n");
	}

	/**
	 * @param p
	 * @param c
	 * @throws PrecioIncorrecto
	 * @throws StockIncorrecto
	 * @throws ProductorInexistente
	 * @throws DistanciaIncorrecta
	 * @throws IdInexistente
	 * Este método es el menú, que se acciona por un switch y tiene las opciones del 1 al 12
	 */
	private static void menu(ListaProductos p, ListaCompras c)
			throws PrecioIncorrecto, StockIncorrecto, ProductorInexistente, DistanciaIncorrecta, IdInexistente {
		int opcio = 0;
		boolean cerrar = true;
		mostraMenu();
		while (opcio == 0) {
			try {
				opcio = Integer.parseInt(teclat.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Error el formato es incorrecto " + e);

			}
		}
		while (opcio != 13 && cerrar != false) {

			switch (opcio) {
			case 1:
				opcio1(p, c);
				break;
			case 2:
				opcio2(p);
				break;
			case 3:
				opcio3(p);
				break;
			case 4:
				opcio4(p);
				break;
			case 5:
				opcio5(p);
				break;
			case 6:
				opcio6(p);
				break;
			case 7:
				opcio7(p);
				break;
			case 8:
				opcio8(c);
				break;
			case 9:
				opcio9(c);
				break;
			case 10:
				opcio10(p, c);
				break;
			case 11:
				opcio11(p, c);
				break;
			case 12:
				cerrar = opcio12(p, c, cerrar);
				break;
			default:
				break;
			}
			if (opcio != 12) {
				mostraMenu();
				try {
					opcio = Integer.parseInt(teclat.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Error el formato es incorrecto " + e);
					opcio = 0;
				}
			}

		}
	}

	/**
	 * @param p
	 * @param c
	 * Este método lee los dos archivos y los guarda en sus respectivas clases
	 */
	public static void opcio1(ListaProductos p, ListaCompras c) {
		String linea;
		File fichero = new File("Tienda.txt");
		Scanner s = null;

		try {

			s = new Scanner(fichero);

			while (s.hasNextLine()) {
				linea = s.nextLine();
				Separador(linea, numProduct, p);
				numProduct = numProduct + 1;

			}

		} catch (Exception e) {
			System.out.println("Error no hay archivo: " + e);
		} finally {

			try {
				if (s != null)
					s.close();
			} catch (Exception e) {
				System.out.println("Error archivo vacio: " + e);
			}
		}
		ObjectInputStream inputFile;
		try {
			inputFile = new ObjectInputStream(new FileInputStream("compras.bin"));
			for (int i = 0; i < Instancia; i++)
				c.AfegirComrpa((Compra) inputFile.readObject());

			inputFile.close();
		} catch (IOException e) {
			System.out.println("Archivo leido entero ");
		} catch (ClassNotFoundException e) {
			System.out.println("No se encuentra la clase Compra " + e);
		} catch (ClassCastException e) {
			System.out.println("El formato no es correcto para la clase vehiculo " + e);
		}

	}

	/**
	 * @param p
	 *  Este método imprime por pantalla la lista de Productos
	 */
	public static void opcio2(ListaProductos p) {
		Producto[] lista;
		lista = p.listar();
		for (int i = 0; i < lista.length; i++) {
			System.out.println(lista[i]);
		}

	}

	/**
	 * @param p
	 * Este método imprime por pantalla la lista de Productos Celiacos
	 */
	public static void opcio3(ListaProductos p) {
		Producto[] lista;
		lista = p.listarCeliacos();
		for (int i = 0; i < lista.length; i++) {
			System.out.println(lista[i]);
		}

	}

	/**
	 * @param p
	 * @throws ProductorInexistente
	 * Este método imprime por pantalla la lista de Productos por el id del productor
	 */
	public static void opcio4(ListaProductos p) throws ProductorInexistente {
		System.out.println("¿Cual es el DNI del Productor?");
		String idproductor = teclat.nextLine();
		Producto[] lista;
		try {
			lista = p.listarProductores(idproductor);
			for (int i = 0; i < lista.length; i++) {
				System.out.println(lista[i]);
			}
		} catch (ProductorInexistente e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * @param p
	 * Este método guarda un nuevo producto escrito por teclado
	 */
	public static void opcio5(ListaProductos p) {
		System.out.println("Añade un nuevo Producto");
		String aux = teclat.nextLine();
		try {
			Separador(aux, numProduct, p);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error el formato es incorrecto " + e);
		}
	}

	/**
	 * @param p
	 * @throws ProductorInexistente
	 * Este método borra todos los productos de un productor especificado por el id del productor
	 */
	public static void opcio6(ListaProductos p) throws ProductorInexistente {
		System.out.println("¿Cual es el DNI del Productor?");
		String idproductor = teclat.nextLine();
		try {
			p.borrarProductor(idproductor);
		} catch (ProductorInexistente e) {
			System.out.println("Error el formato es incorrecto " + e);
		}
	}

	/**
	 * @param p
	 * @throws PrecioIncorrecto
	 * @throws StockIncorrecto
	 * @throws IdInexistente
	 * Este método modifica un producto especificado por id
	 */
	public static void opcio7(ListaProductos p) throws PrecioIncorrecto, StockIncorrecto, IdInexistente {
		System.out.println("¿Que producto quieres modificar? Pon su id:");
		String id = teclat.nextLine();
		int idpro = p.buscarProducId(id);
		System.out.println("¿Que precio quiere?");
		int precio = 0, stock = 0;
		precio = Integer.parseInt(teclat.nextLine());
		try {
			p.modificarprecio(idpro, precio);
		} catch (PrecioIncorrecto e) {
			System.out.println(e.toString());
		}
		System.out.println("¿que Stock quiere?");
		stock = Integer.parseInt(teclat.nextLine());
		try {
			p.modificarstock(idpro, stock);
		} catch (StockIncorrecto e) {
			System.out.println(e.toString());
		}

	}

	/**
	 * @param c
	 * Este método imprime por pantalla la lista de Compras
	 */
	public static void opcio8(ListaCompras c) {
		System.out.println(c.toString());
	}

	/**
	 * @param c
	 * Este método permite cambiar la posición del usuario pasando los parámetros por teclado
	 */
	public static void opcio9(ListaCompras c) {
		double latitud, longitud;
		System.out.println("Pon la latitud de tu posicion?");
		latitud = Double.parseDouble(teclat.nextLine());
		c.setLatitud(latitud);
		System.out.println("Pon la longitud de tu posicion?");
		longitud = Double.parseDouble(teclat.nextLine());
		c.setLongitud(longitud);
	}

	/**
	 * @param p
	 * @param c
	 * @throws DistanciaIncorrecta
	 * Este método permite imprimir por pantalla los productos que están a una distancia especificada por parámetro escrito por teclado
	 */
	private static void opcio10(ListaProductos p, ListaCompras c) throws DistanciaIncorrecta {
		double valordistancia = 0;
		while (valordistancia == 0) {
			try {
				System.out.println("¿Cuanta distancia maxima quieres?");
				valordistancia = Double.parseDouble(teclat.nextLine());

			} catch (NumberFormatException e) {
				System.out.println("Error el formato es incorrecto " + e);
			}
		}
		Producto[] lista;
		try {
			lista = p.listarpordistancia(valordistancia);
			for (int i = 0; i < lista.length; i++) {
				System.out.println(lista[i]);
			}
		} catch (DistanciaIncorrecta e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * @param p
	 * @param com
	 * @throws StockIncorrecto
	 * @throws IdInexistente
	 * Este método permite hacer una nueva compra cogiendo la información del productor por su id
	 */
	public static void opcio11(ListaProductos p, ListaCompras com) throws StockIncorrecto, IdInexistente {
		int costototal = 0, cantcomp = 0;
		String id, nom = null;
		System.out.println("¿Que producto quieres comprar? Pon su id:");
		id = teclat.nextLine();
		int i = p.buscarProducId(id);
		System.out.println(p.listare(i));
		System.out.println("¿Cuanto quieres comprar?");
		cantcomp = Integer.parseInt(teclat.nextLine());
		Producto auxo;
		auxo = p.CogerInfo(i);
		id = auxo.getId();
		nom = auxo.getNom();
		costototal = auxo.getPreu();
		try {
			p.modificarstock(i, auxo.getStock() - cantcomp);
		} catch (StockIncorrecto e) {
			System.out.println(e.toString());
		}
		costototal = cantcomp * costototal;
		Compra c = new Compra(id, nom, costototal, cantcomp);
		com.AfegirComrpa(c);

	}

	/**
	 * @param p
	 * @param c
	 * @param cerrar
	 * @return
	 * Este método finaliza el programa y pregunta al usuario si desea guardar los cambios que ha hecho, si lo hace el programa sobreescribe los archivos especificados
	 */
	public static boolean opcio12(ListaProductos p, ListaCompras c, boolean cerrar) {
		System.out.println("Quiere salir guardando o sin guardar?");
		String respuesta = teclat.nextLine();
		Producto[] lista;
		lista = p.listar();
		if (respuesta.equalsIgnoreCase("guardando")) {
			p.restaurarid();
			FileWriter fichero = null;
			PrintWriter pw = null;
			try {
				fichero = new FileWriter("Tienda.txt");
				pw = new PrintWriter(fichero);

				for (int i = 0; i < p.numLinias; i++)
					pw.println(lista[i]);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != fichero)
						fichero.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			ObjectOutputStream outputFile;
			try {
				outputFile = new ObjectOutputStream(new FileOutputStream("compras.bin"));
				for (int i = 0; i < c.numLiniasC; i++)
					outputFile.writeObject(c.listar(i));
				outputFile.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			cerrar = false;
		}
		cerrar = false;
		return cerrar;

	}

}
