package fr.eni.aniforme.ihm;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogConnexion extends JDialog {

	private JTextField textNom;
	private JPasswordField textMotPasse;
	private JLabel labelNom, labelMotPasse;
	private JButton btnValider, btnQuitter;

	public interface ConnexionListener {
		void checkConnexion(String nom, String motPasse);

		void deconnexion();
	}

	private ConnexionListener listener;

	public DialogConnexion(ConnexionListener listener) {

		this.listener = listener;
		listener.deconnexion();

		setTitle("Connexion");
		setBounds(100, 100, 289, 184);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		getContentPane().add(getLabelNom());
		getContentPane().add(getLabelMotPasse());

		getContentPane().add(getTextNom());
		getContentPane().add(getTextMotPasse());

		getContentPane().add(getBtnValider());
		getContentPane().add(getBtnQuitter());
	}

	public JTextField getTextNom() {
		if (textNom == null) {
			textNom = new JTextField();
			textNom.setBounds(111, 35, 128, 20);
			textNom.setColumns(10);
		}
		return textNom;
	}

	public JPasswordField getTextMotPasse() {
		if (textMotPasse == null) {
			textMotPasse = new JPasswordField();
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
			btnValider.setBounds(20, 111, 89, 23);
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkConnexion()) {
						String password = new String(getTextMotPasse().getPassword());

						listener.checkConnexion(getTextNom().getText(), password);

					} else {
						JOptionPane.showMessageDialog(DialogConnexion.this, "Nom et mot de passe obligatoires");

					}
				}
			});
		}
		return btnValider;
	}

	public JButton getBtnQuitter() {
		if (btnQuitter == null) {
			btnQuitter = new JButton("Quitter");
			btnQuitter.setBounds(153, 111, 89, 23);
			btnQuitter.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return btnQuitter;
	}

	public boolean checkConnexion() {
		if (isNullOrEmpty(getTextNom().getText()) || isNullOrEmpty(new String(getTextMotPasse().getPassword()))) {
			return false;
		}
		return true;
	}

	private boolean isNullOrEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}

}
