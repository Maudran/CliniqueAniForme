package fr.eni.aniforme.ihm;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogConnexion extends JDialog {

	private JTextField textNom, textMotPasse;
	private JLabel labelNom, labelMotPasse;
	private JButton btnValider;

	public DialogConnexion() {
		initialize();
	}

	private void initialize() {

		this.setTitle("Connexion");
		this.setBounds(100, 100, 289, 184);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);

		
		this.getContentPane().add(getLabelNom());
		this.getContentPane().add(getLabelMotPasse());

		this.getContentPane().add(getTextNom());
		this.getContentPane().add(getTextMotPasse());
		
		this.getContentPane().add(getBtnValider());
	}

	public JTextField getTextNom() {
		if (textNom == null) {
			textNom = new JTextField();
			textNom.setBounds(111, 35, 128, 20);
			textNom.setColumns(10);
		}
		return textNom;
	}

	public JTextField getTextMotPasse() {
		if (textMotPasse == null) {
			textMotPasse = new JTextField();
			textMotPasse.setBounds(111, 72, 128, 20);
			textMotPasse.setColumns(10);
		}
		return textMotPasse;
	}

	public JLabel getLabelNom() {
		if (labelNom == null) {
			labelNom = new JLabel("Nom");
			labelNom.setBounds(10, 38, 46, 14);
		}
		return labelNom;
	}

	public JLabel getLabelMotPasse() {
		if (labelMotPasse == null) {
			labelMotPasse = new JLabel("Mot de passe");
			labelMotPasse.setBounds(10, 75, 64, 14);
		}
		return labelMotPasse;
	}

	public JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.setBounds(153, 111, 89, 23);
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnValider;
	}

}
