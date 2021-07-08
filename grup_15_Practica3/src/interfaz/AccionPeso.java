package interfaz;

import java.awt.event.*;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AccionPeso implements ActionListener {
	private Prueba ventana1;
	private JTextField pesoMax, pesoMin;
	private JTextArea info;

	public AccionPeso(Ventana2Cliente ventana2, JTextField pesoMax, JTextField pesoMin, JTextArea info,
			Prueba ventana1) {
		this.pesoMax = pesoMax;
		this.pesoMin = pesoMin;
		this.info = info;
		this.ventana1 = ventana1;
	}

	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
		String max = pesoMax.getText();
		String min = pesoMin.getText();
		try {
		info.setText(ventana1.afegirPeso(Integer.parseInt(max), Integer.parseInt(min)));
		}
		catch(NumberFormatException e) {
			System.out.println("Error el formato es incorrecto " + e);
		}
	}

}
