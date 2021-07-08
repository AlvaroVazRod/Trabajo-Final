package interfaz;

import java.awt.event.*;

public class AccionCambiar implements ActionListener {
	private Ventana2Cliente ventana2;
	private Prueba ventana1;

	public AccionCambiar(Ventana2Cliente ventana2, Prueba ventana1) {
		this.ventana2 = ventana2;
		this.ventana1 = ventana1;
	}

	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
		ventana1.hacervisible();
		this.ventana2.setVisible(false);

	}

}
