package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Ventana3Cliente extends JFrame {
	private static final long serialVersionUID = 1L;
	private Ventana2Cliente ventana2 = new Ventana2Cliente("");
	private Prueba ventana1prueba = new Prueba("");
	private JPanel panelbotons, panelescritura, panelinfo;
	private JButton boton1, boton2, boton3;
	private JTextField nom1, nom2;
	private JLabel etiqueta1, etiqueta2;
	private JTextArea info, info2;

	public Ventana3Cliente(String titol) {
		super(titol);
		ventana1prueba.dispose();
		ventana2.dispose();
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
		info2 = new JTextArea();
		boton1 = new JButton("Comprar");
		boton2 = new JButton("Salir");
		boton3 = new JButton("Volver");
		etiqueta1 = new JLabel("Indica id= ");
		panelescritura.add(etiqueta1);
		nom1 = new JTextField(15);
		panelescritura.add(nom1);
		etiqueta2 = new JLabel("Indica cantidad= ");
		panelescritura.add(etiqueta2);
		nom2 = new JTextField(15);
		panelescritura.add(nom2);
		add(BorderLayout.CENTER, panelescritura);
		add(BorderLayout.SOUTH, panelbotons);
		add(BorderLayout.NORTH, panelinfo);
		panelbotons.add(boton1);
		panelbotons.add(boton2);
		panelbotons.add(boton3);
		info.setText(ventana1prueba.listarabajo());
		info2.setText(ventana1prueba.listarProd());
		info.setEditable(false);
		info2.setEditable(false);
		panelinfo.add(info);
		panelinfo.add(info2);
		AccionBuy acciobuy = new AccionBuy(ventana1prueba, this, nom1, nom2);
		boton1.addActionListener(acciobuy);
		boton2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ventana1prueba.guardar();
				System.exit(0);
			}
		});
		AccionCambiar2 acciocam2 = new AccionCambiar2(ventana2, this);
		boton3.addActionListener(acciocam2);

	}

	public static void main(String[] args) {
		new Ventana3Cliente("Tienda: Comprar ");
	}

	public void hacervisible() {
		Ventana3Cliente.this.setVisible(true);
	}
}
