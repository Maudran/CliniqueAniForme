package fr.eni.aniforme.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
				frameAjoutEmploye.setSize(800, 500);
				frameAjoutEmploye.setLocationRelativeTo(null);
				
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
				JOptionPane jop1;

				jop1 = new JOptionPane();
				JOptionPane.showMessageDialog(jop1, "Confirmez vous l'archivage");
			

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
				frameReinitialiserEmploye.setSize(800, 500);
				frameReinitialiserEmploye.setLocationRelativeTo(null);

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
