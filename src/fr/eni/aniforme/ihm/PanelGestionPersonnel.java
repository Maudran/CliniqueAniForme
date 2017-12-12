package fr.eni.aniforme.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.PersonnelManager;

public class PanelGestionPersonnel extends JPanel {

	private JButton btnAjouter, btnSupprimer, btnReinitialiser;
	private JPanel panelButtons;
	private JScrollPane tableauScrollPane;
	private JTable tableau;
	private TableEmployesModel tableauModel;
	private EcranAjoutPersonnel ecranAjoutPersonnel;

	PersonnelManager personnelManager = PersonnelManager.getInstance();

	public PanelGestionPersonnel(JFrame frame) {

		setLayout(new BorderLayout());

		add(getPanelButtons(frame), BorderLayout.NORTH);

		add(getTableauScrollPane(), BorderLayout.CENTER);
	}

	public JButton getBtnAjouter() {
		if (btnAjouter == null) {
			btnAjouter = new JButton("Ajouter");
		}
		btnAjouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						EcranAjoutPersonnel eap = new EcranAjoutPersonnel();
						eap.setVisible(true);
					}
				});

			}
		});
		return btnAjouter;
	}

	public JButton getBtnSupprimer(JFrame frame) {
		if (btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer");
		}
		btnSupprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getTable().getSelectedRow() != -1) {
					if (JOptionPane.showConfirmDialog(frame, "Voulez vous vraiment archiver cet employé ?",
							"Demande de confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						try {
							personnelManager.archiverEmploye(
									getTableauModel().getValueAt(getTable().getSelectedRow()).getCodePers());
							getTableauModel().updateData();
						} catch (BLLException e1) {
							e1.printStackTrace();
						}
					}
				}

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

	public JPanel getPanelButtons(JFrame frame) {
		if (panelButtons == null) {
			panelButtons = new JPanel();
			panelButtons.add(getBtnAjouter());
			panelButtons.add(getBtnSupprimer(frame));
			panelButtons.add(getBtnReinitialiser());

			return panelButtons;
		}
		return panelButtons;

	}

}
