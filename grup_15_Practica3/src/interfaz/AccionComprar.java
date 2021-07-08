package interfaz;

import java.awt.event.*;

public class AccionComprar implements ActionListener {
	private Ventana2Cliente finestra2;
	private Ventana3Cliente finestra3;

	public AccionComprar(Prueba finestra, Ventana2Cliente finestra2, Ventana3Cliente finestra3) {
		this.finestra2 = finestra2;
		this.finestra3 = finestra3;
	}

	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
		this.finestra2.setVisible(false);
		finestra3 = new Ventana3Cliente("Tienda: Comprar");
		finestra3.setLocation(100, 200);
		finestra3.setSize(800, 500);
		finestra3.setVisible(true);
	}

}
