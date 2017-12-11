package fr.eni.aniforme.ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.eni.aniforme.bo.Personnel;

public class PanelGestionPersonnel extends JPanel {

	private JButton btnAjouter, btnSupprimer, btnReinitialiser;
	private JPanel panelButtons;
	private JScrollPane tableauScrollPane;
	private JTable tableau;
	private TableEmployesModel tableauModel;
	private EcranAjoutClient ecranAjoutClient;
	
	public PanelGestionPersonnel() {
		
		setLayout(new BorderLayout());
		
		add(getPanelButtons(), BorderLayout.NORTH);

		add(getTableauScrollPane(), BorderLayout.CENTER);


	}

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
				getEcranAjoutClient();
				getEcranAjoutClient().setVisible(true);
				
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

	public JScrollPane getTableauScrollPane() {
		if (tableauScrollPane == null) {
			tableauScrollPane = new JScrollPane(getTable());
		}
		return tableauScrollPane;
	}

	public JTable getTable() {
		if (tableau == null) {
			tableau = new JTable(getTableauModel());
		}
		return tableau;
	}

	public TableEmployesModel getTableauModel() {
		if (tableauModel == null) {
			tableauModel = new TableEmployesModel();
		}
		return tableauModel;
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

	public EcranAjoutClient getEcranAjoutClient() {
		if (ecranAjoutClient == null) {
			ecranAjoutClient = new EcranAjoutClient();
		}
		return ecranAjoutClient;
	}



}
