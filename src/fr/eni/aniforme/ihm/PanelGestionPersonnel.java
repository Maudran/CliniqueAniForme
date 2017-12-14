package fr.eni.aniforme.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.PersonnelManager;
import fr.eni.aniforme.bo.Personnel;
import fr.eni.aniforme.ihm.DialogReinitMotPasse.ReinitListener;
import fr.eni.aniforme.ihm.EcranAjoutPersonnel.EmployeListener;

public class PanelGestionPersonnel extends JPanel implements EmployeListener, ReinitListener {

	private JButton btnAjouter, btnSupprimer, btnReinitialiser;
	private JPanel panelButtons;
	private JScrollPane tableauScrollPane;
	private JTable tableau;
	private TableEmployesModel tableauModel;
	private EcranAjoutPersonnel ecranAjoutPersonnel;
	private DialogReinitMotPasse dialogReinitMotPasse;

	PersonnelManager personnelManager = PersonnelManager.getInstance();

	public PanelGestionPersonnel(JFrame frame) {

		setLayout(new BorderLayout());

		add(getPanelButtons(frame), BorderLayout.NORTH);

		add(getTableauScrollPane(), BorderLayout.CENTER);
	}

	public JButton getBtnAjouter() {
		if (btnAjouter == null) {
			btnAjouter = new JButton(new ImageIcon("web/ic_add_black_24dp_1x.png"));
			btnAjouter.setContentAreaFilled(false);
			btnAjouter.setToolTipText("Ajouter employé");
		}
		btnAjouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						getEcranAjoutPersonnel().setVisible(true);
					}
				});

			}
		});
		return btnAjouter;
	}

	public JButton getBtnSupprimer(JFrame frame) {
		if (btnSupprimer == null) {
			btnSupprimer = new JButton(new ImageIcon("ic_delete_black_24dp/web/ic_delete_black_24dp_1x.png"));
			btnSupprimer.setContentAreaFilled(false);
			btnSupprimer.setToolTipText("Supprimer employé");
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
			btnReinitialiser = new JButton(new ImageIcon("ic_lock_open_black_24dp_1x.png"));
			btnReinitialiser.setContentAreaFilled(false);
			btnReinitialiser.setToolTipText("Réinitialiser mot de passe");
		}
		btnReinitialiser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getTable().getSelectedRow() != -1) {
					getDialogReinitMotPasse().setVisible(true);
				}
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

	public EcranAjoutPersonnel getEcranAjoutPersonnel() {
		if (ecranAjoutPersonnel == null) {
			ecranAjoutPersonnel = new EcranAjoutPersonnel(this);
		}
		return ecranAjoutPersonnel;
	}

	public DialogReinitMotPasse getDialogReinitMotPasse() {
		if (dialogReinitMotPasse == null) {
			dialogReinitMotPasse = new DialogReinitMotPasse(getTableauModel().getValueAt(getTable().getSelectedRow()), this);
		}
		return dialogReinitMotPasse;
	}

	@Override
	public void refreshTable() {
		getTableauModel().updateData();
		
	}

	@Override
	public void reinitMotPasse(String motPasse) {
		Personnel employe = getTableauModel().getValueAt(getTable().getSelectedRow());
		employe.setMotPasse(motPasse);
		
		try {
			personnelManager.updatePersonnel(employe);
			getTableauModel().updateData();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
	}

}
