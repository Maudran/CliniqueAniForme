package fr.eni.aniforme.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PanelClients extends JPanel {

	private JButton btnRechercherClient, btnAjouterClient, btnSupprimerClient, btnValider, btnAnnuler, btnAjouterAnimal,
			btnSupprimerAnimal, btnEditerAnimal;
	private JLabel lblCode, lblNom, lblPrenom, lblAdresse1, lblAdresse2, lblCodePostal, lblVille;
	private JTextField txtCode, txtNom, txtPrenom, txtAdresse1, txtAdresse2, txtCodePostal, txtVille;
	private JPanel panelButtons;
	private JPanel panelCoordonneesClients;
	private JTable tableauAnimaux;

	public JButton getBtnRechercherClient() {
		if (btnRechercherClient == null) {
			btnRechercherClient = new JButton("Recherche");
		}
		btnRechercherClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frameRechercheClient = new JFrame("Résultat de la recherche");
				frameRechercheClient.setVisible(true);
				frameRechercheClient.pack();
				frameRechercheClient.setSize(500, 200);
				frameRechercheClient.setLocationRelativeTo(null);
				
			}
		});
		return btnRechercherClient;
	}

	public JButton getBtnAjouterClient() {
		if (btnAjouterClient == null) {
			btnAjouterClient = new JButton ("Ajouter");
		}
		btnAjouterClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frameAjoutClient = new JFrame("Ajouter un client");
				frameAjoutClient.setVisible(true);
				frameAjoutClient.pack();
				frameAjoutClient.setSize(500, 200);
				frameAjoutClient.setLocationRelativeTo(null);
				
			}
		});
		return btnAjouterClient;
	}

	public JButton getBtnSupprimerClient() {
		if (btnSupprimerClient == null) {
			btnSupprimerClient = new JButton ("Supprimer");
		}
		btnSupprimerClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane jop1;

				jop1 = new JOptionPane();
				JOptionPane.showMessageDialog(jop1, "Confirmez vous l'archivage");
			

			}
		});
		return btnSupprimerClient;
	}

	public JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton ("Valider");
		}
		return btnValider;
	}

	public JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton ("Annuler");
		}
		return btnAnnuler;
	}

	public JButton getBtnAjouterAnimal() {
		if (btnAjouterAnimal == null) {
			btnAjouterAnimal = new JButton ("Ajouter");
		}
		return btnAjouterAnimal;
	}

	public JButton getBtnSupprimerAnimal() {
		if (btnSupprimerAnimal == null) {
			btnSupprimerAnimal = new JButton ("Supprimer");
		}
		return btnSupprimerAnimal;
	}

	public JButton getBtnEditerAnimal() {
		if (btnEditerAnimal == null) {
			btnEditerAnimal = new JButton ("Editer");
		}
		return btnEditerAnimal;
	}

	public JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("Code");
		}
		return lblCode;
	}

	public JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
		}
		return lblNom;
	}

	public JLabel getLblPrenom() {
		if (lblPrenom == null) {
			lblPrenom = new JLabel("Prenom");
		}
		return lblPrenom;
	}

	public JLabel getLblAdresse1() {
		if (lblAdresse1 == null) {
			lblAdresse1 = new JLabel("Adresse");
		}
		return lblAdresse1;
	}

	public JLabel getLblAdresse2() {
		if (lblAdresse2 == null) {
			lblAdresse2 = new JLabel("Adresse");
		}
		return lblAdresse2;
	}

	public JLabel getLblCodePostal() {
		if (lblCodePostal == null) {
			lblCodePostal = new JLabel("Code Postal");
		}
		return lblCodePostal;
	}

	public JLabel getLblVille() {
		if (lblVille == null) {
			lblVille = new JLabel("Ville");
		}
		return lblVille;
	}

	public JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField(20);
		}
		return txtCode;
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

	public JPanel getPanelButtons() {
		return panelButtons;
	}

	public JPanel getPanelCoordonneesClients() {
		return panelCoordonneesClients;
	}

	public JTable getTableauAnimaux() {
		return tableauAnimaux;
	}

	public PanelClients() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		add(getBtnRechercherClient(), gbc);

		gbc.gridy = 0;
		gbc.gridx = 1;
		add(getBtnAjouterClient(), gbc);

		gbc.gridy = 0;
		gbc.gridx = 2;
		add(getBtnSupprimerClient(), gbc);

		gbc.gridy = 0;
		gbc.gridx = 3;
		add(getBtnValider(), gbc);

		gbc.gridy = 0;
		gbc.gridx = 4;
		add(getBtnAnnuler(), gbc);

		gbc.gridy = 6;
		gbc.gridx = 4;
		add(getBtnAjouterAnimal(), gbc);

		gbc.gridy = 6;
		gbc.gridx = 5;
		add(getBtnSupprimerAnimal(), gbc);

		gbc.gridy = 6;
		gbc.gridx = 6;
		add(getBtnEditerAnimal(), gbc);

		gbc.gridy = 1;
		gbc.gridx = 0;
		add(getLblCode(), gbc);

		gbc.gridx = 1;
		add(getTxtCode(), gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		add(getLblNom(), gbc);

		gbc.gridx = 1;
		add(getTxtNom(), gbc);

		gbc.gridy = 3;
		gbc.gridx = 0;
		add(getLblPrenom(), gbc);

		gbc.gridx = 1;
		add(getTxtPrenom(), gbc);

		gbc.gridy = 4;
		gbc.gridx = 0;
		add(getLblAdresse1(), gbc);

		gbc.gridx = 1;
		add(getTxtAdresse1(), gbc);

		gbc.gridy = 5;
		gbc.gridx = 0;
		add(getLblAdresse2(), gbc);

		gbc.gridx = 1;
		add(getTxtAdresse2(), gbc);

		gbc.gridy = 6;
		gbc.gridx = 0;
		add(getLblCodePostal(), gbc);

		gbc.gridx = 1;
		add(getTxtCodePostal(), gbc);

		gbc.gridy = 7;
		gbc.gridx = 0;
		add(getLblVille(), gbc);

		gbc.gridx = 1;
		add(getTxtVille(), gbc);

	}

}
