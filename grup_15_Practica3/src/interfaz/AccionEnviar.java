package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class AccionEnviar implements ActionListener {
	private Ventana1Productor finestra;
	private Ventana2Productor finestra2;
	private JTextField Nif2;

	public AccionEnviar(Ventana1Productor finestra, JTextField nif, Ventana2Productor finestra2) {
		this.finestra = finestra;
		this.Nif2 = nif;
		this.finestra2 = finestra2;

	}

	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
		String nif = Nif2.getText();
		if (finestra.nifProductor(nif)) {
			Ventana1Productor.Nif = nif;
			this.finestra.setVisible(false);
			finestra2 = new Ventana2Productor("Productor: ");
			finestra2.setLocation(100, 200);
			finestra2.setSize(800, 500);
			finestra2.setVisible(true);

		}
	}

}
