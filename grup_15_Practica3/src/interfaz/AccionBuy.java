package interfaz;

import java.awt.event.*;
import javax.swing.JTextField;

import tiendaOnlie.IdInexistente;
import tiendaOnlie.StockIncorrecto;

public class AccionBuy implements ActionListener {
	private Prueba finestra;
	private JTextField id;
	private JTextField cantidad;

	public AccionBuy(Prueba finestra, Ventana3Cliente finestra3, JTextField id, JTextField cantidad) {
		this.finestra = finestra;
		this.id = id;
		this.cantidad = cantidad;
	}

	public void actionPerformed(ActionEvent evt) {
		evt.getSource();
		String idPro = id.getText();
		int cantPro = Integer.parseInt(cantidad.getText());
		try {
			finestra.comprarProducto(idPro, cantPro);
		} catch (StockIncorrecto e) {
			System.out.println(e.toString());
		} catch (IdInexistente e) {
			System.out.println(e.toString());
		}

	}
}
