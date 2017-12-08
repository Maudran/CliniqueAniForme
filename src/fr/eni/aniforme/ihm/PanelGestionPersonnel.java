package fr.eni.aniforme.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class PanelGestionPersonnel extends JPanel {

	private JButton btnAjouter, btnSupprimer, btnReinitialiser;
	private JPanel panelButtons;

	public JButton getBtnAjouter() {
		if (btnAjouter == null) {
			btnAjouter = new JButton("Ajouter");
		}
		btnAjouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frameAjoutEmploye = new JFrame("Ajout d'un employé");
				frameAjoutEmploye.setVisible(true);
				frameAjoutEmploye.pack();

			}
		});
		return btnAjouter;
	}

	public JButton getBtnSupprimer() {
		if (btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer");
		}
		btnSupprimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frameSupprimerEmploye = new JFrame("Archivage d'un employé");
				frameSupprimerEmploye.setVisible(true);
				frameSupprimerEmploye.pack();
				
			}
		});
		return btnSupprimer;
	}

	public JButton getBtnReinitialiser() {
		if (btnReinitialiser == null) {
			btnReinitialiser = new JButton("Réinitialiser");
		}
		btnReinitialiser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frameReinitialiserEmploye = new JFrame("Réinitialiser le mot de passe d'un employé");
				frameReinitialiserEmploye.setVisible(true);
				frameReinitialiserEmploye.pack();
				
			}
		});
		return btnReinitialiser;
	}

	public JPanel getPanelButtons() {
		if (panelButtons == null) {
			panelButtons = new JPanel();
			panelButtons.add(getBtnAjouter());
			panelButtons.add(getBtnSupprimer());
			panelButtons.add(getBtnReinitialiser());

			return panelButtons;
		}
		return panelButtons;

	}

	public PanelGestionPersonnel() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridy = 0;
		gbc.gridx = 0;
		// gbc.insets = new Insets(2, 5, 2, 5);
		add(getBtnAjouter(), gbc);

		gbc.gridy = 0;
		gbc.gridx = 1;
		add(getBtnSupprimer(), gbc);

		gbc.gridy = 0;
		gbc.gridx = 2;
		add(getBtnReinitialiser(), gbc);

	}

}
