package fr.eni.aniforme.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import fr.eni.aniforme.bll.AnimalManager;
import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.PersonnelManager;
import fr.eni.aniforme.bo.Personnel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PanelAgenda extends JPanel {

	private JLabel lblVeterinaire, lblDate;
	private JButton btnDossierMedical;
	private JComboBox<String> cboVeterinaire;
	PersonnelManager personnelManager = PersonnelManager.getInstance();
	private JScrollPane agendaScrollPane;
	private JTable tableau;
	private TableAgendaModel model;
	private UtilDateModel modelDp;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl dpCalendar;
	private JPanel panelVeterinaireDate;
	private JPanel panelDossierMedical;
	private EcranDossierMedical ecranDossierMedical;

	AnimalManager animalManager = AnimalManager.getInstance();

	public PanelAgenda(String nomVeto) {

		setLayout(new BorderLayout());

		add(getPanelVeterinaireDate(), BorderLayout.NORTH);

		getCboVeterinaire().setSelectedItem(nomVeto);
		getCboVeterinaire().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getModel().updateVeterinaire((String) getCboVeterinaire().getSelectedItem(),
						(Date) getDpCalendar().getModel().getValue());
			}
		});

		getDpCalendar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getModel().updateVeterinaire((String) getCboVeterinaire().getSelectedItem(),
						(Date) getDpCalendar().getModel().getValue());
			}
		});

		add(getAgendaScrollPane(), BorderLayout.CENTER);

		add(getPanelDossierMedical(), BorderLayout.SOUTH);

	}

	public JLabel getLblVeterinaire() {
		if (lblVeterinaire == null) {
			lblVeterinaire = new JLabel("Vétérinaire");
		}
		return lblVeterinaire;
	}

	public JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("Date");
		}
		return lblDate;
	}

	public JButton getBtnDossierMedical() {
		if (btnDossierMedical == null) {
			btnDossierMedical = new JButton(new ImageIcon("ic_folder_open_black_24dp_2x.png"));
			btnDossierMedical.setContentAreaFilled(false);
			btnDossierMedical.setToolTipText("Dossier médical");
			btnDossierMedical.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (getTableau().getSelectedRow() != -1) {
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								getEcranDossierMedical().setVisible(true);
							}
						});
					}

				}
			});
		}
		return btnDossierMedical;
	}

	public JComboBox<String> getCboVeterinaire() {
		List<String> veterinaires = new ArrayList<String>();
		List<Personnel> personnels;

		if (cboVeterinaire == null) {

			try {
				personnels = personnelManager.getVeterinaires();
				veterinaires.add(null);
				for (Personnel personnel : personnels) {
					veterinaires.add(personnel.getNom());
				}
			} catch (BLLException e) {

			}
			String[] veterinairesArray = veterinaires.toArray(new String[0]);
			cboVeterinaire = new JComboBox<String>(veterinairesArray);
		}
		return cboVeterinaire;
	}

	public JScrollPane getAgendaScrollPane() {
		if (agendaScrollPane == null) {
			agendaScrollPane = new JScrollPane(getTableau());
		}
		return agendaScrollPane;
	}

	public TableAgendaModel getModel() {
		if (model == null) {
			model = new TableAgendaModel();
		}
		return model;
	}

	public JTable getTableau() {
		if (tableau == null) {
			tableau = new JTable(getModel());
		}
		return tableau;
	}

	public UtilDateModel getModelDp() {
		if (modelDp == null) {
			modelDp = new UtilDateModel();
			modelDp.setValue(new Date());
		}
		return modelDp;
	}

	public JDatePanelImpl getDatePanel() {
		if (datePanel == null) {
			datePanel = new JDatePanelImpl(getModelDp());
		}
		return datePanel;
	}

	public JDatePickerImpl getDpCalendar() {
		if (dpCalendar == null) {
			dpCalendar = new JDatePickerImpl(getDatePanel());

		}
		return dpCalendar;
	}

	public JPanel getPanelVeterinaireDate() {
		if (panelVeterinaireDate == null) {
			panelVeterinaireDate = new JPanel();
			panelVeterinaireDate.add(getLblVeterinaire());
			panelVeterinaireDate.add(getCboVeterinaire());
			panelVeterinaireDate.add(getLblDate());
			panelVeterinaireDate.add(getDpCalendar());

		}

		return panelVeterinaireDate;
	}

	public JPanel getPanelDossierMedical() {
		if (panelDossierMedical == null) {
			panelDossierMedical = new JPanel();
			panelDossierMedical.add(getBtnDossierMedical());
		}
		return panelDossierMedical;
	}

	public EcranDossierMedical getEcranDossierMedical() {
		if (ecranDossierMedical == null) {
			try {
				ecranDossierMedical = new EcranDossierMedical(animalManager
						.getAnimalById(getModel().getValueAt(getTableau().getSelectedRow()).getCodeAnimal()));
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		return ecranDossierMedical;
	}

}
