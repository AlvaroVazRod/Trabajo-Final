package interfaz;

import java.awt.event.*;

public class AccionCambiar2 implements ActionListener {
	private Ventana2Cliente ventana2;
	private Ventana3Cliente ventana3;

	public AccionCambiar2(Ventana2Cliente ventana2, Ventana3Cliente ventana3) {
		this.ventana2 = ventana2;
		this.ventana3 = ventana3;
	}

	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
		this.ventana3.dispose();
		ventana2 = new Ventana2Cliente("Tienda: Lista Compra");
		ventana2.setLocation(100, 200);
		ventana2.setSize(800, 500);
		ventana2.setVisible(true);

	}

}
