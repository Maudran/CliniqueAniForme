package fr.eni.aniforme.ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.ClientManager;
import fr.eni.aniforme.bo.Client;

public class EcranAjoutClient extends JFrame {

	private JPanel panelBtns, panelChamps;
	private JButton btnValider, btnAnnuler;
	private JLabel lblCode, code, lblNom, lblPrenom, lblAdresse1, lblCodePostal, lblVille;
	private JTextField txtNom, txtPrenom, txtAdresse1, txtAdresse2, txtCodePostal, txtVille;

	ClientManager clientManager = ClientManager.getInstance();

	private ClientListener listener;

	public EcranAjoutClient(ClientListener listener) {
		
		this.listener = listener;
		
		setTitle("Ajout client");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		add(getPanelChamps(), BorderLayout.CENTER);
		add(getPanelBtns(), BorderLayout.SOUTH);
	}

	public JPanel getPanelBtns() {
		if (panelBtns == null) {
			panelBtns = new JPanel();
			panelBtns.add(getBtnValider());
			panelBtns.add(getBtnAnnuler());
		}
		return panelBtns;
	}

	public JPanel getPanelChamps() {
		if (panelChamps == null) {
			panelChamps = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.anchor = GridBagConstraints.WEST;

			gbc.gridx = 0;
			gbc.gridy = 0;
			panelChamps.add(getLblCode(), gbc);
			gbc.gridx = 1;
			panelChamps.add(getCode(), gbc);

			gbc.gridx = 0;
			gbc.gridy = 1;
			panelChamps.add(getLblNom(), gbc);
			gbc.gridx = 1;
			panelChamps.add(getTxtNom(), gbc);

			gbc.gridx = 0;
			gbc.gridy = 2;
			panelChamps.add(getLblPrenom(), gbc);
			gbc.gridx = 1;
			panelChamps.add(getTxtPrenom(), gbc);

			gbc.gridx = 0;
			gbc.gridy = 3;
			panelChamps.add(getLblAdresse1(), gbc);
			gbc.gridx = 1;
			panelChamps.add(getTxtAdresse1(), gbc);

			gbc.gridy = 4;
			panelChamps.add(getTxtAdresse2(), gbc);

			gbc.gridx = 0;
			gbc.gridy = 5;
			panelChamps.add(getLblCodePostal(), gbc);
			gbc.gridx = 1;
			panelChamps.add(getTxtCodePostal(), gbc);

			gbc.gridx = 0;
			gbc.gridy = 6;
			panelChamps.add(getLblVille(), gbc);
			gbc.gridx = 1;
			panelChamps.add(getTxtVille(), gbc);

		}
		return panelChamps;
	}

	public JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						int code = clientManager.insertClient(getClientFromChamps());
						listener.afficherClient(clientManager.getClientById(code));
					} catch (BLLException e1) {
						e1.printStackTrace();
					}

				}
			});
		}
		return btnValider;
	}

	public JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					effacerChamps();
				}
			});
		}
		return btnAnnuler;
	}

	public JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("Code ");
		}
		return lblCode;
	}

	public JLabel getCode() {
		if (code == null) {
			code = new JLabel("");
		}
		return code;
	}

	public JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom ");
		}
		return lblNom;
	}

	public JLabel getLblPrenom() {
		if (lblPrenom == null) {
			lblPrenom = new JLabel("Prénom ");
		}
		return lblPrenom;
	}

	public JLabel getLblAdresse1() {
		if (lblAdresse1 == null) {
			lblAdresse1 = new JLabel("Adresse ");
		}
		return lblAdresse1;
	}

	public JLabel getLblCodePostal() {
		if (lblCodePostal == null) {
			lblCodePostal = new JLabel("Code postal ");
		}
		return lblCodePostal;
	}

	public JLabel getLblVille() {
		if (lblVille == null) {
			lblVille = new JLabel("Ville ");
		}
		return lblVille;
	}

	public JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField(20);
		}
		return txtNom;
	}

	public JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField(20);
		}
		return txtPrenom;
	}

	public JTextField getTxtAdresse1() {
		if (txtAdresse1 == null) {
			txtAdresse1 = new JTextField(20);
		}
		return txtAdresse1;
	}

	public JTextField getTxtAdresse2() {
		if (txtAdresse2 == null) {
			txtAdresse2 = new JTextField(20);
		}
		return txtAdresse2;
	}

	public JTextField getTxtCodePostal() {
		if (txtCodePostal == null) {
			txtCodePostal = new JTextField(20);
		}
		return txtCodePostal;
	}

	public JTextField getTxtVille() {
		if (txtVille == null) {
			txtVille = new JTextField(20);
		}
		return txtVille;
	}

	public Client getClientFromChamps() {
		Client client = new Client();

		client.setNom(getTxtNom().getText());
		client.setPrenom(getTxtPrenom().getText());
		client.setAdresse1(getTxtAdresse1().getText());
		client.setAdresse2(getTxtAdresse2().getText());
		client.setCodePostal(getTxtCodePostal().getText());
		client.setVille(getTxtVille().getText());

		return client;
	}

	public void effacerChamps() {
		getCode().setText(null);
		getTxtNom().setText(null);
		getTxtPrenom().setText(null);
		getTxtAdresse1().setText(null);
		getTxtAdresse2().setText(null);
		getTxtCodePostal().setText(null);
		getTxtVille().setText(null);

	}
}
