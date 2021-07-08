package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class AccionStock implements ActionListener {

	private Ventana1Productor ventana1;
	private JTextField nom2;

	public AccionStock(Ventana1Productor finestra1, Ventana3Productor finestra3, JTextField nom2) {
		this.ventana1 = finestra1;
		this.nom2 = nom2;
	}

	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
		ventana1.cambiarStock(Integer.parseInt(nom2.getText()));

	}

}
