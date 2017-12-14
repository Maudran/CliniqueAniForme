package fr.eni.aniforme.ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.aniforme.bo.Personnel;

public class DialogReinitMotPasse extends JDialog {

	private JTextField txtNewPassword;
	private JLabel lblNewPassword, messageErreur;
	private JButton btnValider;
	private JPanel panelTexte, panelBtn, panelMessage;

	public interface ReinitListener {
		void reinitMotPasse(String motPasse);
	}

	private ReinitListener listener;

	public DialogReinitMotPasse(Personnel employe, ReinitListener listener) {

		this.listener = listener;

		setLayout(new BorderLayout());
		setTitle("Réinitialiser mot de passe");
		setSize(400, 200);
		setLocationRelativeTo(null);

		add(getPanelTexte(), BorderLayout.NORTH);
		add(getPanelMessage(), BorderLayout.CENTER);
		add(getPanelBtn(), BorderLayout.SOUTH);

	}

	public JTextField getTxtNewPassword() {
		if (txtNewPassword == null) {
			txtNewPassword = new JTextField(15);
		}

		return txtNewPassword;
	}

	public JLabel getLblNewPassword() {
		if (lblNewPassword == null) {
			lblNewPassword = new JLabel("Nouveau mot de passe : ");
		}
		return lblNewPassword;
	}

	public JLabel getMessageErreur() {
		if (messageErreur == null) {
			messageErreur = new JLabel();
		}
		return messageErreur;
	}

	public JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton(new ImageIcon("ic_done_black_24dp/web/ic_done_black_24dp_1x.png"));
			btnValider.setContentAreaFilled(false);
			btnValider.setToolTipText("Valider nouveau mot de passe");
			btnValider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (checkPassword()) {
						listener.reinitMotPasse(getTxtNewPassword().getText());
						getMessageErreur().setText("");
						getTxtNewPassword().setText("");
						setVisible(false);
					} else {
						getMessageErreur().setText("Le nouveau mot de passe doit comporter entre 1 et 10 caractères");
					}

				}
			});
		}
		return btnValider;
	}

	public JPanel getPanelTexte() {
		if (panelTexte == null) {
			panelTexte = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(15, 15, 15, 15);
			gbc.gridx = 0;
			gbc.gridy = 0;
			panelTexte.add(getLblNewPassword(), gbc);
			gbc.gridx = 1;
			gbc.gridy = 0;
			panelTexte.add(getTxtNewPassword(), gbc);
		}
		return panelTexte;
	}

	public JPanel getPanelBtn() {
		if (panelBtn == null) {
			panelBtn = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(15, 15, 15, 15);
			gbc.gridx = 0;
			gbc.gridy = 0;
			panelBtn.add(getBtnValider(), gbc);
		}
		return panelBtn;
	}

	public JPanel getPanelMessage() {

		if (panelMessage == null) {
			panelMessage = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			panelMessage.add(getMessageErreur(), gbc);
		}

		return panelMessage;
	}

	private boolean checkPassword() {
		if (isNullOrEmpty(getTxtNewPassword().getText()) || getTxtNewPassword().getText().length() > 10) {
			return false;
		}
		return true;
	}

	private boolean isNullOrEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}

}
