package interfaz;

import java.awt.event.*;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AccionProductor implements ActionListener {
	private Prueba ventana1;
	private JTextField productor;
	private JTextArea info;

	public AccionProductor(Ventana2Cliente ventana2, JTextArea info, Prueba ventana1, JTextField productor) {
		this.productor = productor;
		this.info = info;
		this.ventana1 = ventana1;
	}

	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
		String produc = productor.getText();
		info.setText(ventana1.afegirProductor(produc));

	}

}
