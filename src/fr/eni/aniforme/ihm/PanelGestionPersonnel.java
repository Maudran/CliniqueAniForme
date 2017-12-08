package fr.eni.aniforme.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class PanelGestionPersonnel extends JPanel {

	private JButton btnAjouter, btnSupprimer, btnReinitialiser;
	private JPanel panelButtons;


	public JButton getBtnAjouter() {
		if (btnAjouter == null) {
			btnAjouter = new JButton("Ajouter");
		}
		return btnAjouter;
	}

	public JButton getBtnSupprimer() {
		if (btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer");
		}
		return btnSupprimer;
	}

	public JButton getBtnReinitialiser() {
		if (btnReinitialiser == null) {
			btnReinitialiser = new JButton("Réinitialiser");
		}
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
