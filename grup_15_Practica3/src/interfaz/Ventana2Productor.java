package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import tiendaOnlie.Granel;
import tiendaOnlie.Unidades;

public class Ventana2Productor extends JFrame {
	private static final long serialVersionUID = 1L;
	private Ventana3Productor ventana3;
	private Ventana1Productor ventana1Productor = new Ventana1Productor("");
	private JPanel panelbotons, panelescritura, panelinfo;
	private JButton boton1, boton2, boton3, boton5;
	private JButton[] listaBoton;

	public Ventana2Productor(String titol) {
		super(titol);
		ventana1Productor.dispose();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		iniContingutFinestra();
		this.setVisible(true);
	}

	private void iniContingutFinestra() {
		this.setLayout(new BorderLayout(10, 10));
		panelinfo = new JPanel();
		panelinfo.setLayout(new GridLayout(ventana1Productor.poner(), 1));
		panelescritura = new JPanel();
		panelescritura.setLayout(new FlowLayout());
		panelbotons = new JPanel();
		boton1 = new JButton("Celiacos");
		boton2 = new JButton("Peso");
		boton3 = new JButton("Stock");
		boton5 = new JButton("Salir");
		listaBoton = new JButton[ventana1Productor.poner()];
		int j = 0;
		AccionModificar acciomodifica = new AccionModificar(this, ventana3);
		for (int i = 0; i < listaBoton.length; i++) {
			if (ventana1Productor.listar(i).getIDproductor().equalsIgnoreCase(Ventana1Productor.Nif)) {
				listaBoton[j] = new JButton((ventana1Productor.listar(i).toString()));
				listaBoton[j].setBackground(Color.LIGHT_GRAY);
				listaBoton[j].addActionListener(acciomodifica);
				panelinfo.add(listaBoton[j]);
				j++;
			}

		}

		add(BorderLayout.CENTER, panelescritura);
		add(BorderLayout.SOUTH, panelbotons);
		add(BorderLayout.NORTH, panelinfo);
		panelbotons.add(boton1);
		panelbotons.add(boton2);
		panelbotons.add(boton3);
		panelbotons.add(boton5);
		AccionListar1 acciollista1 = new AccionListar1(this);
		boton1.addActionListener(acciollista1);
		AccionListar2 acciollista2 = new AccionListar2(this);
		boton2.addActionListener(acciollista2);
		AccionListarStock acciollistastock = new AccionListarStock(this);
		boton3.addActionListener(acciollistastock);
		boton5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ventana1Productor.guardar();
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new Ventana2Productor("Productor: ");
	}

	public void hacervisible() {
		this.setVisible(true);
	}

	public void opcionCeliacos() {
		AccionModificar acciomodifica = new AccionModificar(this, ventana3);
		for (int i = 0; i < listaBoton.length; i++) {
			if (listaBoton[i] != null)
				panelinfo.remove(listaBoton[i]);

		}
		listaBoton = new JButton[ventana1Productor.poner()];
		for (int i = 0; i < listaBoton.length; i++) {
			if (ventana1Productor.listar(i).getIDproductor().equalsIgnoreCase(Ventana1Productor.Nif)) {
				if (ventana1Productor.listar(i) instanceof Granel) {
					listaBoton[i] = new JButton((ventana1Productor.listar(i).toString()));
					listaBoton[i].setBackground(Color.LIGHT_GRAY);
					listaBoton[i].addActionListener(acciomodifica);
					panelinfo.add(listaBoton[i]);
				}
			}

		}

	}

	public void opcionPeso() {
		AccionModificar acciomodifica = new AccionModificar(this, ventana3);
		for (int i = 0; i < listaBoton.length; i++) {
			if (listaBoton[i] != null)
				panelinfo.remove(listaBoton[i]);

		}
		listaBoton = new JButton[ventana1Productor.poner()];
		for (int i = 0; i < listaBoton.length; i++) {
			if (ventana1Productor.listar(i).getIDproductor().equalsIgnoreCase(Ventana1Productor.Nif)) {
				if (ventana1Productor.listar(i) instanceof Unidades) {
					listaBoton[i] = new JButton((ventana1Productor.listar(i).toString()));
					listaBoton[i].setBackground(Color.LIGHT_GRAY);
					listaBoton[i].addActionListener(acciomodifica);
					panelinfo.add(listaBoton[i]);
				}
			}

		}
	}

	public void opcionStock() {

		AccionModificar acciomodifica = new AccionModificar(this, ventana3);
		for (int i = 0; i < listaBoton.length; i++) {
			if (listaBoton[i] != null)
				panelinfo.remove(listaBoton[i]);

		}
		int j = 0;
		listaBoton = new JButton[ventana1Productor.poner()];
		for (int i = 0; i < listaBoton.length; i++) {
			if (ventana1Productor.listar(i).getIDproductor().equalsIgnoreCase(Ventana1Productor.Nif)) {
				if (ventana1Productor.listar(i).getStock() > 0) {
					listaBoton[j] = new JButton((ventana1Productor.listar(i).toString()));
					listaBoton[j].setBackground(Color.LIGHT_GRAY);
					listaBoton[j].addActionListener(acciomodifica);
					panelinfo.add(listaBoton[j]);
					j++;
				}
			}

		}
	}

}
