package interfaz;

import java.awt.event.*;

public class AccionListarStock implements ActionListener {
	private Ventana2Productor ventana2;

	public AccionListarStock(Ventana2Productor ventana2) {
		this.ventana2 = ventana2;

	}

	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
		ventana2.opcionStock();

	}

}
