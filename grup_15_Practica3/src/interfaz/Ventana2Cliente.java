package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Ventana2Cliente extends JFrame {
	private static final long serialVersionUID = 1L;
	private Ventana3Cliente ventana3;
	private Prueba ventana1prueba = new Prueba("");
	private JPanel panelbotons, panelescritura, panelinfo;
	private JButton boton1, boton2, boton3, boton4, boton5;
	private JTextField nom1, nom2, nom3;
	private JLabel etiqueta1, etiqueta2, etiqueta3;
	private JTextArea info;

	public Ventana2Cliente(String titol) {
		super(titol);
		ventana1prueba.dispose();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		iniContingutFinestra();
	}

	private void iniContingutFinestra() {
		this.setLayout(new BorderLayout(10, 10));
		panelinfo = new JPanel();
		panelescritura = new JPanel();
		panelescritura.setLayout(new FlowLayout());
		panelbotons = new JPanel();
		info = new JTextArea();
		boton1 = new JButton("Por peso");
		boton2 = new JButton("Por productor");
		boton3 = new JButton("Cambiar Posicion");
		boton4 = new JButton("Comprar");
		boton5 = new JButton("Salir");
		etiqueta1 = new JLabel("Indica Peso Maximo= ");
		panelescritura.add(etiqueta1);
		nom1 = new JTextField(10);
		panelescritura.add(nom1);
		etiqueta2 = new JLabel("Indica Peso Minimo= ");
		panelescritura.add(etiqueta2);
		nom2 = new JTextField(10);
		panelescritura.add(nom2);
		etiqueta3 = new JLabel("Indica Productor: (id) ");
		panelescritura.add(etiqueta3);
		nom3 = new JTextField(10);
		panelescritura.add(nom3);
		add(BorderLayout.CENTER, panelescritura);
		add(BorderLayout.SOUTH, panelbotons);
		add(BorderLayout.NORTH, panelinfo);
		panelbotons.add(boton1);
		panelbotons.add(boton2);
		panelbotons.add(boton3);
		panelbotons.add(boton4);
		panelbotons.add(boton5);
		info.setText(ventana1prueba.listarabajo());
		info.setEditable(false);
		panelinfo.add(info);
		AccionPeso acciopes = new AccionPeso(this, nom1, nom2, info, ventana1prueba);
		boton1.addActionListener(acciopes);
		AccionProductor accioprod = new AccionProductor(this, info, ventana1prueba, nom3);
		boton2.addActionListener(accioprod);
		AccionCambiar acciocam = new AccionCambiar(this, ventana1prueba);
		boton3.addActionListener(acciocam);
		AccionComprar acciocom = new AccionComprar(ventana1prueba, this, ventana3);
		boton4.addActionListener(acciocom);
		boton5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ventana1prueba.guardar();
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {

		new Ventana2Cliente("Tienda: Lista Compra ");
	}

}
