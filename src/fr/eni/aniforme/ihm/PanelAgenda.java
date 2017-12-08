package fr.eni.aniforme.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
			btnDossierMedical = new JButton("Dossier Médical");
		}
		return btnDossierMedical;
	}

	public JComboBox getCboVeterinaire() {
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

	public PanelAgenda() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		UtilDateModel model = new UtilDateModel();
		model.setValue(new Date());
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl dpCalendar = new JDatePickerImpl(datePanel);

		gbc.gridy = 0;
		gbc.gridx = 0;
		add(getLblVeterinaire(), gbc);

		gbc.gridy = 0;
		gbc.gridx = 1;
		add(getCboVeterinaire(), gbc);

		gbc.gridy = 0;
		gbc.gridx = 2;
		add(getLblDate(), gbc);

		gbc.gridy = 0;
		gbc.gridx = 4;
		add(dpCalendar, gbc);

		gbc.gridy = 8;
		gbc.gridx = 5;
		add(getBtnDossierMedical(), gbc);

	}

}
