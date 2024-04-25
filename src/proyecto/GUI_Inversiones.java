package proyecto;

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JButton;

public class GUI_Inversiones extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public GUI_Inversiones() {
		setBackground(new Color(255, 128, 192));
		setBounds(new Rectangle(196, 116, 785, 472));
		setLayout(null);
		
		JButton btnNewButton = new JButton("Pene");
		btnNewButton.setBounds(277, 206, 89, 23);
		add(btnNewButton);

	}

}
