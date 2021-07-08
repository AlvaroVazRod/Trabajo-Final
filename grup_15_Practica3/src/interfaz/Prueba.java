package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;
import tiendaOnlie.Compra;
import tiendaOnlie.IdInexistente;
import tiendaOnlie.ListaCompras;
import tiendaOnlie.ListaProductos;
import tiendaOnlie.Producto;
import tiendaOnlie.StockIncorrecto;

/**
 * @author Alvaro
 *
 */
/**
 * @author Alvaro
 *
 */
public class Prueba extends JFrame {
	private static final long serialVersionUID = 1L;
	private Ventana2Cliente ventana2;
	private static ListaCompras j;
	private static int Instancia = 100, numProduct = 1;
	private JPanel panelbotons, panelescritura;
	private JButton boton1, boton2;
	private JTextField nom1, nom2;
	private JLabel etiqueta1, etiqueta2;
	private static ListaProductos p;

	public Prueba(String titol) {
		super(titol);
		this.setLocation(100, 200);
		this.setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		iniContingutFinestra();
		this.setVisible(true);
	}

	private void iniContingutFinestra() {
		this.setLayout(new BorderLayout(10, 10));
		panelbotons = new JPanel();
		etiqueta1 = new JLabel("Pon tu Ubicacion");
		etiqueta1.setFont(new Font("Calibri", Font.BOLD, 24));
		etiqueta1.setForeground(Color.BLUE);
		this.add(etiqueta1, BorderLayout.NORTH);
		panelescritura = new JPanel();
		boton1 = new JButton();
		boton1.setText("Enviar");
		panelbotons.add(boton1);
		boton2 = new JButton();
		boton2.setText("Salir");
		panelbotons.add(boton2);
		etiqueta1 = new JLabel("Indica tu latitud= ");
		panelescritura.add(etiqueta1);
		nom1 = new JTextField(25);
		panelescritura.add(nom1);
		nom1.setEditable(true);
		etiqueta2 = new JLabel("Indica tu longitud= ");
		panelescritura.add(etiqueta2);
		nom2 = new JTextField(25);
		panelescritura.add(nom2);
		nom2.setEditable(true);
		add(BorderLayout.CENTER, panelescritura);
		add(BorderLayout.SOUTH, panelbotons);

		AccionPrueba accioBoto = new AccionPrueba(this, nom1, nom2, ventana2);
		boton1.addActionListener(accioBoto);
		boton2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {
		p = new ListaProductos();
		ObjectInputStream inputFile;
		j = new ListaCompras(Instancia);
		try {
			inputFile = new ObjectInputStream(
					new FileInputStream("compras.bin"));
			for (int i = 0; i < Instancia; i++)
				j.AfegirComrpa((Compra) inputFile.readObject());

			inputFile.close();
		} catch (IOException e) {
			System.out.println("Archivo leido entero.");
		} catch (ClassNotFoundException e) {
			System.out.println("Error, no es troba la classe Vehicle." + e);
		} catch (ClassCastException e) {
			System.out.println(
					"Error, el format de l'arxiu no és correcte per la definició actual de la classe Vehicle." + e);
		}
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

		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		} finally {

			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("Mensaje 2: " + ex2.getMessage());
			}
		}
		new Prueba("Tienda: Ubicacion");
	}

	public void afegirLat(String lletra) {
		try {
			j.setLatitud(Double.parseDouble(lletra));
		} catch (NumberFormatException e) {
			System.out.println("Error el formato es incorrecto " + e);
		}
	}

	public void afegirLon(String lletra) {
		try {
			j.setLongitud(Double.parseDouble(lletra));
		} catch (NumberFormatException e) {
			System.out.println("Error el formato es incorrecto " + e);
		}
	}

	public String listarabajo() {
		return j.listarMasNuevo();
	}

	public String listarProd() {
		return p.toString();
	}

	public String afegirPeso(int max, int min) {
		return j.listaPorPeso(max, min, p);
	}

	public String afegirProductor(String id) {
		return j.listarPorProductor(id, p);
	}

	public void hacervisible() {
		Prueba.this.setVisible(true);
	}

	public void guardar() {
		String guardar = JOptionPane.showInputDialog("¿Quieres guardar en el fichero?");
		Producto[] lista;
		lista = p.listar();
		while (guardar == null || guardar.equals("")) {
			JOptionPane.showMessageDialog(null, "No es una respuesta", "ERROR", JOptionPane.ERROR_MESSAGE);
			guardar = JOptionPane.showInputDialog("¿Quieres guardar?");
		}
		if (guardar.equalsIgnoreCase("si")) {
			for (int i = 0; i < p.numLinias; i++) {
				p.restaurarid();
			}
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
				outputFile = new ObjectOutputStream(
						new FileOutputStream("compras.bin"));
				for (int i = 0; i < j.numLiniasC; i++)
					outputFile.writeObject(j.listar(i));
				outputFile.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		} else {

		}
	}

	public void comprarProducto(String id, int cant) throws StockIncorrecto, IdInexistente {
		int costototal = 0;
		int i = p.buscarProducId(id);
		Producto auxo;
		auxo = p.CogerInfo(i);
		id = auxo.getId();
		costototal = auxo.getPreu();
		p.modificarstock(i, auxo.getStock() - cant);
		costototal = cant * costototal;
		Compra c = new Compra(id, auxo.getNom(), costototal, cant);
		j.AfegirComrpa(c);

	}

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
}
