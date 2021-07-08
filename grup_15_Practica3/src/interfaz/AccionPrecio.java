package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class AccionPrecio implements ActionListener {

	private Ventana1Productor ventana1;
	private JTextField nom1;

	public AccionPrecio(Ventana1Productor finestra1, Ventana3Productor finestra3, JTextField nom1) {
		this.ventana1 = finestra1;
		this.nom1 = nom1;
	}

	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
		ventana1.cambiarPrecio(Integer.parseInt(nom1.getText()));

	}

}
