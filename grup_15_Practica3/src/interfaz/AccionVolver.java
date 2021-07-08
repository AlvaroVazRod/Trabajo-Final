package interfaz;

import java.awt.event.*;

public class AccionVolver implements ActionListener {
	private Ventana2Productor ventana2;
	private Ventana3Productor ventana3;

	public AccionVolver(Ventana2Productor ventana2, Ventana3Productor ventana3) {
		this.ventana2 = ventana2;
		this.ventana3 = ventana3;
	}

	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
		ventana3.dispose();
		ventana2 = new Ventana2Productor("Productor: ");
		ventana2.setLocation(100, 200);
		ventana2.setSize(800, 500);
		ventana2.setVisible(true);

	}

}
