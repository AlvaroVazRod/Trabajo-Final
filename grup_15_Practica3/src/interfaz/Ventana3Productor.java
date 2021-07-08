package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Ventana3Productor extends JFrame {
	private static final long serialVersionUID = 1L;
	private Ventana1Productor ventana1Productor = new Ventana1Productor("");
	private Ventana2Productor ventana2Productor = new Ventana2Productor("");
	private JPanel panelbotons, panelescritura;
	private JButton boton1, boton2, boton3, boton4;
	private JTextField nom1, nom2;
	private JLabel etiqueta1, etiqueta2;

	public Ventana3Productor(String titol) {
		super(titol);
		ventana1Productor.dispose();
		ventana2Productor.dispose();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		iniContingutFinestra();
		this.setVisible(true);
	}

	private void iniContingutFinestra() {
		this.setLayout(new BorderLayout(200, 150));
		panelbotons = new JPanel();
		etiqueta1 = new JLabel();
		this.add(etiqueta1, BorderLayout.NORTH);
		panelbotons = new JPanel();
		boton1 = new JButton();
		boton1.setText("Precio");
		panelbotons.add(boton1);
		boton2 = new JButton();
		boton2.setText("Stock");
		panelbotons.add(boton2);
		boton3 = new JButton();
		boton3.setText("Salir");
		panelbotons.add(boton3);
		boton4 = new JButton();
		boton4.setText("Volver");
		panelbotons.add(boton4);
		panelescritura = new JPanel();
		etiqueta1 = new JLabel("Precio= ");
		panelescritura.add(etiqueta1);
		nom1 = new JTextField(25);
		panelescritura.add(nom1);
		nom1.setEditable(true);
		etiqueta2 = new JLabel("Stock= ");
		panelescritura.add(etiqueta2);
		nom2 = new JTextField(25);
		panelescritura.add(nom2);
		nom2.setEditable(true);
		add(BorderLayout.CENTER, panelescritura);
		add(BorderLayout.SOUTH, panelbotons);
		AccionPrecio acciopreu = new AccionPrecio(ventana1Productor, this, nom1);
		boton1.addActionListener(acciopreu);
		AccionStock acciostock = new AccionStock(ventana1Productor, this, nom2);
		boton2.addActionListener(acciostock);
		boton3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ventana1Productor.guardar();
				System.exit(0);
			}

		});
		AccionVolver acciovolve = new AccionVolver(ventana2Productor, this);
		boton4.addActionListener(acciovolve);

	}

	public static void main(String[] args) {
		new Ventana3Productor("Productor: Modicar");
	}

}
