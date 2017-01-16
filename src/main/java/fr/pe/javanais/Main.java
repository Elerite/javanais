package fr.pe.javanais;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JavanaisTF traducteur = new JavanaisTF();

	protected JButton traduireEnJavanais, traduireEnFrançais, traduireAutomatiquement;

	protected JTextField testField;

	public Main() {
		this.testField = new JTextField(50);
		
		// Bouton pour traduire en Javanais
		this.traduireEnJavanais = new JButton("Javanais");
		this.traduireEnJavanais.setMnemonic(KeyEvent.VK_B);
		this.traduireEnJavanais.setActionCommand("Javanais");

		// Bouton pour traduire en Français
		this.traduireEnFrançais = new JButton("Français");
		this.traduireEnFrançais.setMnemonic(KeyEvent.VK_C);
		this.traduireEnFrançais.setActionCommand("Français");

		// Bouton pour traduire en Automatique
		this.traduireAutomatiquement = new JButton("Automatique");
		this.traduireAutomatiquement.setMnemonic(KeyEvent.VK_D);
		this.traduireAutomatiquement.setActionCommand("Automatique");

		// Ajout des listener
		this.traduireEnJavanais.addActionListener(this);
		this.traduireEnFrançais.addActionListener(this);
		this.traduireAutomatiquement.addActionListener(this);

		// Ajout des composants à la frame
		add(this.traduireEnJavanais);
		add(this.traduireEnFrançais);
		add(this.traduireAutomatiquement);
		add(this.testField);
	}

	/**
	 * Create the GUI and show it.  For thread safety, 
	 * this method should be invoked from the 
	 * event-dispatching thread.
	 */
	private static void createAndShowGUI() {

		//Create and set up the window.
		JFrame frame = new JFrame("Javanais");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		Main newContentPane = new Main();
		newContentPane.setOpaque(true); //content panes must be opaque
		frame.setContentPane(newContentPane);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(); 
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Javanais":
			this.testField.setText(this.traducteur.traduireJavanais(this.testField.getText()));
			break;
		case "Français":
			this.testField.setText(this.traducteur.traduireFR(this.testField.getText()));
			break;
		case "Automatique":
			this.testField.setText(this.traducteur.traduireAutomatiquement(this.testField.getText()));
			break;
		default:
			break;
		}
	}

}
