package interfaz;

import java.awt.event.*;

import javax.swing.JButton;

public class AccionModificar implements ActionListener {
	private Ventana2Productor ventana2;
	private Ventana3Productor ventana3;

	public AccionModificar(Ventana2Productor finestra2, Ventana3Productor finestra3) {
		this.ventana2 = finestra2;
		this.ventana3 = finestra3;
	}

	public void actionPerformed(ActionEvent evt) {
		JButton b = (JButton) evt.getSource();
		Ventana1Productor.id = b.getText().substring(0, 4);
		this.ventana2.setVisible(false);
		ventana3 = new Ventana3Productor("Productor: Modificar");
		ventana3.setLocation(100, 200);
		ventana3.setSize(800, 500);
		ventana3.setVisible(true);
	}

}
