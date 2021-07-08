package interfaz;

import java.awt.event.*;
import javax.swing.JTextField;

public class AccionPrueba implements ActionListener {
	private Prueba finestra;
	private Ventana2Cliente finestra2;
	private JTextField j1;
	private JTextField j2;

	public AccionPrueba(Prueba finestra, JTextField j1, JTextField j2, Ventana2Cliente finestra2) {
		this.finestra = finestra;
		this.j1 = j1;
		this.j2 = j2;
		this.finestra2 = finestra2;

	}

	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
		String lat = j1.getText();
		String lon = j2.getText();
		finestra.afegirLat(lat);
		finestra.afegirLon(lon);
		this.finestra.setVisible(false);
		finestra2 = new Ventana2Cliente("Tienda: Lista Compra");
		finestra2.setLocation(100, 200);
		finestra2.setSize(800, 500);
		finestra2.setVisible(true);
	}

}
