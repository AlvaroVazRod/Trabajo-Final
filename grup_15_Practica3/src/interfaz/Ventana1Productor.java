package interfaz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import tiendaOnlie.Compra;
import tiendaOnlie.ListaCompras;
import tiendaOnlie.ListaProductos;
import tiendaOnlie.Producto;

public class Ventana1Productor extends JFrame {
	private static final long serialVersionUID = 1L;
	private Ventana2Productor ventana2Productor;
	private static ListaCompras j;
	private static int numLinias = 5, Instancia = 100, numProduct = 1;
	private JPanel panelbotons, panelescritura;
	private JButton boton1, boton2;
	private JTextField nom1;
	private JLabel etiqueta1;
	private static ListaProductos p;
	public static String Nif;
	public static int numeroPro;
	public static String id;

	public Ventana1Productor(String titol) {
		super(titol);
		this.setLocation(100, 200);
		this.setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		iniContingutFinestra();
		this.setVisible(true);
	}

	private void iniContingutFinestra() {
		this.setLayout(new BorderLayout(200, 150));
		panelbotons = new JPanel();
		etiqueta1 = new JLabel("Pon tu NIF");
		etiqueta1.setFont(new Font("Calibri", Font.BOLD, 24));
		etiqueta1.setForeground(Color.RED);
		this.add(etiqueta1, BorderLayout.NORTH);
		boton1 = new JButton();
		boton1.setText("Enviar");
		panelbotons.add(boton1);
		boton2 = new JButton();
		boton2.setText("Salir");
		panelbotons.add(boton2);
		panelescritura = new JPanel();
		etiqueta1 = new JLabel("Indica tu nif= ");
		panelescritura.add(etiqueta1);
		nom1 = new JTextField(25);
		panelescritura.add(nom1);
		nom1.setEditable(true);
		add(BorderLayout.CENTER, panelescritura);
		add(BorderLayout.SOUTH, panelbotons);
		AccionEnviar accioEnviar = new AccionEnviar(this, nom1, ventana2Productor);
		boton1.addActionListener(accioEnviar);
		boton2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}

		});

	}

	public static void main(String[] args) {
		ObjectInputStream inputFile;
		p = new ListaProductos();
		j = new ListaCompras(numLinias);
		try {
			inputFile = new ObjectInputStream(new FileInputStream("compras.bin"));
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
				Separador(linea, numProduct);
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
		new Ventana1Productor("Productor: Nif");
	}

	public boolean nifProductor(String nif) {

		for (int i = 0; i < p.numLinias; i++) {
			if (p.listare(i).getIDproductor().equalsIgnoreCase(nif)) {
				return true;
			}

		}
		return false;
	}

	public int poner() {
		numeroPro = p.numLinias;
		return numeroPro;
	}

	public void cambiarPrecio(int Precio) {
		for (int i = 0; i < p.numLinias; i++) {
			if (p.listare(i).getId().equalsIgnoreCase(id)) {
				p.listare(i).setPreu(Precio);
			}
		}

	}

	public void cambiarStock(int stock) {
		for (int i = 0; i < p.numLinias; i++) {
			if (p.listare(i).getId().equalsIgnoreCase(id)) {
				p.listare(i).setStock(stock);
				;
			}
		}

	}

	public static void Separador(String linea, int NumProduct) {
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

	public Producto listar(int i) {
		return p.listare(i);

	}

	public Producto[] listar2() {
		Producto[] lista;
		lista = p.listar();
		return lista;

	}

	public Producto[] listaCeliacos() {
		Producto[] lista;
		lista = p.listarCeliacos();
		return lista;

	}

	public void guardar() {
		String guardar = JOptionPane.showInputDialog("¿Quieres guardar en el fichero?");

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
					pw.println(p.listare(i));

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
				for (int i = 0; i < j.numLiniasC; i++)
					outputFile.writeObject(j.listar(i));
				outputFile.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		} else {

		}
	}

}
